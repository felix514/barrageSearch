package com.group4.barrageSearch.response;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.group4.barrageSearch.controller")
public class JsonpResponse extends AbstractJsonpResponseBodyAdvice {
    public JsonpResponse() {
        //构造函数
        super("callback","jsonp");
    }
}
