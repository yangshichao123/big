package com.data.big.controller;

import com.data.big.util.Properties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/fdCon")
public class ControllerTest {

    //@RequestMapping(value = "/queryCurrentDayWarningData" , method = RequestMethod.POST)
    @PostMapping("/queryCurrentDayWarningData")
    @ResponseBody
    public Map<String,Object> queryCurrentDayWarningData(@RequestParam Map<String ,Object> map) {
        Map<String ,Object> mm=new HashMap<>();
        mm.put("status","200");
        String  beginTime =(String)map.get("beginTime");
        String  endTime =(String)map.get("endTime");
        String  ipcid =(String)map.get("ipcid");
        String  type =(String)map.get("type");
        String  starttime =(String)map.get("starttime");
        String  endtime =(String)map.get("endtime");
        System.out.println("ipcid: "+ipcid+" type: "+type+" starttime "+starttime+" endtime "+endtime);
        Map<String,Object> mO=new HashMap<>();
        Map<String,Object> mO1=new HashMap<>();
        mO1.put("STATUS","4");
        mO1.put("DeptCode","00P000000000000000000000000000XiaHuaYuanBei006688");
        mO1.put("createTime","1600838113000");
        mO1.put("img_path","http://localhost/data/timg.jpg");
        mO1.put("ip","10.234.61.10");
        mO1.put("id","2");
        mO1.put("cameraName","测试摄像机");
        mO1.put("vid_path","http://localhost/data/2-5 Vuejs介绍.avi");

        List list =new ArrayList();
        List list1 =new ArrayList();
        list.add(mO1);
        mO.put("yxbj",list);
        mO.put("pdcd",list1);
        mO.put("dbrq",list1);
        mO.put("rynx",list1);
        mO.put("klmd",list1);
        mO.put("cztdnx",list1);
        mm.put("data",mO);

        return mm;
    } @RequestMapping("/queryCurrentDayWarningData2")
    @ResponseBody
    public Map<String,Object> queryCurrentDayWarningData2(@RequestBody Map<String ,Object> map) {
        Map<String ,Object> mm=new HashMap<>();
        mm.put("status","200");
        String  beginTime =(String)map.get("beginTime");
        String  endTime =(String)map.get("endTime");
        String  ipcid =(String)map.get("ipcid");
        String  type =(String)map.get("type");
        String  starttime =(String)map.get("starttime");
        String  endtime =(String)map.get("endtime");
        System.out.println("ipcid: "+ipcid+" type: "+type+" starttime "+starttime+" endtime "+endtime);
        Map<String,Object> mO=new HashMap<>();
        Map<String,Object> mO1=new HashMap<>();
        mO1.put("STATUS","4");
        mO1.put("DeptCode","00P000000000000000000000000000XiaHuaYuanBei006688");
        mO1.put("createTime","1600838113000");
        mO1.put("img_path","http://localhost/data/timg.jpg");
        mO1.put("ip","10.234.61.10");
        mO1.put("id","2");
        mO1.put("cameraName","测试摄像机");
        mO1.put("vid_path","http://localhost/data/2-5 Vuejs介绍.avi");

        List list =new ArrayList();
        List list1 =new ArrayList();
        list.add(mO1);
        mO.put("yxbj",list);
        mO.put("pdcd",list1);
        mO.put("dbrq",list1);
        mO.put("rynx",list1);
        mO.put("klmd",list1);
        mO.put("cztdnx",list1);
        mm.put("data",mO);

        return mm;
    }
 @RequestMapping("/queryCurrentDayWarningData1")
    @ResponseBody
    public Map<String,Object> queryCurrentDayWarningData1(String ipcid,String type,String starttime,String endtime) {
        Map<String ,Object> mm=new HashMap<>();
        mm.put("status","200");
        System.out.println("ipcid: "+ipcid+" type: "+type+" starttime "+starttime+" endtime "+endtime);
        Map<String,Object> mO=new HashMap<>();
        Map<String,Object> mO1=new HashMap<>();
        mO1.put("STATUS","4");
        mO1.put("DeptCode","00P000000000000000000000000000XiaHuaYuanBei006688");
        mO1.put("createTime","1600838113000");
        mO1.put("img_path","http://localhost/data/timg.jpg");
        mO1.put("ip","10.234.61.10");
        mO1.put("id","2");
        mO1.put("cameraName","测试摄像机");
        mO1.put("vid_path","http://localhost/data/2-5 Vuejs介绍.avi");

        List list =new ArrayList();
        List list1 =new ArrayList();
        list.add(mO1);
        mO.put("yxbj",list);
        mO.put("pdcd",list1);
        mO.put("dbrq",list1);
        mO.put("rynx",list1);
        mO.put("klmd",list1);
        mO.put("cztdnx",list1);
        mm.put("data",mO);

        return mm;
    }

