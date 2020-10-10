package com.data.big.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.big.mapper.*;
import com.data.big.model.*;
import com.data.big.netty.BootNettyChannelInboundHandlerAdapter;
import com.data.big.service.ServiceNetty;
import com.data.big.util.*;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service("ServiceNetty")
public class ServiceNettyImpl implements ServiceNetty {
    //心跳 帧序号
    private static String keepAliveNum = "0000";
    //心跳回执序号
    private static String keepAliveNumReceipt = "0000";
    //报警回执序号
    private static String AlarmReceipt = "0000";

    //注册 帧序号
    private static String registerNum = "0000";

    @Autowired
    private WindSpeedAlarmMapper windSpeedAlarmMapper;

    @Autowired
    private RainAlarmMapper rainAlarmMapper;
    @Autowired
    private SnowAlarmMapper snowAlarmMapper;
    @Autowired
    private ForeignBodyAlarmMapper foreignBodyAlarmMapper;
    @Autowired
    private SensorStatusAlarmMapper sensorStatusAlarmMapper;
    @Autowired
    private UnitEquipmentAlarmMapper unitEquipmentAlarmMapper;
    @Autowired
    private LogRestMapper logRestMapper;
    @Autowired
    private VideoFileMapper videoFileMapper;
    @Autowired
    private VideoKilometerMapper videoKilometerMapper;
    @Autowired
    private CameraMapper cameraMapper;

    // 日志记录器
    private static final Logger logger = LogManager.getLogger(ServiceNettyImpl.class);

    @Override
    public void receiveData(ChannelHandlerContext ctx, Object msg) {

        String ms = (String) msg;
        String[] c0c0s = ms.split("C0C0");
        if (c0c0s.length >= 2) {
            if(ms.indexOf("C0C0", 1)>0){
            String substring = ms.substring(ms.indexOf("C0C0") + 4, ms.indexOf("C0C0", 1));
            byte[] new_bts = CRCUtil.toByteArray(substring);//转byte数组
                byte[] bytes1 = CRCUtil.ENDecodeData(new_bts);//替换 c0c0等编码

                byte[] ver = getByets(bytes1,0,bytes1.length-2);//去掉 验证码

                byte[] verification = getByets(bytes1,bytes1.length-2,bytes1.length);//取 验证码
                String code = CRCUtil.bytesToHex(verification);

                String leng = CRCUtil.byteToHex(bytes1[6]) + CRCUtil.byteToHex(bytes1[7]);
                int i = Integer.parseInt(leng, 16);//取命令长度 转10进制
                int crc1021 = CRCUtil.getCRC1021(bytes1, i); //获取验证码

                int i1 = Integer.parseInt(code, 16);//取验证码 转10进制
                if (crc1021 == i1) {//比度验证码  一致表示数据正确 不一致 不处理
                    String type = CRCUtil.byteToHex(ver[5]);
                    String result = CRCUtil.byteToHex(ver[8]);

                    switch (type) {

                        case "67"://注册回执
                            if ("01".equals(result)) {//成功
                                BootNettyChannelInboundHandlerAdapter.boo = true;
                                logger.info("接收注册命令  注册成功 ");
                            } else {
                                logger.error("注册失败：" + result);
                            }
                            break;
                        case "01"://报警开始
                        {
                            byte[] dataByets = getByets(ver, 13, ver.length);//获取报警数据
                            switch (result) {
                                case "00"://风速 报警
                                    windAlarm(dataByets, ms, result);
                                    break;
                                case "01"://雨量报警
                                    rainAlarm(dataByets, ms, result);
                                    break;
                                case "02"://雪深报警
                                    snowAararm(dataByets, ms, result);
                                    break;
                                case "03"://异物报警
                                    foreignBodyAlarm(dataByets, ms, result);
                                    break;
                                case "04"://传感器设备 报警
                                    sensorAlarm(dataByets, ms, result);
                                    break;
                                case "05"://监控单元设备报警
                                    unitAlarm(dataByets, ms, result);
                                    break;

                                default:
                                    logger.error("收到未知得报警命令 ：" + result);
                                    addLog(new Date(),"收到未知得报警命令："+result,ms);
                                    break;
                            }
                            break;
                        }
                        case "02"://报警结束
                        {
                            byte[] dataByets = getByets(ver, 13, ver.length);//获取报警数据
                            String alarmType = CRCUtil.byteToHex(dataByets[8]);
                            switch (alarmType) {
                                case "05"://风速 报警结束
                                    windAlarmEnd(dataByets, ms, result);
                                    break;
                                case "06"://雨量报警 结束
                                    rainAlarmEnd(dataByets, ms, result);
                                    break;
                                case "07"://雪深报警结束
                                    snowAararmEnd(dataByets, ms, result);
                                    break;
                                case "08"://异物报警结束
                                    foreignBodyAlarmEnd(dataByets, ms, result);
                                    break;

                                default:
                                    logger.error("收到未知得解除报警命令 ：" + alarmType);
                                    addLog(new Date(),"收到未知得解除报警命令 ："+alarmType,ms);
                                    break;
                            }
                            break;
                        }
                        case "65"://心跳回执
                            logger.info("接收到心跳回执 数据为："+ms);
                            if(Integer.parseInt(keepAliveNum,16)%1000==0){
                                String s4 = CRCUtil.Bytes2HexString(getByets(ver, ver.length - 14, ver.length));
                                String s = AsciiStringToHex.convertHexToString(s4);
                                Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
                                addLog(DateUtils.parseDate(yyyyMMddHHmmss,"yyyyMMddHHmmss"),"发送心跳",ms);
                            }
                            break;
                        case "64"://心跳
                            sendKeepAliveReceipt();
                            break;
                        default:
                            logger.error("收到未知得检测类型命令 ：" + result);
                            addLog(new Date(),"收到未知得检测类型命令："+result,ms);
                            break;
                    }

                } else {
                    logger.error("验证码没有匹配 不处理数据：数据为" + ms);
                    addLog(new Date(),"验证码没有匹配 不处理数据",ms);
                }
            }else{
                logger.error("数据错误 数据不完整：数据为" + ms);
                addLog(new Date(),"数据错误 数据不完整",ms);
            }

        } else {
            logger.error("数据错误：数据为" + ms);
            addLog(new Date(),"收到数据错误",ms);
        }


    }
    private  void addLog(Date date,String ms,String data){
        LogRest log = new LogRest();
        log.setFunname("gitData");
        log.setIp(Properties.getNettyHost()+":"+Properties.getNettyPost());
        log.setLrsj(new Date());
        log.setParamin("");
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("data",data);

        log.setRedata(jsonParam.toJSONString());
        log.setType(1 + "");
        logRestMapper.insert(log);
    }

