package com.data.big.webService;


import javax.jws.WebService;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@WebService(serviceName = "service", targetNamespace = "http://webService.mu.crcc.com/", endpointInterface = "com.data.big.webService.MuWebService")
public class MuWebServiceImpl implements MuWebService {

}
