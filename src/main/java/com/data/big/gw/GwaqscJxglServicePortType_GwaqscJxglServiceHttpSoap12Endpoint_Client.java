
package com.data.big.gw;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.2
 * 2020-11-10T10:38:56.623+08:00
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
        System.out.println("Invoking getHcsj...");
        String _getHcsj_qsrq = "2020-11-10";
        String _getHcsj_jsrq = "2020-11-11";
        String _getHcsj_cxdj = "";
        String _getHcsj_xm = "";
        String _getHcsj_page = "1";
        String _getHcsj_rows = "10";
        String _getHcsj__return = port.getHcsj(_getHcsj_qsrq, _getHcsj_jsrq, _getHcsj_cxdj, _getHcsj_xm, _getHcsj_page, _getHcsj_rows);
        System.out.println("getHcsj.result=" + _getHcsj__return);


        }
 

        System.exit(0);
    }

}
