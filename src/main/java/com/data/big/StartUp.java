package com.data.big;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.data.big.util.ANBAO3;
import com.data.big.util.DateUtils;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.utils.JavaUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


import javax.sound.sampled.Line;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;

/**
 * 自动生成代码  方法
 *
 * @author 61471
 */
public class StartUp {

    public static void main(String[] args) throws URISyntaxException {
		/*try {
			System.out.println("--------------------start generator-------------------");
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream is = classloader.getResourceAsStream("generatorConfig/generatorConfig.xml");
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(is);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			System.out.println("--------------------end generator-------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		}
*/
    }

    /**
     * 调用webservice.asmx接口
     *
     * @param method 需要调用的方法
     * @param json   传递的参数
     */
    public static void callWebserviceASMX(String method, String json) {
        //获取webservice接口地址
        String url = "http://172.20.98.116:8081/SSMISBDWebService.asmx";
        //获取域名地址，server定义的
        String soapaction = "http://tempuri.org/";

        Service service = new Service();

        try {
            Call call = (Call) service.createCall();
            SOAPHeaderElement soapHeaderElement = new SOAPHeaderElement("http://tempuri.org/", "SecurityHeader");
            soapHeaderElement.addChildElement("UserName").setValue("YJS_BD");
            soapHeaderElement.addChildElement("Password").setValue("YJS_BD");
            call.addHeader(soapHeaderElement);

            call.setTargetEndpointAddress(new java.net.URL(url));

            org.apache.axis.description.OperationDesc oper;
            org.apache.axis.description.ParameterDesc param;
            oper = new org.apache.axis.description.OperationDesc();
            oper.setName("GeLineList");
            param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_STARTINDEX"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
            oper.addParameter(param);
            param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_PERPAGESIZE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
            oper.addParameter(param);
            oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfLine"));
            oper.setReturnClass(Line[].class);
            oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GeLineListResult"));
            param = oper.getReturnParamDesc();
            param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "Line"));
            oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
            oper.setUse(org.apache.axis.constants.Use.LITERAL);


            call.setOperation(oper);
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://tempuri.org/GeLineList");
            call.setEncodingStyle(null);
            call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
            call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GeLineList"));


            call.registerTypeMapping(Line.class, new javax.xml.namespace.QName("http://tempuri.org/", "GeLineList"), new BeanSerializerFactory(Line.class, new javax.xml.namespace.QName("http://tempuri.org/", "GeLineListResult")), new BeanDeserializerFactory(Line.class, new javax.xml.namespace.QName("http://tempuri.org/", "GeLineListResult")));


            //调用方法并传递参数
            Object object = (Object) call.invoke(new Object[]{new Integer(1), new Integer(1)});

            Line[] result = (Line[]) JavaUtils.convert(object, Line[].class);
            for (int i = 0; i < result.length; i++) {
                Line line = result[i];

                System.out.println("result is:::" + line.toString());
            }


        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            //(Line[]) org.apache.axis.utils.JavaUtils.convert(result, Line[].class);
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static org.apache.axis.description.OperationDesc[] _operations;


    private static void _initOperationDesc1() {
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
		/*oper.setName("HelloWorld");
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "HelloWorldResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("HelloWorld2");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "age"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		oper.setReturnClass(String.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "HelloWorld2Result"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GeLineTotalCount");
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		oper.setReturnClass(int.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GeLineTotalCountResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[2] = oper;*/

		/*oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GeLineList");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_STARTINDEX"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_PERPAGESIZE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfLine"));
		oper.setReturnClass(Line[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GeLineListResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "Line"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[3] = oper;*/

		/*oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GeStationListByLine");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_XLDM"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfStation"));
		oper.setReturnClass(Station[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GeStationListByLineResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "Station"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[4] = oper;*/

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAnBao3TotalCount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_QSDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_JZDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetAnBao3TotalCountResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

		/*oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetAnBao3_SBGZTotalCount");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_QSDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_JZDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		oper.setReturnClass(int.class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetAnBao3_SBGZTotalCountResult"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[6] = oper;*/

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetAnBao3List");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_QSDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_JZDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_STARTINDEX"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_PERPAGESIZE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfANBAO3"));
        oper.setReturnClass(ANBAO3[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetAnBao3ListResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "ANBAO3"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

		/*oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetAnBao3List_SBGZ");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_QSDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_JZDATE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_STARTINDEX"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_PERPAGESIZE"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfJTSG_SBGZ_Bean"));
		oper.setReturnClass(JTSG_SBGZ_Bean[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetAnBao3List_SBGZResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "JTSG_SBGZ_Bean"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[8] = oper;*/

		/*oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetZerenBySgIDList");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_GGID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfZEREN"));
		oper.setReturnClass(ZEREN[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetZerenBySgIDListResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "ZEREN"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[9] = oper;*/

    }

    private static void _initOperationDesc2() {
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
		/*oper.setName("GetZerenByGZIDList");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_GGID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfJTSG_SBGZ_CLGB_ZRRD_Bean"));
		oper.setReturnClass(JTSG_SBGZ_CLGB_ZRRD_Bean[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetZerenByGZIDListResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "JTSG_SBGZ_CLGB_ZRRD_Bean"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[10] = oper;*/

		/*oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetFJBySgIDList");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_GGID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfFJ"));
		oper.setReturnClass(FJ[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetFJBySgIDListResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "FJ"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[11] = oper;*/
/*
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("GetFJNR");
		param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "V_FJID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
		param.setOmittable(true);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfFJ"));
		oper.setReturnClass(FJ[].class);
		oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetFJNRResult"));
		param = oper.getReturnParamDesc();
		param.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "FJ"));
		oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		_operations[12] = oper;*/

    }

}
