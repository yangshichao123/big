package com.data.big.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.data.big.rsa.DESUtil;
import com.data.big.rsa.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class CXFUtil {


    public static Client getConnection(String url) {
        Client client = null;
        try {

            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            String result = "";
            client = dcf.createClient(url);
            if (client != null) {
                // 设置超时单位为毫秒
                HTTPConduit conduit = (HTTPConduit) client.getConduit();
                HTTPClientPolicy policy = new HTTPClientPolicy();
                policy.setConnectionTimeout(30000);
                policy.setAllowChunking(false);
                policy.setReceiveTimeout(30000);
                conduit.setClient(policy);

            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return client;
    }


    //"http://172.23.37.61:11021/ws/bigdata?wsdl"
    public static String getKeyPair(Client client) {
        String result = "连接异常";

        Object[] objects = new Object[0];
        try {
            objects = client.invoke("keyPair");
            if (objects.length > 0) {
                //解密
                String src = DESUtil.decrypt((String) objects[0], "596F37A6(0B26)4E10{92CA}39F1D229");
                JSONObject jsonObject = JSON.parseObject(src);

                if ("1".equals(jsonObject.getString("code"))) {
                    result = jsonObject.getString("message");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public static String auth(Client client, String keyPair, Map<String,String> map) {
        String result = "连接异常";


        Object[] objects = new Object[0];
        try {
            //参数进行加密
            JSONObject json = new JSONObject();
            // json.put("userName","admin");
            //json.put("passWord","admin");
            json.putAll(map);
            System.out.println("-----公钥----\n" + keyPair);
            System.out.println("-----参数----\n" + json.toJSONString());
            String denseStr = RSAUtil.publicEncrypt(json.toJSONString(), RSAUtil.getPublicKey(keyPair));
            System.out.println("-----加密结果----\n" + denseStr);
            objects = client.invoke("auth", denseStr, keyPair);
            if (objects.length > 0) {
                //解密
                String src = DESUtil.decrypt((String) objects[0], "596F37A6(0B26)4E10{92CA}39F1D229");
                JSONObject jsonObject = JSON.parseObject(src);
                if ("1".equals(jsonObject.getString("code"))) {
                    result = jsonObject.getString("message");
                    System.out.println("-----token----\n" + result);
                } else {
                    System.out.println("-----请求失败----\n" + jsonObject.getString("message"));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public static String bigDataAlarm(Client client, String keyPair, Map<String,Object> map) {
        String result = "连接异常";
        JSONObject json = new JSONObject();

        json.putAll(map);
        System.out.println("-----参数----\n" + json.toJSONString());
        try {

            String denseStr = RSAUtil.publicEncrypt(json.toJSONString(), RSAUtil.getPublicKey(keyPair));
            Object[] objects = new Object[0];
            objects = client.invoke("bigDataAlarm", denseStr, keyPair);
            if (objects.length > 0) {
                //解密
                result = DESUtil.decrypt((String) objects[0], "596F37A6(0B26)4E10{92CA}39F1D229");
                System.out.println(result);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public static String gwAlarm(Client client, List<String> list, String ffName) {

        String result = "连接异常";
        int i = list.size();
        Object[] objectsMap = new Object[i];
        for (int j = 0; j < list.size(); j++) {
            objectsMap[j] = list.get(j);
        }
        log.info("-----参数----\n" + objectsMap.toString());
        try {
            log.info("开始执行时间：" + new Date().getTime());

            Object[] objects = new Object[0];


            objects = client.invoke(ffName, objectsMap);
            if (objects.length > 0) {
                //解密
                result = (String) objects[0];
                System.out.println(result);
            }
        } catch (Exception e) {
            log.info("结束执行时间：" + new Date().getTime());
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
