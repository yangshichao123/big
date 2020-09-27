package com.data.big.util;

import com.alibaba.fastjson.JSON;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.utils.JavaUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

public class webServiceUtils {

    // 日志记录器
    private static final Logger logger = LogManager.getLogger(webServiceUtils.class);
    /**
     * 获取安保3数据
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @param xm        线路
     * @param jl        路局
     * @return 安保类数组
     */
    public static ANBAO3[] callWebserviceASMX(String beginTime, String endTime, String xm, String jl) {
        //获取webservice接口地址
        String url = Properties.getAnbao3Url();
        String name= Properties.getAnbao3Name();
        //获取域名地址，server定义的
        String soapaction = "http://tempuri.org/";
        ANBAO3[] result = new ANBAO3[0];
        Service service = new Service();
        logger.error("访问地址："+url);
        logger.error("访问账号密码："+name);
        logger.error("访问开始时间："+beginTime);
        logger.error("访问结束时间："+endTime);

        try {
            Call call = (Call) service.createCall();
            SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement("http://tempuri.org/", "SecurityHeader");
            soapHeaderElement.addChildElement("UserName").setValue(name);
            soapHeaderElement.addChildElement("Password").setValue(name);
            call.addHeader(soapHeaderElement);

            call.setTargetEndpointAddress(new java.net.URL(url));

            org.apache.axis.description.OperationDesc oper;
            org.apache.axis.description.ParameterDesc param;
            oper = new org.apache.axis.description.OperationDesc();
            oper.setName("GetSGBYXL");
            param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_QSDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
            oper.addParameter(param);
            param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_JZDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
            oper.addParameter(param);
            param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "XLMC"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
            oper.addParameter(param);
            param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "lj"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
            oper.addParameter(param);

            oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfANBAO3"));
            oper.setReturnClass(ANBAO3[].class);
            oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetSGBYXLResult"));
            param = oper.getReturnParamDesc();
            param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "ANBAO3"));
            oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
            oper.setUse(org.apache.axis.constants.Use.LITERAL);


            call.setOperation(oper);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://tempuri.org/GetSGBYXL");
            call.setEncodingStyle(null);
            call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
            call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetSGBYXL"));


            call.registerTypeMapping(ANBAO3.class, new javax.xml.namespace.QName("http://tempuri.org/", "GetSGBYXL"), new BeanSerializerFactory(ANBAO3.class, new javax.xml.namespace.QName("http://tempuri.org/", "GetSGBYXLResult")), new BeanDeserializerFactory(ANBAO3.class, new javax.xml.namespace.QName("http://tempuri.org/", "GetSGBYXLResult")));


            //调用方法并传递参数
            Object object = (Object) call.invoke(new Object[]{beginTime, endTime, xm, jl});

            result = (ANBAO3[]) JavaUtils.convert(object, ANBAO3[].class);
           /* for (int i = 0; i < result.length; i++) {
                ANBAO3 line = result[i];

                System.out.println("result is:::"+line.toString());
            }*/


        } catch (ServiceException e) {
            logger.error(e.getMessage(),e);
        } catch (RemoteException e) {
            //(Line[]) org.apache.axis.utils.JavaUtils.convert(result, Line[].class);
            logger.error(e.getMessage(),e);
        } catch (SOAPException e) {
            logger.error(e.getMessage(),e);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(),e);
        }
        return result;
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
        //String[] value = param.toArray(new String[0]);

        //返回调用结果

        if (result.length > 0) {
			/*System.out.println(result[0]);
			Object json = JSON.toJSON(result[0]);
			System.out.println(json);

			return  json.toString();*/

            //return JSON.toJSONString(result[0]);
            return (String)result[0];

        }

        return "invoke success, but is void ";

    }
}
