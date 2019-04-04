package com.homedepot.bb.examples;

import com.homedepot.bb.annotations.Bean;
import com.homedepot.bb.annotations.Controller;
import com.homedepot.bb.annotations.RequestMapping;
import com.homedepot.bb.annotations.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ExampleController {

    @Value("messagePrefix")
    private String messagePrefix;

    @Bean("ExampleBean")
    private ExampleBean exampleBean;

    @RequestMapping(path = "/ec")
    public void service(HttpServletRequest req, HttpServletResponse res){

    }

}
