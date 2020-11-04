package com.data.big.webService;

import com.data.big.model.User;
import org.apache.commons.lang3.StringUtils;

import javax.jws.WebService;

@WebService
public interface MuWebService {

    User getUser(String userName, String userId);
    String getHcsj(String userName, String userId,String str);
     String getWxjh(String userName, String userId,String str);
}
