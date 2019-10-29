package com.ecjtu.dynamicdatasource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author xiexiang
 * @date 2019/10/29 10:11 上午
 */

@RestController
@RequestMapping("/web")
public class TestController {

    @RequestMapping("/test")
    public void fun(HttpServletRequest request){
        String xmlByRequest = getXmlByRequest(request);
        String requestUri = request.getRequestURI();
        System.out.println(requestUri);
        System.out.println(xmlByRequest);
    }


    private String getXmlByRequest(HttpServletRequest request) {
        InputStream inputStream;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            inputStream = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
        } catch (IOException e) {
            //
        }
        return stringBuffer.toString();
    }
}