    private void foreignBodyAlarmEnd(byte[] dataByets, String ms, String result) {
        ForeignBodyAlarm foreignBodyAlarm = new ForeignBodyAlarm();
        foreignBodyAlarm.setAlarmStatus("0");
        foreignBodyAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<ForeignBodyAlarm> select = foreignBodyAlarmMapper.select(foreignBodyAlarm);
        if(select.size()>0){
            ForeignBodyAlarm wsa = new ForeignBodyAlarm();
            wsa.setAlarmStatus("1");
            String s4 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
            String s = AsciiStringToHex.convertHexToString(s4);
            Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
            wsa.setRelieveTime(yyyyMMddHHmmss);
            wsa.setData(select.get(0).getData()+";"+ms);
            Example example = new Example(ForeignBodyAlarm.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", select.get(0).getId());
            int i = foreignBodyAlarmMapper.updateByExampleSelective(wsa, example);
            if (i > 0) {
                addVideoTask(select.get(0).getCheckLocaleCode(),DateUtils.getDate(select.get(0).getDate(),"yyyy-MM-dd HH:mm:ss"),DateUtils.getDate(yyyyMMddHHmmss,"yyyy-MM-dd HH:mm:ss"));

                logger.info("保存异物解除告警数据成功");
                //发送回执信息
                sendAlarmReceipt("08",result,"00");
            }else{
                sendAlarmReceipt("08",result,"01");
            }
        }
    }

    private void snowAararmEnd(byte[] dataByets, String ms, String result) {
        SnowAlarm snowAlarm = new SnowAlarm();
        snowAlarm.setAlarmStatus("0");
        snowAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<SnowAlarm> select = snowAlarmMapper.select(snowAlarm);
        if(select.size()>0){
            SnowAlarm wsa = new SnowAlarm();
            wsa.setAlarmStatus("1");
            String s4 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
            String s = AsciiStringToHex.convertHexToString(s4);
            Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
            wsa.setRelieveTime(yyyyMMddHHmmss);
            wsa.setData(select.get(0).getData()+";"+ms);
            Example example = new Example(SnowAlarm.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", select.get(0).getId());
            int i = snowAlarmMapper.updateByExampleSelective(wsa, example);
            if (i > 0) {
                addVideoTask(select.get(0).getCheckLocaleCode(),DateUtils.getDate(select.get(0).getDate(),"yyyy-MM-dd HH:mm:ss"),DateUtils.getDate(yyyyMMddHHmmss,"yyyy-MM-dd HH:mm:ss"));

                logger.info("保存雪深解除告警数据成功");
                //发送回执信息
                sendAlarmReceipt("08",result,"00");
            }else{
                sendAlarmReceipt("08",result,"01");
            }
        }
    }

    private void rainAlarmEnd(byte[] dataByets, String ms, String result) {
        RainAlarm rainAlarm = new RainAlarm();
        rainAlarm.setAlarmStatus("0");
        rainAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<RainAlarm> select = rainAlarmMapper.select(rainAlarm);
        if(select.size()>0){
            RainAlarm wsa = new RainAlarm();
            wsa.setAlarmStatus("1");
            String s4 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
            String s = AsciiStringToHex.convertHexToString(s4);
            Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
            wsa.setRelieveTime(yyyyMMddHHmmss);
            wsa.setData(select.get(0).getData()+";"+ms);
            Example example = new Example(RainAlarm.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", select.get(0).getId());
            int i = rainAlarmMapper.updateByExampleSelective(wsa, example);
            if (i > 0) {
                addVideoTask(select.get(0).getCheckLocaleCode(),DateUtils.getDate(select.get(0).getDate(),"yyyy-MM-dd HH:mm:ss"),DateUtils.getDate(yyyyMMddHHmmss,"yyyy-MM-dd HH:mm:ss"));

                logger.info("保存雨量解除告警数据成功");
                //发送回执信息
                sendAlarmReceipt("08",result,"00");
            }else{
                sendAlarmReceipt("08",result,"01");
            }
        }

    }

    private void windAlarmEnd(byte[] dataByets, String ms, String result) {
        WindSpeedAlarm windSpeedAlarm = new WindSpeedAlarm();
        windSpeedAlarm.setAlarmStatus("0");
        windSpeedAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<WindSpeedAlarm> select = windSpeedAlarmMapper.select(windSpeedAlarm);
        if(select.size()>0){
            WindSpeedAlarm wsa = new WindSpeedAlarm();
            wsa.setAlarmStatus("1");
            String s4 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
            String s = AsciiStringToHex.convertHexToString(s4);
            Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
            wsa.setRelieveTime(yyyyMMddHHmmss);
            wsa.setData(select.get(0).getData()+";"+ms);
            Example example = new Example(WindSpeedAlarm.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", select.get(0).getId());
            int i = windSpeedAlarmMapper.updateByExampleSelective(wsa, example);
            if (i > 0) {
                addVideoTask(select.get(0).getCheckLocaleCode(),DateUtils.getDate(select.get(0).getDate(),"yyyy-MM-dd HH:mm:ss"),DateUtils.getDate(yyyyMMddHHmmss,"yyyy-MM-dd HH:mm:ss"));
                logger.info("保存风速解除告警数据成功");
                //发送回执信息
                sendAlarmReceipt("08",result,"00");
            }else{
                sendAlarmReceipt("08",result,"01");
            }
        }

    }
    private int addVideoTask(String k,String startTime,String endTime){
        Camera camera=cameraMapper.getMinVideoCode(k);
        if(camera!=null&&camera.getDeviceId()!=null){

            VideoFile videoFile=new VideoFile();
            videoFile.setId(UUIDHelper.getUUIDStr());
            videoFile.setIpcid(camera.getDeviceId());
            videoFile.setKssj(startTime);
            videoFile.setJssj(endTime);
            videoFile.setStatus("0");
            int i = videoFileMapper.insertSelective(videoFile);//添加到任务表里
        }
        Camera camera1=cameraMapper.getMxnVideoCode(k);
        if(camera1!=null&&camera1.getDeviceId()!=null){
            VideoFile videoFile=new VideoFile();
            videoFile.setId(UUIDHelper.getUUIDStr());
            videoFile.setIpcid(camera1.getDeviceId());
            videoFile.setKssj(startTime);
            videoFile.setJssj(endTime);
            videoFile.setStatus("0");
            int i1 = videoFileMapper.insertSelective(videoFile);//添加到任务表里
        }
        return 1;
    }


    private void unitAlarm(byte[] dataByets, String ms, String result) {
        UnitEquipmentAlarm unitEquipmentAlarm=new UnitEquipmentAlarm();
        unitEquipmentAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 0, 4)));
        unitEquipmentAlarm.setaHostStatus(CRCUtil.byteToHex(dataByets[4]));
        unitEquipmentAlarm.setbHostStatus(CRCUtil.byteToHex(dataByets[5]));
        long hevToInt2 = getHevToInt(getByets(dataByets, 6, 8));
        unitEquipmentAlarm.setCardDataLength("" + hevToInt2);
        int num=8+(int)hevToInt2;
        String s1 = CRCUtil.Bytes2HexString(getByets(dataByets, 8, num));
        unitEquipmentAlarm.setCardStatus("" + AsciiStringToHex.convertUTF8ToString(s1));
        unitEquipmentAlarm.setSourceAStatus(CRCUtil.byteToHex(dataByets[num]));
        unitEquipmentAlarm.setSourceBStatus(CRCUtil.byteToHex(dataByets[num+1]));
        unitEquipmentAlarm.setSourceAInVoltageValue("" + getHevToInt(getByets(dataByets, num+2, num+4)));
        unitEquipmentAlarm.setSourceBInVoltageValue("" + getHevToInt(getByets(dataByets, num+4, num+6)));
        unitEquipmentAlarm.setSourceAOutVoltageValue("" + getHevToInt(getByets(dataByets, num+6, num+8)));
        unitEquipmentAlarm.setSourceBOutVoltageValue("" + getHevToInt(getByets(dataByets, num+8, num+10)));
        unitEquipmentAlarm.setSourceARemainingElectricity("" + getHevToInt(getByets(dataByets, num+10, num+12)));
        unitEquipmentAlarm.setSourceBRemainingElectricity("" + getHevToInt(getByets(dataByets, num+12, num+14)));
        long hevToInt3 = getHevToInt(getByets(dataByets, num+14, num+16));
        unitEquipmentAlarm.setRunDataLength(hevToInt3+"");
        num=num+16;
        String s3 = CRCUtil.Bytes2HexString(getByets(dataByets, num, num+(int)hevToInt3));
        unitEquipmentAlarm.setSourceRunStatus("" + AsciiStringToHex.convertUTF8ToString(s3));
        unitEquipmentAlarm.setFaultType(CRCUtil.byteToHex(dataByets[dataByets.length - 19]));
        unitEquipmentAlarm.setReserve1("" + getHevToInt(getByets(dataByets, dataByets.length - 18, dataByets.length - 16)));
        unitEquipmentAlarm.setReserve2("" + getHevToInt(getByets(dataByets, dataByets.length - 16, dataByets.length - 14)));
        String s4 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
        String s = AsciiStringToHex.convertHexToString(s4);
        Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
        unitEquipmentAlarm.setDate(yyyyMMddHHmmss);
        unitEquipmentAlarm.setId(UUIDHelper.getUUIDStr());
        unitEquipmentAlarm.setData(ms);
        unitEquipmentAlarm.setVideoUpStatus("0");
        unitEquipmentAlarm.setAlarmStatus("0");
        int insert = unitEquipmentAlarmMapper.insert(unitEquipmentAlarm);
        if (insert > 0) {
            logger.info("保存设备单元告警开始数据成功");
            //发送回执信息
            sendAlarmReceipt("09",result,"00");
        }else{
            sendAlarmReceipt("09",result,"01");
        }
    }

    private void sensorAlarm(byte[] dataByets, String ms, String result) {
        SensorStatusAlarm ssa = new SensorStatusAlarm();
        ssa.setAlarmStatus("0");
        ssa.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<SensorStatusAlarm> select = sensorStatusAlarmMapper.select(ssa);
        if(select.size()>0){
            //发送回执信息
            sendAlarmReceipt("07",result,"00");
            return;
        }

        SensorStatusAlarm sensorStatusAlarm=new SensorStatusAlarm();
        sensorStatusAlarm.setControlUnitCode("" + getHevToInt(getByets(dataByets, 0, 4)));
        sensorStatusAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        sensorStatusAlarm.setCheckLocaleKilometre("K"+getHevToInt(getByets(dataByets, 4, 8))/1000+"+"+getHevToInt(getByets(dataByets, 4, 8))%1000);

        sensorStatusAlarm.setCheckLocaleType(CRCUtil.byteToHex(dataByets[8]));
        sensorStatusAlarm.setSensorCommunicationStatus(CRCUtil.byteToHex(dataByets[9]));
        sensorStatusAlarm.setSensorPowerSupplyStatus(CRCUtil.byteToHex(dataByets[10]));
        sensorStatusAlarm.setFaultType(CRCUtil.byteToHex(dataByets[11]));
        sensorStatusAlarm.setReserve1("" + getHevToInt(getByets(dataByets, dataByets.length - 16, dataByets.length - 15)));
        sensorStatusAlarm.setReserve2("" + getHevToInt(getByets(dataByets, dataByets.length - 15, dataByets.length - 14)));
        String s3 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
        String s = AsciiStringToHex.convertHexToString(s3);
        Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
        sensorStatusAlarm.setDate(yyyyMMddHHmmss);
        sensorStatusAlarm.setId(UUIDHelper.getUUIDStr());
        sensorStatusAlarm.setData(ms);
        sensorStatusAlarm.setVideoUpStatus("0");
        sensorStatusAlarm.setAlarmStatus("0");
        int insert = sensorStatusAlarmMapper.insert(sensorStatusAlarm);
        if (insert > 0) {
            logger.info("保存传感器告警开始数据成功");
            //发送回执信息
            sendAlarmReceipt("09",result,"00");
        }else{
            sendAlarmReceipt("09",result,"01");
        }
    }

    private void foreignBodyAlarm(byte[] dataByets, String ms, String result) {
        //先判断是否存在相同公里标和告警时间的   有直接返回 没有再继续
        ForeignBodyAlarm fAlarm = new ForeignBodyAlarm();
        fAlarm.setDate(DateUtils.parseDate(AsciiStringToHex.convertHexToString(CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length))), "yyyyMMddHHmmss"));
        fAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<ForeignBodyAlarm> selectOne = foreignBodyAlarmMapper.select(fAlarm);
        if(selectOne.size()>0){
            //发送回执信息
            sendAlarmReceipt("07",result,"00");
            return;
        }
        //判断现在是否有正在告警的   有直接返回 没有 保存数据
        ForeignBodyAlarm ssa = new ForeignBodyAlarm();
        ssa.setAlarmStatus("0");
        ssa.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<ForeignBodyAlarm> select = foreignBodyAlarmMapper.select(ssa);
        if(select.size()>0){
            //发送回执信息
            sendAlarmReceipt("07",result,"00");
            return;
        }


