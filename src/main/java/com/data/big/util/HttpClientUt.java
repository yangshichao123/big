package com.data.big.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * httpClient json请求
 *
 * @author Linkview
 */
public class HttpClientUt {
    // 日志记录器
    private static final Logger logger = LogManager.getLogger(HttpClientUt.class);

    public static Map<String,String> cookieMap = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        String s = doPost("http://172.23.37.60:8099/fdCon/GetCameraInfo", "");
        HashMap<String,Object> dataMapList = getDataMapList(s);
        for (String s1 : dataMapList.keySet()) {

            System.out.println(s1);
            System.out.println(dataMapList.get(s1));
        }
        List<Map<String,Object>> list = (List) dataMapList.get("data");
        for (Map<String,Object> o : list) {
            for (String s1 : o.keySet()) {
                System.out.println(s1);
                Object o1 = o.get(s1);
                System.out.println(o1.toString());
            }
        }
    }

    public static String sendFile(String url, String jsonstr, String Authorization) {
        String result = null;
        List<File> files = new ArrayList<>();
        File f1 = new File(jsonstr);
        files.add(f1);
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            //请求超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);

            //设置参数
            MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
            // 解决乱码问题
            meBuilder.setCharset(Charset.forName("UTF-8"));
            meBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            String fid = Properties.getFid();
            meBuilder.addTextBody("fid", fid);
            for (File file : files) {
                FileBody fileBody = new FileBody(file, ContentType.create("multipart/form-data", Charset.forName("UTF-8")));
                meBuilder.addPart("files", fileBody);
            }
            httpPost.setHeader("Authorization", Authorization);

            HttpEntity reqEntity = meBuilder.build();

            httpPost.setEntity(reqEntity);

            logger.info("sendUrl: " + url + "  token: " + Authorization + " 文件地址：" + jsonstr + " 目录名称：" + fid);

            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                    System.out.println(resEntity);
                    logger.info("请求返回状态 " + result);
                }
            }
        } catch (ClientProtocolException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * post http请求
     *
     * @param url     请求地址
     * @param jsonstr json字符串
     * @return json字符串
     */
    public static String doPost(String url, String jsonstr,String headerStr) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            //请求超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(3000).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Authorization", headerStr);
            StringEntity se = new StringEntity(jsonstr, "utf-8");
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                //如果成功 则处理数据
                if (code == 200) {

                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "utf-8");

                    }
                } else {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("stateCode", code);//-1表示没有登陆
                    result = jsonParam.toString();
                }
            }
        } catch (ClientProtocolException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }


    public static String doGetd(String uri, Map<String,String> mapStr) {
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            for (String s : mapStr.keySet()) {
                uriBuilder.addParameter(s, mapStr.get(s));
            }
            httpClient = HttpClientBuilder.create().build();
            httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("user-agent", "Koala Admin");

            RequestConfig requestConfig =  RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
            httpGet.setConfig(requestConfig);

            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                //如果成功 则处理数据
                if (code == 200) {

                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "utf-8");

                    }
                } else {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("code", code);//-1表示没有登陆
                    result = jsonParam.toString();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }


    /**
     * post http请求
     *
     * @param url     请求地址
     * @param jsonstr json字符串
     * @return json字符串
     */
    public static String doPost(String url, String jsonstr) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            //请求超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(3000).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("user-agent", "Koala Admin");
            StringEntity se = new StringEntity(jsonstr, "utf-8");
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                //如果成功 则处理数据
                if (code == 200) {

                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "utf-8");

                    }
                } else {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("code", code);//-1表示没有登陆
                    result = jsonParam.toString();
                }
            }
        } catch (ClientProtocolException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    // 根据返回的json数据 返回map集合 list查询多个人员
    public static HashMap<String,Object> getDataMapList(String jsonData) {
        HashMap<String,Object> map = new HashMap<>();
        List<Map<String,Object>> arrayList = new ArrayList<>();
        //不等于null时执行下面代码
        if (jsonData != null && !"".equals(jsonData.trim())) {
            HashMap<String,Object> mapObj = JSONObject.parseObject(jsonData, HashMap.class);

            map.put("result", mapObj.get("result"));
            if ("success".equals(mapObj.get("result"))) {
                List<Object> list = JSONObject.parseArray(JSONObject.parseObject(
                        jsonData).getString("data"), Object.class);
                for (Object object : list) {
                    Map<String,Object> mapDate = JSONObject.parseObject(object
                            .toString(), HashMap.class);
                    arrayList.add(mapDate);
                }
				/*HashMap<String, Object> mapPage = JSONObject.parseObject(JSONObject
						.parseObject(jsonData).getString("page"), HashMap.class);*/
                map.put("data", arrayList);
            } else {
                map.put("message", mapObj.get("message"));
            }
        } else {
            map.put("result", "-999");
            map.put("message", "传入数据为空");
        }
        return map;
    }

    // 根据返回的json数据 返回map集合 list查询多个人员
    public static HashMap<String,Object> getDataMapList2flie(String jsonData) {
        HashMap<String,Object> map = new HashMap<>();
        List<Map<String,Object>> arrayList = new ArrayList<>();
        //不等于null时执行下面代码
        if (jsonData != null && !"".equals(jsonData.trim())) {
            HashMap<String,Object> mapObj = JSONObject.parseObject(jsonData, HashMap.class);

            map.put("stateCode", mapObj.get("stateCode"));
            if ("200".equals(mapObj.get("stateCode"))) {
                map.put("data", mapObj.get("data"));
            } else {
                map.put("errorType", mapObj.get("errorType"));

            }
            map.put("message", mapObj.get("message"));
        } else {
            map.put("stateCode", "-999");
            map.put("message", "传入数据为空");
        }
        return map;
    }

    /**
     * post http请求
     *
     * @param url     请求地址
     * @param jsonstr json字符串
     * @param boo     是否是登录  true 登录  false 别的请求
     * @return json字符串
     */
    public static String doPost(String url, String jsonstr, boolean boo) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            //请求超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(3000).build();
            httpPost.setConfig(requestConfig);
            if (boo) {
                httpPost.addHeader("user-agent", "Koala Admin");
            } else {
                httpPost.addHeader("Cookie", cookieMap.get("cookie"));
            }
            StringEntity se = new StringEntity(jsonstr, "utf-8");
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                //如果成功 则处理数据
                if (code == 200) {
                    if (boo) {
                        Header cookies = response.getFirstHeader("Set-Cookie");
                        if (cookies != null) {
                            String cookie = cookies.getValue();
                            cookieMap.put("cookie", cookie);
                        }
                    }
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "utf-8");
                        //如果包含<html则是没有登陆重定向返回的页面信息
                        if (result.contains("<html")) {
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("code", -1);//-1表示没有登陆
                            jsonParam.put("page", "");
                            jsonParam.put("data", "");
                            result = jsonParam.toString();
                        }
                    }
                } else {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("code", code);//-1表示没有登陆
                    jsonParam.put("page", "");
                    jsonParam.put("data", "");
                    result = jsonParam.toString();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * post http请求
     *
     * @param url     请求路径
     * @param fileStr 文件地址路径
     * @return subject_id 关联的人员id "" 表示没有关联  "2" 表示关联人员id为2的
     */
    public static String doPostFile(String url, String fileStr, String subject_id) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            //请求超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(3000).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Cookie", cookieMap.get("cookie"));

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            if (subject_id != null && !"".equals(subject_id)) {
                builder.addTextBody("subject_id", subject_id, ContentType.TEXT_PLAIN);
            }
            File f = new File(fileStr);
            builder.addBinaryBody(
                    "photo",
                    f,
                    ContentType.DEFAULT_BINARY,
                    f.getName());
            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {

                int code = response.getStatusLine().getStatusCode();
                //如果成功 则处理数据
                if (code == 200) {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "utf-8");
                        //如果包含<html则是没有登陆重定向返回的页面信息
                        if (result.contains("<html")) {
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("code", -1);//-1表示没有登陆
                            jsonParam.put("page", "");
                            jsonParam.put("data", "");
                            result = jsonParam.toString();
                        }
                    }
                } else {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("code", code);//-1表示没有登陆
                    jsonParam.put("page", "");
                    jsonParam.put("data", "");
                    result = jsonParam.toString();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * get http请求
     *
     * @return json字符串
     */
    public static String doGet(URI uri) {
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            httpGet = new HttpGet(uri);
            //请求超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(3000).build();
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Cookie", cookieMap.get("cookie"));


            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                //如果成功 则处理数据
                if (code == 200) {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "utf-8");
                        //如果包含<html则是没有登陆重定向返回的页面信息
                        if (result.contains("<html")) {
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("code", -1);//-1表示没有登陆
                            jsonParam.put("page", "");
                            jsonParam.put("data", "");
                            result = jsonParam.toString();
                        }
                    }
                } else {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("code", code);//-1表示没有登陆
                    jsonParam.put("page", "");
                    jsonParam.put("data", "");
                    result = jsonParam.toString();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * put http请求
     *
     * @return json字符串
     */
    public static String doPut(URI uri, String jsonStr) {
        HttpClient httpClient = null;
        HttpPut httpPut = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            httpPut = new HttpPut(uri);
            httpPut.setHeader("Content-type", "application/json");
            httpPut.addHeader("Cookie", cookieMap.get("cookie"));


            StringEntity se = new StringEntity(jsonStr, "utf-8");
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPut.setEntity(se);

            HttpResponse response = httpClient.execute(httpPut);
            if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                //如果成功 则处理数据
                if (code == 200) {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "utf-8");
                        //如果包含<html则是没有登陆重定向返回的页面信息
                        if (result.contains("<html")) {
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("code", -1);//-1表示没有登陆
                            jsonParam.put("page", "");
                            jsonParam.put("data", "");
                            result = jsonParam.toString();
                        }
                    }
                } else {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("code", code);//-1表示没有登陆
                    jsonParam.put("page", "");
                    jsonParam.put("data", "");
                    result = jsonParam.toString();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        try {
            final StringBuffer buffer = new StringBuffer(dataStr == null ? "" : dataStr);
            if (!"".equals(dataStr) && dataStr.contains("\\u")) {
                buffer.delete(0, buffer.length());
                int start = 0;
                int end = 0;
                while (start > -1) {
                    end = dataStr.indexOf("\\u", start + 2);
                    String a = "";//如果夹着非unicode编码的字符串，存放在这
                    String charStr = "";
                    if (end == -1) {
                        if (dataStr.substring(start + 2, dataStr.length()).length() > 4) {
                            charStr = dataStr.substring(start + 2, start + 6);
                            a = dataStr.substring(start + 6, dataStr.length());
                        } else {
                            charStr = dataStr.substring(start + 2, dataStr.length());
                        }
                    } else {
                        charStr = dataStr.substring(start + 2, end);
                    }
                    char letter = (char) Integer.parseInt(charStr.trim(), 16); // 16进制parse整形字符串。
                    buffer.append(new Character(letter).toString());
                    if (!"".equals(a)) {
                        buffer.append(a);
                    }
                    start = end;
                }
            }
            return buffer.toString();
        } catch (Exception e) {
            System.out.println(dataStr + " 字符串转换失败" + e);
        }
        return dataStr;
    }


    /**
     * delete http请求
     *
     * @return json字符串
     */
    public static String doDelete(URI uri) {
        HttpClient httpClient = null;
        HttpDelete httpDelete = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            httpDelete = new HttpDelete(uri);
            httpDelete.addHeader("Cookie", cookieMap.get("cookie"));


            HttpResponse response = httpClient.execute(httpDelete);
            if (response != null) {
                int code = response.getStatusLine().getStatusCode();
                //如果成功 则处理数据
                if (code == 200) {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, "utf-8");
                        //如果包含<html则是没有登陆重定向返回的页面信息
                        if (result.contains("<html")) {
                            JSONObject jsonParam = new JSONObject();
                            jsonParam.put("code", -1);//-1表示没有登陆
                            jsonParam.put("page", "");
                            jsonParam.put("data", "");
                            result = jsonParam.toString();
                        }
                    }
                } else {
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("code", code);//-1表示没有登陆
                    jsonParam.put("page", "");
                    jsonParam.put("data", "");
                    result = jsonParam.toString();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 获取jsong格式字符串
     */
    public static String getJsonStr(Map<String,String> mapPeople) {
        HashMap<String,Object> map = new HashMap<>();
        JSONObject jsonParam = new JSONObject();
        for (String s1 : mapPeople.keySet()) {//
            jsonParam.put(s1, mapPeople.get(s1));
        }

        return jsonParam.toString();
    }


    //  静态方法，类名可直接调用
    public static String doPostMap(String url, Map<String, Object> paramsMap) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        //配置连接超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpPost httpPost = new HttpPost(url);
        //设置超时时间
        httpPost.setConfig(requestConfig);

        //装配post请求参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String key : paramsMap.keySet()) {
            list.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }

        try {
            //将参数进行编码为合适的格式,如将键值对编码为param1=value1&param2=value2
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);

            //执行 post请求
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
            String strRequest = "";
            if (null != closeableHttpResponse && !"".equals(closeableHttpResponse)) {
                System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
                if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = closeableHttpResponse.getEntity();
                    strRequest = EntityUtils.toString(httpEntity);
                } else {
                    strRequest = "Error Response" + closeableHttpResponse.getStatusLine().getStatusCode();
                }
            }
            return strRequest;

        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
            return "协议异常";
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return "解析异常";
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return "传输异常";
        } finally {
            try {
                if (closeableHttpClient != null) {
                    closeableHttpClient.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

        }
    }

}

 