    @RequestMapping("/GetCameraAlarm")
    @ResponseBody
    public Map<String,Object> GetCameraAlarm(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("alarm_id", "测试名称");
        map.put("alarm_time", "测试名称");
        map.put("alarm_level", "枪击");
        map.put("alarm_type", "1");
        map.put("alarm_status", "2");
        map.put("description", "3");

        Map<String,String> map2 = new HashMap<>();
        list.add(map2);
        map2.put("device_id", "12345");
        map2.put("alarm_id", "测试名称");
        map2.put("alarm_time", "1");
        map2.put("alarm_level", "2");
        map2.put("alarm_type", "枪击");
        map2.put("alarm_status", "3");
        map2.put("description", "3");

        return reMap;
    }

    @RequestMapping("/GetServerAlarm")
    @ResponseBody
    public Map<String,Object> GetServerAlarm(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("alarm_id", "测试名称");
        map.put("alarm_time", "测试名称");
        map.put("alarm_level", "枪击");
        map.put("alarm_type", "1");
        map.put("alarm_status", "2");
        map.put("description", "3");

        Map<String,String> map2 = new HashMap<>();
        list.add(map2);
        map2.put("device_id", "12345");
        map2.put("alarm_id", "测试名称");
        map2.put("alarm_time", "1");
        map2.put("alarm_level", "2");
        map2.put("alarm_type", "枪击");
        map2.put("alarm_status", "3");
        map2.put("description", "3");

        return reMap;
    }

    @RequestMapping("/GetDiskAlarm")
    @ResponseBody
    public Map<String,Object> GetDiskAlarm(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("alarm_id", "测试名称");
        map.put("alarm_time", "测试名称");
        map.put("alarm_level", "枪击");
        map.put("alarm_type", "1");
        map.put("alarm_status", "2");
        map.put("description", "3");

        Map<String,String> map2 = new HashMap<>();
        list.add(map2);
        map2.put("device_id", "12345");
        map2.put("alarm_id", "测试名称");
        map2.put("alarm_time", "1");
        map2.put("alarm_level", "2");
        map2.put("alarm_type", "枪击");
        map2.put("alarm_status", "3");
        map2.put("description", "3");

        return reMap;
    }

    @RequestMapping("/GetSwitchAlarm")
    @ResponseBody
    public Map<String,Object> GetSwitchAlarm(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("alarm_id", "测试名称");
        map.put("alarm_time", "测试名称");
        map.put("alarm_level", "枪击");
        map.put("alarm_type", "1");
        map.put("alarm_status", "2");
        map.put("description", "3");

        Map<String,String> map2 = new HashMap<>();
        list.add(map2);
        map2.put("device_id", "12345");
        map2.put("alarm_id", "测试名称");
        map2.put("alarm_time", "1");
        map2.put("alarm_level", "2");
        map2.put("alarm_type", "枪击");
        map2.put("alarm_status", "3");
        map2.put("description", "3");

        return reMap;
    }

    @RequestMapping("/GetIVSAlarm")
    @ResponseBody
    public Map<String,Object> GetIVSAlarm(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("alarm_id", "测试名称");
        map.put("alarm_time", "测试名称");
        map.put("alarm_level", "枪击");
        map.put("alarm_type", "1");
        map.put("alarm_status", "2");
        map.put("description", "3");

        Map<String,String> map2 = new HashMap<>();
        list.add(map2);
        map2.put("device_id", "12345");
        map2.put("alarm_id", "测试名称");
        map2.put("alarm_time", "1");
        map2.put("alarm_level", "2");
        map2.put("alarm_type", "枪击");
        map2.put("alarm_status", "3");
        map2.put("description", "3");

        return reMap;
    }

