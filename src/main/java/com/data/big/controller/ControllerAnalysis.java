package com.data.big.controller;

import com.data.big.model.Analysiresult;
import com.data.big.model.Analysisvideo;
import com.data.big.model.Ipcseelocation;
import com.data.big.model.Screenresolving;
import com.data.big.service.ServiceAnalysis;
import com.data.big.service.ServiceMap;
import com.data.big.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ControllerAnalysis")
@CrossOrigin
public class ControllerAnalysis {


    @Autowired
    private ServiceAnalysis serviceAnalysis;


    @RequestMapping("/addAnalysiresult")
    public Message addAnalysiresult(Analysiresult analysiresult) {

        return serviceAnalysis.addAnalysiresult(analysiresult);
    }
    @RequestMapping("/updateAnalysiresult")
    public Message updateAnalysiresult(Analysiresult analysiresult) {

        return serviceAnalysis.updateAnalysiresult(analysiresult);
    }
    @RequestMapping("/deleteAnalysiresult")
    public Message deleteAnalysiresult(Analysiresult analysiresult) {

        return serviceAnalysis.deleteAnalysiresult(analysiresult);
    }
    @RequestMapping("/getAnalysiresult")
    public Message getAnalysiresult(Analysiresult analysiresult) {

        return serviceAnalysis.getAnalysiresult(analysiresult);
    }

    /**
     * 查询分析结果
     * @param ids  多个id以;号隔开
     * @return
     */
    @RequestMapping("/getAnalysiresultByIds")
    public Message getAnalysiresultByIds(String ids) {

        return serviceAnalysis.getAnalysiresultByIds(ids);
    }





    @RequestMapping("/addAnalysisvideo")
    public Message addAnalysisvideo(Analysisvideo analysisvideo) {

        return serviceAnalysis.addAnalysisvideo(analysisvideo);
    }

    @RequestMapping("/updateAnalysisvideo")
    public Message updateAnalysisvideo(Analysisvideo analysisvideo) {

        return serviceAnalysis.updateAnalysisvideo(analysisvideo);
    }
    @RequestMapping("/deleteAnalysisvideo")
    public Message deleteAnalysisvideo(Analysisvideo analysisvideo) {

        return serviceAnalysis.deleteAnalysisvideo(analysisvideo);
    }
    @RequestMapping("/getAnalysisvideo")
    public Message getAnalysisvideo(Analysisvideo analysisvideo) {

        return serviceAnalysis.getAnalysisvideo(analysisvideo);
    }


}
