package com.data.big.rsa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


public class WsTest {
    public static void main(String[] args){
        //在一个方法中连续调用多次WebService接口,每次调用前需要重置上下文
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        String keyPair=null;
        //获取公钥
         keyPair=getKeyPair(dcf);
        //重置上下文
        Thread.currentThread().setContextClassLoader(cl);
        //请求授权
        String token=auth(dcf,keyPair);
        if(token!=null && !"".equals(token)){
            //重置上下文
            Thread.currentThread().setContextClassLoader(cl);
            //获取公钥
            keyPair=getKeyPair(dcf);
            //重置上下文
            Thread.currentThread().setContextClassLoader(cl);
            //请求报警数据数据
            bigDataAlarm(dcf,token,keyPair);
        }else{
            System.out.println("未获取token");
        }

    }




    private static String getKeyPair(JaxWsDynamicClientFactory dcf){
        String result="";
        Client client = dcf.createClient("http://172.23.37.61:11021/ws/bigdata?wsdl");
        Object[] objects = new Object[0];
        try {
            objects = client.invoke("keyPair" );
            if(objects.length>0){
                //解密
                String src=DESUtil.decrypt((String) objects[0],"596F37A6(0B26)4E10{92CA}39F1D229");
                JSONObject jsonObject = JSON.parseObject(src);

                if("1".equals(jsonObject.getString("code"))){
                    result=jsonObject.getString("message");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String auth(JaxWsDynamicClientFactory dcf,String keyPair){
        String result="";
        Client client = dcf.createClient("http://172.23.37.61:11021/ws/bigdata?wsdl");
        Object[] objects = new Object[0];
        try {
            //参数进行加密
            JSONObject json = new JSONObject();
            json.put("userName","admin");
            json.put("passWord","admin");
            System.out.println("-----公钥----\n"+keyPair);
            System.out.println("-----参数----\n"+json.toJSONString());
            String denseStr=RSAUtil.publicEncrypt(json.toJSONString(),RSAUtil.getPublicKey(keyPair));
            System.out.println("-----加密结果----\n"+denseStr);
            objects = client.invoke("auth", denseStr,keyPair);
            if(objects.length>0){
                //解密
                String src=DESUtil.decrypt((String) objects[0],"596F37A6(0B26)4E10{92CA}39F1D229");
                JSONObject jsonObject = JSON.parseObject(src);
                if("1".equals(jsonObject.getString("code"))){
                    result=jsonObject.getString("message");
                    System.out.println("-----token----\n"+result);
                }else{
                    System.out.println("-----请求失败----\n"+jsonObject.getString("message"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private static void bigDataAlarm(JaxWsDynamicClientFactory dcf,String token,String keyPair){
        JSONObject json = new JSONObject();
        //路局编码
        json.put("bureauCode","C");
        //线路编码
        json.put("lineCode","30142");
        //监测点
        json.put("monitorCode","48246,715990");
        //报警级别 不写默认所有级别 可单独设置1个级别 或者 1,2,3,4 多个级别
        json.put("alarmLevel","1,2,3,4");
        //开始时间
        json.put("startTime","2018-06-24 17:06:41");
        //结束时间
        json.put("endTime","2021-08-25 14:16:56");
        //请求的数据类型 1：风报警 2：雨报警 3：雪报警 4：异物报警
        json.put("type","");
        //token
        json.put("token",token);
        System.out.println("-----参数----\n"+json.toJSONString());
        try{
            Client client = dcf.createClient("http://172.23.37.61:11021/ws/bigdata?wsdl");
            String denseStr=RSAUtil.publicEncrypt(json.toJSONString(),RSAUtil.getPublicKey(keyPair));
            Object[] objects = new Object[0];
            objects = client.invoke("bigDataAlarm", denseStr, keyPair);
            if(objects.length>0){
                //解密
                String result=DESUtil.decrypt((String) objects[0],"596F37A6(0B26)4E10{92CA}39F1D229");
                System.out.println(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
