package com.data.big.util;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.utils.JavaUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

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


}
