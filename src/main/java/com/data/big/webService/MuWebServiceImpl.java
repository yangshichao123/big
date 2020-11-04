package com.data.big.webService;


import javax.jws.WebService;

import com.data.big.model.User;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@WebService(serviceName = "service", targetNamespace = "http://webService.big.data.com/", endpointInterface = "com.data.big.webService.MuWebService")
public class MuWebServiceImpl implements MuWebService {
    @Override
    public User getUser(String userName, String userId) {
        User user=new User();
       /* user.setName(userName);
        user.setUserId(userId);*/
        return user;
    }

    @Override
    public String getHcsj(String userName, String userId,String str) {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        stringBuffer.append("<HcsjDatas>");
        stringBuffer.append("   <hcsj>");
        stringBuffer.append("       <dwmc>承德</dwmc>");
        stringBuffer.append("       <xm>锦承</xm>");
        stringBuffer.append("       <xbh>0022</xbh>");
        stringBuffer.append("       <xb>下</xb>");
        stringBuffer.append("       <lc>378.504</lc>");
        stringBuffer.append("       <jcsj class=\"sql-timestamp\">2014-05-15 16:20:00.0</jcsj>");
        stringBuffer.append("       <jssj class=\"sql-timestamp\">2014-05-15 16:38:42.0</jssj>");
        stringBuffer.append("       <czjsd>0.2</czjsd>");
        stringBuffer.append("       <czjsdjb>3</czjsdjb>");
        stringBuffer.append("       <spjsd>0</spjsd>");
        stringBuffer.append("       <spjsdjb>0</spjsdjb>");
        stringBuffer.append("       <sd>73</sd>");
        stringBuffer.append("       <jch>9217</jch>");
        stringBuffer.append("       <cc>6455</cc>");
        stringBuffer.append("   </hcsj>");
        stringBuffer.append("   <hcsj>");
        stringBuffer.append("       <dwmc>承德</dwmc>");
        stringBuffer.append("       <xm>锦承</xm>");
        stringBuffer.append("       <xbh>0022</xbh>");
        stringBuffer.append("       <xb>下</xb>");
        stringBuffer.append("       <lc>378.504</lc>");
        stringBuffer.append("       <jcsj class=\"sql-timestamp\">2014-05-15 16:20:00.0</jcsj>");
        stringBuffer.append("       <jssj class=\"sql-timestamp\">2014-05-15 16:38:42.0</jssj>");
        stringBuffer.append("       <czjsd>0.2</czjsd>");
        stringBuffer.append("       <czjsdjb>3</czjsdjb>");
        stringBuffer.append("       <spjsd>0</spjsd>");
        stringBuffer.append("       <spjsdjb>0</spjsdjb>");
        stringBuffer.append("       <sd>73</sd>");
        stringBuffer.append("       <jch>9217</jch>");
        stringBuffer.append("       <cc>6455</cc>");
        stringBuffer.append("   </hcsj>");
        stringBuffer.append("</HcsjDatas>");
        return stringBuffer.toString();

/*
        Element response = new Element("request");
        //根元素标签内的属性名与值
        response.setAttribute("command", "33333");
        //生成Doc文档
        Document Doc = new Document(response);
        //获取文档中的根标签
        response = Doc.getRootElement();


        Element	result = new Element("uuuuuuuu");
        result.addContent("0000000000");
        response.addContent(result);


        XMLOutputter XMLOut = new XMLOutputter();
        //将doc文档转换为字符串型的XML格式
        String xmlinfo = XMLOut.outputString(Doc);

        xmlinfo = xmlinfo.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>");

        System.out.println(Doc.getParent());


        return xmlinfo;*/
    }
    @Override
    public String getWxjh(String userName, String userId,String str) {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        stringBuffer.append("<HcsjDatas>");
        stringBuffer.append("   <hcsj>");
        stringBuffer.append("       <dwmc>承德</dwmc>");
        stringBuffer.append("       <xm>锦承</xm>");
        stringBuffer.append("       <xbh>0022</xbh>");
        stringBuffer.append("       <xb>下</xb>");
        stringBuffer.append("       <lc>378.504</lc>");
        stringBuffer.append("       <jcsj class=\"sql-timestamp\">2014-05-15 16:20:00.0</jcsj>");
        stringBuffer.append("       <jssj class=\"sql-timestamp\">2014-05-15 16:38:42.0</jssj>");
        stringBuffer.append("       <czjsd>0.2</czjsd>");
        stringBuffer.append("       <czjsdjb>3</czjsdjb>");
        stringBuffer.append("       <spjsd>0</spjsd>");
        stringBuffer.append("       <spjsdjb>0</spjsdjb>");
        stringBuffer.append("       <sd>73</sd>");
        stringBuffer.append("       <jch>9217</jch>");
        stringBuffer.append("       <cc>6455</cc>");
        stringBuffer.append("   </hcsj>");
        stringBuffer.append("   <hcsj>");
        stringBuffer.append("       <dwmc>承德</dwmc>");
        stringBuffer.append("       <xm>锦承</xm>");
        stringBuffer.append("       <xbh>0022</xbh>");
        stringBuffer.append("       <xb>下</xb>");
        stringBuffer.append("       <lc>378.504</lc>");
        stringBuffer.append("       <jcsj class=\"sql-timestamp\">2014-05-15 16:20:00.0</jcsj>");
        stringBuffer.append("       <jssj class=\"sql-timestamp\">2014-05-15 16:38:42.0</jssj>");
        stringBuffer.append("       <czjsd>0.2</czjsd>");
        stringBuffer.append("       <czjsdjb>3</czjsdjb>");
        stringBuffer.append("       <spjsd>0</spjsd>");
        stringBuffer.append("       <spjsdjb>0</spjsdjb>");
        stringBuffer.append("       <sd>73</sd>");
        stringBuffer.append("       <jch>9217</jch>");
        stringBuffer.append("       <cc>6455</cc>");
        stringBuffer.append("   </hcsj>");
        stringBuffer.append("</HcsjDatas>");
        return stringBuffer.toString();

/*
        Element response = new Element("request");
        //根元素标签内的属性名与值
        response.setAttribute("command", "33333");
        //生成Doc文档
        Document Doc = new Document(response);
        //获取文档中的根标签
        response = Doc.getRootElement();


        Element	result = new Element("uuuuuuuu");
        result.addContent("0000000000");
        response.addContent(result);


        XMLOutputter XMLOut = new XMLOutputter();
        //将doc文档转换为字符串型的XML格式
        String xmlinfo = XMLOut.outputString(Doc);

        xmlinfo = xmlinfo.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>");

        System.out.println(Doc.getParent());


        return xmlinfo;*/
    }
}
