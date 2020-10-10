
package com.data.big.gw;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;


/**
 * This class was generated by Apache CXF 3.1.2
 * 2020-09-11T10:16:44.863+08:00
 * Generated source version: 3.1.2
 * 
 */
public final class GwaqscJxglServicePortType_GwaqscJxglServiceHttpSoap12Endpoint_Client {

    private static final QName SERVICE_NAME = new QName("http://services.itcmor.com", "GwaqscJxglService");

    private GwaqscJxglServicePortType_GwaqscJxglServiceHttpSoap12Endpoint_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = GwaqscJxglService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        GwaqscJxglService ss = new GwaqscJxglService(wsdlURL, SERVICE_NAME);
        GwaqscJxglServicePortType port = ss.getGwaqscJxglServiceHttpSoap12Endpoint();  
        
        
        {
        System.out.println("Invoking getSgjh...");
        String _getSgjh_qsrq = "";
        String _getSgjh_jsrq = "";
        String _getSgjh__return = port.getSgjh(_getSgjh_qsrq, _getSgjh_jsrq);
		System.out.println("getSgjh.result=" + _getSgjh__return);
        }

        {
        System.out.println("Invoking getHcsj...");
        String _getHcsj_qsrq = "";
        String _getHcsj_jsrq = "";
        String _getHcsj_cxdj = "";
        String _getHcsj__return = port.getHcsj(_getHcsj_qsrq, _getHcsj_jsrq, _getHcsj_cxdj);
        System.out.println("getHcsj.result=" + _getHcsj__return);


        }
        {
        System.out.println("Invoking getWxjh...");
        String _getWxjh_qsrq = "";
        String _getWxjh_jsrq = "";
        String _getWxjh__return = port.getWxjh(_getWxjh_qsrq, _getWxjh_jsrq);
        System.out.println("getWxjh.result=" + _getWxjh__return);


        }
        
        System.exit(0);
    }

}