    @RequestMapping("/GetNodeInfo")
    @ResponseBody
    public Map<String,Object> GetNodeInfo(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("node_id", "12345");
        map.put("node_name", "测试名称");
        map.put("node_parent", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("node_id", "111111");
        map2.put("node_name", "测试名称");
        map2.put("node_parent", "北京局");
        return reMap;
    }

    @RequestMapping("/GetCameraInfo")
    @ResponseBody
    public Map<String,Object> GetCameraInfo(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("device_name", "测试名称");
        map.put("node_id", "北京局");
        map.put("device_type", "北京局");
        map.put("manufacturer", "北京局");
        map.put("ip_addr", "北京局");
        map.put("ip_port", "北京局");
        map.put("username", "北京局");
        map.put("password", "北京局");
        map.put("install_time", "北京局");
        map.put("affiliation", "北京局");
        map.put("up_down", "北京局");
        map.put("associated_line", "北京局");
        map.put("k_mark", "北京局");
        map.put("longitude", "北京局");
        map.put("latitude", "北京局");
        map.put("region", "北京局");
        map.put("Location_type", "北京局");
        map.put("Location_num", "北京局");
        map.put("area", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("device_id", "111111");
        map2.put("device_name", "测试名称");
        map2.put("node_id", "北京局");
        map2.put("device_type", "北京局");
        map2.put("manufacturer", "北京局");
        map2.put("ip_addr", "北京局");
        map2.put("ip_port", "北京局");
        map2.put("username", "北京局");
        map2.put("password", "北京局");
        map2.put("install_time", "北京局");
        map2.put("affiliation", "北京局");
        map2.put("up_down", "北京局");
        map2.put("associated_line", "北京局");
        map2.put("k_mark", "北京局");
        map2.put("longitude", "北京局");
        map2.put("latitude", "北京局");
        map2.put("region", "北京局");
        map2.put("Location_type", "北京局");
        map2.put("Location_num", "北京局");
        map2.put("area", "北京局");
        return reMap;
    }

    @RequestMapping("/GetServerInfo")
    @ResponseBody
    public Map<String,Object> GetServerInfo(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("device_name", "测试名称");
        map.put("device_type", "北京局");
        map.put("manufacturer", "北京局");
        map.put("ip_addr", "北京局");
        map.put("device_model", "北京局");
        map.put("location", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("device_id", "111111");
        map2.put("device_name", "测试名称");
        map2.put("device_type", "北京局");
        map2.put("manufacturer", "北京局");
        map2.put("ip_addr", "北京局");
        map2.put("device_model", "北京局");
        map2.put("location", "北京局");
        return reMap;
    }

    @RequestMapping("/GetDiskInfo")
    @ResponseBody
    public Map<String,Object> GetDiskInfo(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("device_name", "测试名称");
        map.put("device_type", "北京局");
        map.put("manufacturer", "北京局");
        map.put("ip_addr", "北京局");
        map.put("device_model", "北京局");
        map.put("location", "北京局");
        map.put("capacity", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("device_id", "111111");
        map2.put("device_name", "测试名称");
        map2.put("device_type", "北京局");
        map2.put("manufacturer", "北京局");
        map2.put("ip_addr", "北京局");
        map2.put("device_model", "北京局");
        map2.put("location", "北京局");
        map2.put("capacity", "北京局");
        return reMap;
    }

    @RequestMapping("/GetSwitchInfo")
    @ResponseBody
    public Map<String,Object> GetSwitchInfo(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("device_name", "测试名称");
        map.put("device_type", "北京局");
        map.put("manufacturer", "北京局");
        map.put("ip_addr", "北京局");
        map.put("device_model", "北京局");
        map.put("location", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("device_id", "111111");
        map2.put("device_name", "测试名称");
        map2.put("device_type", "北京局");
        map2.put("manufacturer", "北京局");
        map2.put("ip_addr", "北京局");
        map2.put("device_model", "北京局");
        map2.put("location", "北京局");
        return reMap;
    }

    @RequestMapping("/GetCameraStatus")
    @ResponseBody
    public Map<String,Object> GetCameraStatus(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("status_time", "测试名称");
        map.put("online_status", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("device_id", "111111");
        map2.put("status_time", "测试名称");
        map2.put("online_status", "北京局");
        return reMap;
    }

    @RequestMapping("/GetServerStatus")
    @ResponseBody
    public Map<String,Object> GetServerStatus(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("status_time", "测试名称");
        map.put("online_status", "北京局");
        map.put("cpu_usage", "北京局");
        map.put("cpu_temp", "北京局");
        map.put("memory_usage", "北京局");
        map.put("port-status", "北京局");
        map.put("disk_status", "北京局");
        map.put("disk_partition_usage", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("device_id", "111111");
        map2.put("status_time", "测试名称");
        map2.put("online_status", "北京局");
        map2.put("cpu_usage", "北京局");
        map2.put("cpu_temp", "北京局");
        map2.put("memory_usage", "北京局");
        map2.put("port-status", "北京局");
        map2.put("disk_status", "北京局");
        map2.put("disk_partition_usage", "北京局");
        return reMap;
    }

    @RequestMapping("/GeDiskStatus")
    @ResponseBody
    public Map<String,Object> GeDiskStatus(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("status_time", "测试名称");
        map.put("online_status", "北京局");
        map.put("cpu_usage", "北京局");
        map.put("fan_disk", "北京局");
        map.put("disk_status", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("device_id", "111111");
        map2.put("status_time", "测试名称");
        map2.put("online_status", "北京局");
        map2.put("cpu_usage", "北京局");
        map2.put("fan_disk", "北京局");
        map2.put("disk_status", "北京局");
        return reMap;
    }

    @RequestMapping("/GetSwitchStatus")
    @ResponseBody
    public Map<String,Object> GetSwitchStatus(String optUserCode, String id) {
        Map<String,Object> reMap = new HashMap<>();
        List<Map> list = new ArrayList<>();
        reMap.put("result", "success");
        reMap.put("data", list);

        Map<String,String> map = new HashMap<>();
        list.add(map);
        map.put("device_id", "12345");
        map.put("status_time", "测试名称");
        map.put("online_status", "北京局");
        map.put("memory_usage", "北京局");
        map.put("port_status", "北京局");
        map.put("cpu_usage", "北京局");
        Map<String,String> map2 = new HashMap<>();
        //list.add(map2);
        map2.put("device_id", "111111");
        map2.put("status_time", "测试名称");
        map2.put("online_status", "北京局");
        map2.put("memory_usage", "北京局");
        map2.put("port_status", "北京局");
        map2.put("cpu_usage", "北京局");
        return reMap;
    }

    String base = "D:\\test\\java\\1\\upload\\";

    @PostMapping("/save")
    public String save(MultipartFile upfile, String title, Map<String,Object> map) {

        map.put("title", title);
        if (upfile.getSize() > 0) {
            String origName = upfile.getOriginalFilename();
            String saveName = UUID.randomUUID() + origName;
            File file = new File(base + saveName);
            try {
                upfile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            File thumb = new File(base + "thumb_" + origName);

            return "success";
        }
        return "failure";
    }

    @RequestMapping(value = "/addTbale", method = RequestMethod.POST )
    @ResponseBody
    public Map addTbale(@RequestHeader("Authorization") String authorization, @RequestBody String jsonObject,@RequestBody MultipartFile files, @RequestParam("tableName") String tableName) {
        System.out.println( authorization+"       "+ jsonObject+"    "+tableName);
        return null;
    }
    @RequestMapping(value = "/addTbale1", method = RequestMethod.POST )
    @ResponseBody
    public Map addTbale1(@RequestHeader("Authorization") String authorization, @RequestParam("jsonObject") String jsonObject,@RequestParam("files") MultipartFile files) {
        System.out.println( authorization+"       "+ jsonObject+"    ");
        return null;
    }




    @RequestMapping(value = "/addFiles", method = RequestMethod.POST)
    @ResponseBody
    public Map uploadFile(@RequestHeader("Authorization") String authorization, @RequestBody MultipartFile files, @RequestParam("fid") int fid) {

        Map<String,String> map = new HashMap<>();
        System.out.println(authorization);
        System.out.print("上传文件===" + "\n");
        //判断文件是否为空
        if (files.isEmpty()) {
            map.put("stateCode", "400");
            map.put("message", "文件不存在存在");
            return map;
        }


        // 获取文件名
        String fileName = files.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");

        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: " + fileName + "\n");

        String saveFileUrl = Properties.getSaveFileUrl();
        //加个时间戳，尽量避免文件名称重复
        String path = saveFileUrl + fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径" + path + "\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            map.put("stateCode", "400");
            map.put("message", "文件已经存在");
            return map;
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            files.transferTo(dest); //保存文件
            System.out.print("保存文件路径" + path + "\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
           /* url="http://localhost:8080/images/"+fileName;//本地运行项目
            int jieguo= shiPinService.insertUrl(fileName,path,url);
            System.out.print("插入结果"+jieguo+"\n");
            System.out.print("保存的完整url===="+url+"\n");*/
            map.put("stateCode", "200");
            map.put("data", "2222222222222222222");
            map.put("message", "上传成功");
        } catch (IOException e) {
            map.put("stateCode", "403");
            map.put("message", "io异常  上传失败");
            return map;
        }

        return map;
    }

}