        ForeignBodyAlarm foreignBodyAlarm=new ForeignBodyAlarm();
        foreignBodyAlarm.setControlUnitCode("" + getHevToInt(getByets(dataByets, 0, 4)));
        foreignBodyAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        foreignBodyAlarm.setCheckLocaleKilometre("K"+getHevToInt(getByets(dataByets, 4, 8))/1000+"+"+getHevToInt(getByets(dataByets, 4, 8))%1000);

        foreignBodyAlarm.setGrid1RelayStatus(CRCUtil.byteToHex(dataByets[8]));
        foreignBodyAlarm.setGrid2RelayStatus(CRCUtil.byteToHex(dataByets[9]));
        foreignBodyAlarm.setUpTemporaryRelayStatus(CRCUtil.byteToHex(dataByets[10]));
        foreignBodyAlarm.setDownTemporaryRelayStatus(CRCUtil.byteToHex(dataByets[11]));
        foreignBodyAlarm.setSceneRecoveryRelayStatus(CRCUtil.byteToHex(dataByets[12]));
        foreignBodyAlarm.setDispatchRecoveryRelayStatus(CRCUtil.byteToHex(dataByets[13]));
        foreignBodyAlarm.setGrid1ExperimentRelayStatus(CRCUtil.byteToHex(dataByets[14]));
        foreignBodyAlarm.setGrid2ExperimentRelayStatus(CRCUtil.byteToHex(dataByets[15]));
        foreignBodyAlarm.setUpControlRelayStatus(CRCUtil.byteToHex(dataByets[16]));
        foreignBodyAlarm.setDispatchControlRelayStatus(CRCUtil.byteToHex(dataByets[17]));
        foreignBodyAlarm.setAlarmRelayStatus(CRCUtil.byteToHex(dataByets[18]));
        long hevToInt = getHevToInt(getByets(dataByets, 19, 21));
        foreignBodyAlarm.setInfluenceDataLength("" + hevToInt);
        String s1 = CRCUtil.Bytes2HexString(getByets(dataByets, 21, 21+(int)hevToInt));
        foreignBodyAlarm.setInfluenceInterval("" + AsciiStringToHex.convertUTF8ToString(s1));
        long hevToInt1 = getHevToInt(getByets(dataByets, 21+(int)hevToInt, 21+(int)hevToInt+2));
        foreignBodyAlarm.setTrackDataLength(hevToInt1+"");
        String s2 = CRCUtil.Bytes2HexString(getByets(dataByets, 21+(int)hevToInt+2, 21+(int)hevToInt+2+(int)hevToInt1));
        foreignBodyAlarm.setTrackInterval("" + AsciiStringToHex.convertUTF8ToString(s2));
        foreignBodyAlarm.setAlarmSign(CRCUtil.byteToHex(dataByets[dataByets.length - 17]));
        foreignBodyAlarm.setReserve1("" + getHevToInt(getByets(dataByets, dataByets.length - 16, dataByets.length - 15)));
        foreignBodyAlarm.setReserve2("" + getHevToInt(getByets(dataByets, dataByets.length - 15, dataByets.length - 14)));
        String s3 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
        String s = AsciiStringToHex.convertHexToString(s3);
        Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
        foreignBodyAlarm.setDate(yyyyMMddHHmmss);
        foreignBodyAlarm.setId(UUIDHelper.getUUIDStr());
        foreignBodyAlarm.setData(ms);
        foreignBodyAlarm.setVideoUpStatus("0");
        foreignBodyAlarm.setAlarmStatus("0");
        int insert = foreignBodyAlarmMapper.insert(foreignBodyAlarm);



