package com.data.big.service;

import com.data.big.model.Analysiresult;
import com.data.big.model.Analysisvideo;
import com.data.big.model.VideoType;
import com.data.big.util.UUIDHelper;
import com.data.big.vo.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public interface ServiceAnalysis {
    Message addAnalysiresult(Analysiresult analysiresult);

    Message updateAnalysiresult(Analysiresult analysiresult);

    Message deleteAnalysiresult(Analysiresult analysiresult);

    Message getAnalysiresult(Analysiresult analysiresult);

    Message addAnalysisvideo(Analysisvideo analysisvideo);
    Message addAnalysisvideoAll(List<Analysisvideo> analysisvideo);

    Message updateAnalysisvideo(Analysisvideo analysisvideo);

    Message deleteAnalysisvideo(Analysisvideo analysisvideo);

    Message getAnalysisvideo(Analysisvideo analysisvideo);

    Message getAnalysiresultByIds(String ids);
}
