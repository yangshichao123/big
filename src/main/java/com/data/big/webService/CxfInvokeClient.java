package com.data.big.webService;


import java.io.File;

import java.io.IOException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.cxf.transport.http.HTTPConduit;

import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.core.io.ClassPathResource;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CxfInvokeClient {


    public static void main(String[] args) throws Exception {
		 /*ArrayList<User> listObj=new ArrayList<User>();
	        ArrayList<Object> listObjs=new ArrayList<Object>();
	        User uu=new User();
	        uu.setAge("2222222");
	        
	        User uu1=new User();
	        uu1.setAge("33333333333333333333333333");
	        listObj.add(uu);
	        listObj.add(uu1);
	        listObjs.add("55555555555555555555");
	        listObjs.add(JSON.toJSON(listObj).toString());//实体类需要 json转格式
	        ArrayList<String> listStr=new ArrayList<String>();
	        listStr.add("333");
	        listStr.add("222");
	        listObjs.add(listStr);
	        
		//String res= dynamicCallWebServiceByCXF("http://localhost:8080/services/user?wsdl", "getStringList", "http://service.mu.example.com/", "service", listObjs);
		String res= dynamicCallWebServiceByCXF("http://localhost:8080/services/user?wsdl", "getAlLUser", "http://service.mu.example.com/", "service", null);
		  List<User> parseArray = JSONArray.parseArray(res,User.class);//json转集合
		 
		System.out.println(parseArray);
		 ArrayList<Object> listO=new ArrayList<Object>();
		 listO.add("411001");
		
		String resU= dynamicCallWebServiceByCXF("http://localhost:8080/services/user?wsdl", "getUser", "http://service.mu.example.com/", "service",listO);
		//  List<User> parseArray = JSONArray.parseArray(res,User.class);
		User parseObject = JSON.parseObject(resU,User.class);//json转对象
		System.out.println(parseObject);*/
        ArrayList<Object> listO = new ArrayList<Object>();
        listO.add("411001");
        String resU = dynamicCallWebServiceByCXF("http://localhost:8088/services/mu?wsdl", "getName", "http://webService.mu.crcc.com/", "service", listO);
        System.out.println(resU);
    }

    /**
     * @param wsdlUrl         wsdl的地址：http://localhost:8001/demo/HelloServiceDemoUrl?wsdl
     * @param methodName      调用的方法名称 selectOrderInfo
     * @param targetNamespace 命名空间 http://service.limp.com/
     * @param name            name HelloServiceDemo
     * @param paramList       参数集合
     * @throws Exception
     */

    public static String dynamicCallWebServiceByCXF(String wsdlUrl, String methodName, String targetNamespace, String name, List<Object> paramList) throws Exception {

        //临时增加缓存，增加创建速度


        // 创建动态客户端

        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();

        // 创建客户端连接

        Client client = factory.createClient(wsdlUrl);

        ClientImpl clientImpl = (ClientImpl) client;

        Endpoint endpoint = clientImpl.getEndpoint();


        // Make use of CXF service model to introspect the existing WSDL

        ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);

        // 创建QName来指定NameSpace和要调用的service

        String localPart = name + "SoapBinding";

        QName bindingName = new QName(targetNamespace, localPart);

        BindingInfo binding = serviceInfo.getBinding(bindingName);


        //创建QName来指定NameSpace和要调用的方法绑定方法

        QName opName = new QName(targetNamespace, methodName);//selectOrderInfo


        BindingOperationInfo boi = binding.getOperation(opName);

//		BindingMessageInfo inputMessageInfo = boi.getInput();

        BindingMessageInfo inputMessageInfo = null;

        if (!boi.isUnwrapped()) {

            //OrderProcess uses document literal wrapped style.

            inputMessageInfo = boi.getWrappedOperation().getInput();

        } else {

            inputMessageInfo = boi.getUnwrappedOperation().getInput();

        }


        List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();


        /***********************以下是初始化参数，组装参数；处理返回结果的过程******************************************/

        Object[] parameters = new Object[parts.size()];

        for (int m = 0; m < parts.size(); m++) {

            MessagePartInfo part = parts.get(m);

            // 取得对象实例

            Class<?> partClass = part.getTypeClass();//OrderInfo.class;

            System.out.println(partClass.getCanonicalName()); // GetAgentDetails

            //实例化对象

            Object initDomain = null;

            //普通参数的形参，不需要fastJson转换直接赋值即可

            String string = paramList.get(m).toString();
            if ("java.lang.String".equalsIgnoreCase(partClass.getCanonicalName())

                    || "int".equalsIgnoreCase(partClass.getCanonicalName())) {

                initDomain = string;

            }

            //如果是数组

            else if (partClass.getCanonicalName().indexOf("[]") > -1) {

                //转换数组

                Class<?> componentType = partClass.getComponentType();
                initDomain = JSON.parseArray(string, componentType);

            } else {

                initDomain = JSON.parseObject(string, partClass);

            }

            parameters[m] = initDomain;


        }

        //定义返回结果集

        Object[] result = null;

        //普通参数情况 || 对象参数情况  1个参数 ||ArryList集合

        try {

            result = client.invoke(opName, parameters);

        } catch (Exception ex) {

            ex.printStackTrace();

            return "参数异常" + ex.getMessage();

        }

        //返回调用结果

        if (result.length > 0) {
			/*System.out.println(result[0]);
			Object json = JSON.toJSON(result[0]);
			System.out.println(json);

			return  json.toString();*/

            return JSON.toJSONString(result[0]);

        }

        return "invoke success, but is void ";

    }


}