        if (insert > 0) {
            logger.info("保存异物告警开始数据成功");
            //发送回执信息
            sendAlarmReceipt("07",result,"00");
        }else{
            sendAlarmReceipt("07",result,"01");
        }
    }

    private void snowAararm(byte[] dataByets, String ms, String result) {

        SnowAlarm sAlarm = new SnowAlarm();
        sAlarm.setDate(DateUtils.parseDate(AsciiStringToHex.convertHexToString(CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length))), "yyyyMMddHHmmss"));
        sAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<SnowAlarm> selectOne = snowAlarmMapper.select(sAlarm);
        if(selectOne.size()>0){
            //发送回执信息
            sendAlarmReceipt("07",result,"00");
            return;
        }

        SnowAlarm ssa = new SnowAlarm();
        ssa.setAlarmStatus("0");
        ssa.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<SnowAlarm> select = snowAlarmMapper.select(ssa);
        for (SnowAlarm sensorStatusAlarm : select) {
            if(!sensorStatusAlarm.getAlarmLevel().equals(CRCUtil.byteToHex(dataByets[8]))){
                SnowAlarm sa = new SnowAlarm();
                sa.setAlarmStatus("1");
                sa.setRelieveTime(DateUtils.parseDate(AsciiStringToHex.convertHexToString(CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length))), "yyyyMMddHHmmss"));

                Example example = new Example(SnowAlarm.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("id", sensorStatusAlarm.getId());
                int i=snowAlarmMapper.updateByExampleSelective(sa,example);
                if(i>0){
                    addVideoTask(sensorStatusAlarm.getCheckLocaleCode(),DateUtils.getDate(sensorStatusAlarm.getDate(),"yyyy-MM-dd HH:mm:ss"),DateUtils.getDate(sa.getRelieveTime(),"yyyy-MM-dd HH:mm:ss"));

                }
            }else{
                //发送回执信息
                sendAlarmReceipt("07",result,"00");
                return;
            }
        }

        SnowAlarm snowAlarm =new SnowAlarm();
        snowAlarm.setControlUnitCode("" + getHevToInt(getByets(dataByets, 0, 4)));
        snowAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        snowAlarm.setCheckLocaleKilometre("K"+getHevToInt(getByets(dataByets, 4, 8))/1000+"+"+getHevToInt(getByets(dataByets, 4, 8))%1000);

        snowAlarm.setAlarmLevel(CRCUtil.byteToHex(dataByets[8]));
        snowAlarm.setSnowDeep("" + getHevToInt(getByets(dataByets, 9, 11)));
        snowAlarm.setSnowDeepBenchmark("" + getHevToInt(getByets(dataByets, 11, 13)));
        snowAlarm.setSnowDeepActualCheck("" + getHevToInt(getByets(dataByets, 13, 15)));
        snowAlarm.setSpeedLimit("" + getHevToInt(getByets(dataByets, 15, 17)));
        snowAlarm.setSpeedLimitSectionNumber("" + getHevToInt(getByets(dataByets, 17, 18)));
        snowAlarm.setSpeedLimitSectionBegin("" + getHevToInt(getByets(dataByets, 18, 20)));
        snowAlarm.setSpeedLimitSectionEnd("" + getHevToInt(getByets(dataByets, 20, 24)));
        snowAlarm.setDataLength("" + getHevToInt(getByets(dataByets, 24, 28)));
        String s1 = CRCUtil.Bytes2HexString(getByets(dataByets, 28, dataByets.length - 19));
        snowAlarm.setInfluenceInterval("" + AsciiStringToHex.convertUTF8ToString(s1));
        snowAlarm.setBranchLineCode("" + getHevToInt(getByets(dataByets, dataByets.length - 19, dataByets.length - 18)));
        snowAlarm.setReserve1("" + getHevToInt(getByets(dataByets, dataByets.length - 18, dataByets.length - 16)));
        snowAlarm.setReserve2("" + getHevToInt(getByets(dataByets, dataByets.length - 16, dataByets.length - 14)));
        Date yyyyMMddHHmmss = DateUtils.parseDate(AsciiStringToHex.convertHexToString(CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length))), "yyyyMMddHHmmss");
        snowAlarm.setDate(yyyyMMddHHmmss);
        snowAlarm.setId(UUIDHelper.getUUIDStr());
        snowAlarm.setData(ms);
        snowAlarm.setVideoUpStatus("0");
        snowAlarm.setAlarmStatus("0");
        int insert = snowAlarmMapper.insert(snowAlarm);
        if (insert > 0) {
            logger.info("保存雪深告警开始数据成功");
            //发送回执信息
            sendAlarmReceipt("07",result,"00");
        }else{
            sendAlarmReceipt("07",result,"01");
        }
    }

    private void rainAlarm(byte[] dataByets, String ms, String result) {

        RainAlarm rAlarm = new RainAlarm();
        rAlarm.setDate(DateUtils.parseDate(AsciiStringToHex.convertHexToString(CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length))), "yyyyMMddHHmmss"));
        rAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<RainAlarm> selectOne = rainAlarmMapper.select(rAlarm);
        if(selectOne.size()>0){
            //发送回执信息
            sendAlarmReceipt("07",result,"00");
            return;
        }

        RainAlarm ssa = new RainAlarm();
        ssa.setAlarmStatus("0");
        ssa.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<RainAlarm> select = rainAlarmMapper.select(ssa);
        for (RainAlarm sensorStatusAlarm : select) {
            if(!sensorStatusAlarm.getAlarmLevel().equals(CRCUtil.byteToHex(dataByets[8]))){
                RainAlarm sa = new RainAlarm();
                sa.setAlarmStatus("1");
                sa.setRelieveTime(DateUtils.parseDate(AsciiStringToHex.convertHexToString(CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length))), "yyyyMMddHHmmss"));

                Example example = new Example(RainAlarm.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("id", sensorStatusAlarm.getId());
                int i=rainAlarmMapper.updateByExampleSelective(sa,example);
                if(i>0){
                    addVideoTask(sensorStatusAlarm.getCheckLocaleCode(),DateUtils.getDate(sensorStatusAlarm.getDate(),"yyyy-MM-dd HH:mm:ss"),DateUtils.getDate(sa.getRelieveTime(),"yyyy-MM-dd HH:mm:ss"));

                }
            }else{
                //发送回执信息
                sendAlarmReceipt("07",result,"00");
                return;
            }
        }


        RainAlarm rainAlarm=new RainAlarm();
        rainAlarm.setControlUnitCode("" + getHevToInt(getByets(dataByets, 0, 4)));
        rainAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        rainAlarm.setCheckLocaleKilometre("K"+getHevToInt(getByets(dataByets, 4, 8))/1000+"+"+getHevToInt(getByets(dataByets, 4, 8))%1000);
        rainAlarm.setAlarmLevel(CRCUtil.byteToHex(dataByets[8]));
        rainAlarm.setTotalRain("" + getHevToInt(getByets(dataByets, 9, 11)));
        rainAlarm.setMinuteRain("" + getHevToInt(getByets(dataByets, 11, 13)));
        rainAlarm.setMinute10Rain("" + getHevToInt(getByets(dataByets, 13, 15)));
        rainAlarm.setHourRain("" + getHevToInt(getByets(dataByets, 15, 17)));
        rainAlarm.setHour24Rain("" + getHevToInt(getByets(dataByets, 17, 19)));
        rainAlarm.setContinuityRain("" + getHevToInt(getByets(dataByets, 19, 21)));
        rainAlarm.setSpeedLimit("" + getHevToInt(getByets(dataByets, 21, 23)));
        rainAlarm.setSpeedLimitSectionNumber("" + getHevToInt(getByets(dataByets, 23, 24)));
        rainAlarm.setSpeedLimitSectionBegin("" + getHevToInt(getByets(dataByets, 24, 28)));
        rainAlarm.setSpeedLimitSectionEnd("" + getHevToInt(getByets(dataByets, 28, 32)));
        rainAlarm.setDataLength("" + getHevToInt(getByets(dataByets, 32, 34)));
        String s1 = CRCUtil.Bytes2HexString(getByets(dataByets, 34, dataByets.length - 19));
        rainAlarm.setInfluenceInterval("" + AsciiStringToHex.convertUTF8ToString(s1));
        rainAlarm.setBranchLineCode("" + getHevToInt(getByets(dataByets, dataByets.length - 19, dataByets.length - 18)));
        rainAlarm.setReserve1("" + getHevToInt(getByets(dataByets, dataByets.length - 18, dataByets.length - 16)));
        rainAlarm.setReserve2("" + getHevToInt(getByets(dataByets, dataByets.length - 16, dataByets.length - 14)));
        String s3 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
        String s = AsciiStringToHex.convertHexToString(s3);
        Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
        rainAlarm.setDate(yyyyMMddHHmmss);
        rainAlarm.setId(UUIDHelper.getUUIDStr());
        rainAlarm.setData(ms);
        rainAlarm.setVideoUpStatus("0");
        rainAlarm.setAlarmStatus("0");
        int insert = rainAlarmMapper.insert(rainAlarm);
        if (insert > 0) {
            logger.info("保存雨量告警开始数据成功");
            //发送回执信息
            sendAlarmReceipt("07",result,"00");
        }else{
            sendAlarmReceipt("07",result,"01");
        }
    }

    private void windAlarm(byte[] dataByets, String ms,String alarmType) {
        WindSpeedAlarm wAlarm = new WindSpeedAlarm();
        wAlarm.setDate(DateUtils.parseDate(AsciiStringToHex.convertHexToString(CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length))), "yyyyMMddHHmmss"));
        wAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<WindSpeedAlarm> selectOne = windSpeedAlarmMapper.select(wAlarm);
        if(selectOne.size()>0){
            //发送回执信息
            sendAlarmReceipt("07",alarmType,"00");
            return;
        }

        WindSpeedAlarm ssa = new WindSpeedAlarm();
        ssa.setAlarmStatus("0");
        ssa.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        List<WindSpeedAlarm> select = windSpeedAlarmMapper.select(ssa);
        for (WindSpeedAlarm sensorStatusAlarm : select) {
            if(!sensorStatusAlarm.getAlarmLevel().equals(CRCUtil.byteToHex(dataByets[8]))){
                WindSpeedAlarm sa = new WindSpeedAlarm();
                sa.setAlarmStatus("1");
                sa.setRelieveTime(DateUtils.parseDate(AsciiStringToHex.convertHexToString(CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length))), "yyyyMMddHHmmss"));

                Example example = new Example(WindSpeedAlarm.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("id", sensorStatusAlarm.getId());
                int i = windSpeedAlarmMapper.updateByExampleSelective(sa, example);
                if(i>0){
                    addVideoTask(sensorStatusAlarm.getCheckLocaleCode(),DateUtils.getDate(sensorStatusAlarm.getDate(),"yyyy-MM-dd HH:mm:ss"),DateUtils.getDate(sa.getRelieveTime(),"yyyy-MM-dd HH:mm:ss"));

                }
            }else{
                //发送回执信息
                sendAlarmReceipt("07",alarmType,"00");
                return;
            }
        }

        String s2 = CRCUtil.bytesToHex(dataByets);
        WindSpeedAlarm windSpeedAlarm = new WindSpeedAlarm();
        windSpeedAlarm.setControlUnitCode("" + getHevToInt(getByets(dataByets, 0, 4)));
        windSpeedAlarm.setCheckLocaleCode("" + getHevToInt(getByets(dataByets, 4, 8)));
        windSpeedAlarm.setCheckLocaleKilometre("K"+getHevToInt(getByets(dataByets, 4, 8))/1000+"+"+getHevToInt(getByets(dataByets, 4, 8))%1000);
        windSpeedAlarm.setAlarmLevel(CRCUtil.byteToHex(dataByets[8]));
        windSpeedAlarm.setAlarmWindSpeed("" + getHevToInt(getByets(dataByets, 9, 11)));
        windSpeedAlarm.setAlarmWindDirection("" + getHevToInt(getByets(dataByets, 11, 13)));
        windSpeedAlarm.setWindSpeed1("" + getHevToInt(getByets(dataByets, 13, 15)));
        windSpeedAlarm.setWindDirection1("" + getHevToInt(getByets(dataByets, 15, 17)));
        windSpeedAlarm.setWindSpeed2("" + getHevToInt(getByets(dataByets, 17, 19)));
        windSpeedAlarm.setWindDirection2("" + getHevToInt(getByets(dataByets, 19, 21)));
        windSpeedAlarm.setSpeedLimit("" + getHevToInt(getByets(dataByets, 21, 23)));
        windSpeedAlarm.setSpeedLimitSectionNumber("" + getHevToInt(getByets(dataByets, 23, 24)));
        windSpeedAlarm.setSpeedLimitSectionBegin("" + getHevToInt(getByets(dataByets, 24, 28)));
        windSpeedAlarm.setSpeedLimitSectionEnd("" + getHevToInt(getByets(dataByets, 28, 32)));
        windSpeedAlarm.setDataLength("" + getHevToInt(getByets(dataByets, 32, 34)));
        String s1 = CRCUtil.Bytes2HexString(getByets(dataByets, 34, dataByets.length - 19));
        windSpeedAlarm.setInfluenceInterval("" + AsciiStringToHex.convertUTF8ToString(s1));
        windSpeedAlarm.setBranchLineCode("" + getHevToInt(getByets(dataByets, dataByets.length - 19, dataByets.length - 18)));
        windSpeedAlarm.setReserve1("" + getHevToInt(getByets(dataByets, dataByets.length - 18, dataByets.length - 16)));
        windSpeedAlarm.setReserve2("" + getHevToInt(getByets(dataByets, dataByets.length - 16, dataByets.length - 14)));
        String s3 = CRCUtil.Bytes2HexString(getByets(dataByets, dataByets.length - 14, dataByets.length));
        String s = AsciiStringToHex.convertHexToString(s3);
        Date yyyyMMddHHmmss = DateUtils.parseDate(s, "yyyyMMddHHmmss");
        windSpeedAlarm.setDate(yyyyMMddHHmmss);
        windSpeedAlarm.setId(UUIDHelper.getUUIDStr());
        windSpeedAlarm.setData(ms);
        windSpeedAlarm.setVideoUpStatus("0");
        windSpeedAlarm.setAlarmStatus("0");
        int insert = windSpeedAlarmMapper.insert(windSpeedAlarm);
        if (insert > 0) {



            logger.info("保存风速告警开始数据成功");
            //发送回执信息
            sendAlarmReceipt("07",alarmType,"00");
        }else{
            sendAlarmReceipt("07",alarmType,"01");
        }
    }

    /**
     * 截取byte数组
     *
     * @param dataByets 源数组
     * @param kaishi    开始截取下标
     * @param jieshu    结束下标
     * @return byte
     */
    private byte[] getByets(byte[] dataByets, int kaishi, int jieshu) {
        byte[] bytes = Arrays.copyOfRange(dataByets, kaishi, jieshu); //获取报警数据
        return bytes;
    }

    private long getHevToInt(byte[] dataByets) {
        String s = CRCUtil.bytesToHex(dataByets);
        return Long.parseLong(s, 16);
    }

    @Override
    public void sendKeepAlive() {
        if (BootNettyChannelInboundHandlerAdapter.ctx != null && BootNettyChannelInboundHandlerAdapter.boo) {
            StringBuffer fb = new StringBuffer();
            fb.append(keepAliveNum);
            keepAliveNum = AsciiStringToHex.HexTo10(keepAliveNum);
            String vsersion = Properties.getVersion();
            fb.append(vsersion);
            fb.append("64");
            String Hex = Integer.toHexString(23);
            fb.append("00" + Hex);
            fb.append("00");
            String yyyyMMddHHmmss = DateUtils.getDate(new Date(), "yyyyMMddHHmmss");
            String ymd = AsciiStringToHex.convertStringToHex(yyyyMMddHHmmss);
            fb.append(ymd);
            byte[] bytes3 = CRCUtil.toByteArray(fb.toString());
            int crc1021 = CRCUtil.getCRC1021(bytes3, 23);
            String hexStr = Integer.toHexString(crc1021);
            if(hexStr.length()<4){
                if(hexStr.length()==1){
                    hexStr="000"+hexStr;
                }else if(hexStr.length()==2){
                    hexStr="00"+hexStr;
                }else if(hexStr.length()==3){
                    hexStr="0"+hexStr;
                }
            }
            fb.append(hexStr);
          /*  logger.info("10校验码 ："+crc1021);
            logger.info("16校验码 ："+Integer.toHexString(crc1021));*/
            byte[] bytes = CRCUtil.DecodeData(CRCUtil.toByteArray(fb.toString()));
            String s = CRCUtil.Bytes2HexString(bytes);
            StringBuffer fber = new StringBuffer();
            fber.append("c0c0");
            fber.append(s);
            fber.append("c0c0");
            if(Integer.parseInt(keepAliveNum,16)%1000==0){
                addLog(DateUtils.parseDate(yyyyMMddHHmmss,"yyyyMMddHHmmss"),"发送心跳",fber.toString());
            }

           logger.info("心跳包命令" + fber.toString());

            BootNettyChannelInboundHandlerAdapter.ctx.writeAndFlush(Unpooled.copiedBuffer(CRCUtil.toByteArray(fber.toString())));
        }
    }
    private void sendKeepAliveReceipt() {
        if (BootNettyChannelInboundHandlerAdapter.ctx != null && BootNettyChannelInboundHandlerAdapter.boo) {
            StringBuffer fb = new StringBuffer();
            fb.append(keepAliveNumReceipt);
            keepAliveNumReceipt = AsciiStringToHex.HexTo10(keepAliveNumReceipt);
            String vsersion = Properties.getVersion();
            fb.append(vsersion);
            fb.append("65");
            String Hex = Integer.toHexString(23);
            fb.append("00" + Hex);
            fb.append("00");
            String yyyyMMddHHmmss = DateUtils.getDate(new Date(), "yyyyMMddHHmmss");
            String ymd = AsciiStringToHex.convertStringToHex(yyyyMMddHHmmss);
            fb.append(ymd);
            byte[] bytes3 = CRCUtil.toByteArray(fb.toString());
            int crc1021 = CRCUtil.getCRC1021(bytes3, 23);
            String hexStr = Integer.toHexString(crc1021);
            if(hexStr.length()<4){
                if(hexStr.length()==1){
                    hexStr="000"+hexStr;
                }else if(hexStr.length()==2){
                    hexStr="00"+hexStr;
                }else if(hexStr.length()==3){
                    hexStr="0"+hexStr;
                }
            }
            fb.append(hexStr);
            byte[] bytes = CRCUtil.DecodeData(CRCUtil.toByteArray(fb.toString()));
            String s = CRCUtil.Bytes2HexString(bytes);
            StringBuffer fber = new StringBuffer();
            fber.append("c0c0");
            fber.append(s);
            fber.append("c0c0");

              BootNettyChannelInboundHandlerAdapter.ctx.writeAndFlush(Unpooled.copiedBuffer(CRCUtil.toByteArray(fber.toString())));
        }
    }

    @Override
    public Boolean sendRegister() {



        StringBuffer fb = new StringBuffer();
        fb.append(registerNum);
        registerNum = AsciiStringToHex.HexTo10(registerNum);
        byte[] bytes2 = registerNum.getBytes();

        String vsersion = Properties.getVersion();
        fb.append(vsersion);
        fb.append("66");
        String nameAndPassword = Properties.getNameAndPassword();

        String asciiName = AsciiStringToHex.convertStringToHex(nameAndPassword);

        byte[] bytes1 = CRCUtil.hexStrToBinaryStr(asciiName);

        String Hex = Integer.toHexString(22 + bytes1.length);

        fb.append("00" + Hex);
        fb.append(asciiName);

        String yyyyMMddHHmmss = DateUtils.getDate(new Date(), "yyyyMMddHHmmss");
        String ymd = AsciiStringToHex.convertStringToHex(yyyyMMddHHmmss);
        fb.append(ymd);
        byte[] bytes3 = CRCUtil.toByteArray(fb.toString());
        int crc1021 = CRCUtil.getCRC1021(bytes3, 22 + bytes1.length);
        String hexStr = Integer.toHexString(crc1021);
        if(hexStr.length()<4){
            if(hexStr.length()==1){
                hexStr="000"+hexStr;
            }else if(hexStr.length()==2){
                hexStr="00"+hexStr;
            }else if(hexStr.length()==3){
                hexStr="0"+hexStr;
            }
        }
        fb.append(hexStr);
        byte[] bytes = CRCUtil.DecodeData(CRCUtil.toByteArray(fb.toString()));
        String s = CRCUtil.Bytes2HexString(bytes);
        StringBuffer fber = new StringBuffer();
        fber.append("c0c0");
        fber.append(s);
        fber.append("c0c0");

        String strList = CRCUtil.getStrList(fber.toString(), 2);
        logger.info("注册命令；" + strList);

        BootNettyChannelInboundHandlerAdapter.ctx.writeAndFlush(Unpooled.copiedBuffer(CRCUtil.toByteArray(fber.toString())));
        return true;
    }

    private void sendAlarmReceipt(String type,String alarmType,String res) {
        if (BootNettyChannelInboundHandlerAdapter.ctx != null && BootNettyChannelInboundHandlerAdapter.boo) {
            StringBuffer fb = new StringBuffer();
            fb.append(AlarmReceipt);
            AlarmReceipt = AsciiStringToHex.HexTo10(AlarmReceipt);
            String vsersion = Properties.getVersion();
            fb.append(vsersion);
            fb.append(type);
            String Hex = Integer.toHexString(24);
            fb.append("00" + Hex);
            fb.append(alarmType);
            fb.append(res);
            String yyyyMMddHHmmss = DateUtils.getDate(new Date(), "yyyyMMddHHmmss");
            String ymd = AsciiStringToHex.convertStringToHex(yyyyMMddHHmmss);
            fb.append(ymd);
            byte[] bytes1 = CRCUtil.toByteArray(fb.toString());
            int crc1021 = CRCUtil.getCRC1021(bytes1, 23);
            String hexStr = Integer.toHexString(crc1021);
            if(hexStr.length()<4){
                if(hexStr.length()==1){
                    hexStr="000"+hexStr;
                }else if(hexStr.length()==2){
                    hexStr="00"+hexStr;
                }else if(hexStr.length()==3){
                    hexStr="0"+hexStr;
                }
            }
            fb.append(hexStr);
            byte[] bytes = CRCUtil.DecodeData(CRCUtil.toByteArray(fb.toString()));
            String s = CRCUtil.Bytes2HexString(bytes);
            StringBuffer fber = new StringBuffer();
            fber.append("c0c0");
            fber.append(s);
            fber.append("c0c0");
            logger.info("告警类回执 帧类型" + type+" 告警类型："+alarmType+"回执结果： "+res);
            logger.info("告警回执命令："+fber.toString());

            BootNettyChannelInboundHandlerAdapter.ctx.writeAndFlush(Unpooled.copiedBuffer(CRCUtil.toByteArray(fber.toString())));
        }
    }

    @Override
    public void addTask() {


        WindSpeedAlarm windSpeedAlarm = new WindSpeedAlarm();
        //查询所有 告警状态是1 结束状态  添加任务表状态是0 没有添加的  风雨雪异物 告警信息
        List<WindSpeedAlarm> select = windSpeedAlarmMapper.selectAllByStatus(windSpeedAlarm);
        if(select.size()>0){
            for (WindSpeedAlarm speedAlarm : select) {
                VideoFile videoFile=new VideoFile();
                videoFile.setIpcid(speedAlarm.getVideoCode());
                videoFile.setKssj(DateUtils.getDate(speedAlarm.getDate(),"yyyy-MM-dd HH:mm:ss"));
                videoFile.setJssj(DateUtils.getDate(speedAlarm.getRelieveTime(),"yyyy-MM-dd HH:mm:ss"));
                videoFile.setStatus("0");
                int i = videoFileMapper.insertSelective(videoFile);//添加到任务表里
                if(i>0){
                    //根据表名更改表数据 为 已上传状态
                    switch (speedAlarm.getTableName()){
                        case "wind":{
                            WindSpeedAlarm sa = new WindSpeedAlarm();
                            sa.setVideoUpStatus("1");
                            Example example = new Example(WindSpeedAlarm.class);
                            Example.Criteria criteria = example.createCriteria();
                            criteria.andEqualTo("id", speedAlarm.getId());
                            windSpeedAlarmMapper.updateByExampleSelective(sa,example);
                        }
                            break;
                        case "rain":{

                            RainAlarm sa = new RainAlarm();
                            sa.setVideoUpStatus("1");
                            Example example = new Example(RainAlarm.class);
                            Example.Criteria criteria = example.createCriteria();
                            criteria.andEqualTo("id", speedAlarm.getId());
                            rainAlarmMapper.updateByExampleSelective(sa,example);
                        }
                            break;
                        case "foreign":{

                            ForeignBodyAlarm sa = new ForeignBodyAlarm();
                            sa.setVideoUpStatus("1");
                            Example example = new Example(ForeignBodyAlarm.class);
                            Example.Criteria criteria = example.createCriteria();
                            criteria.andEqualTo("id", speedAlarm.getId());
                            foreignBodyAlarmMapper.updateByExampleSelective(sa,example);
                        }
                            break;
                        case "snow":{
                            SnowAlarm sa = new SnowAlarm();
                            sa.setVideoUpStatus("1");
                            Example example = new Example(SnowAlarm.class);
                            Example.Criteria criteria = example.createCriteria();
                            criteria.andEqualTo("id", speedAlarm.getId());
                            snowAlarmMapper.updateByExampleSelective(sa,example);
                        }
                            break;
                        default:
                            logger.error("查询没有添加到任务表里的数据出错");
                            break;
                    }

                }

            }
        }
    }
}
