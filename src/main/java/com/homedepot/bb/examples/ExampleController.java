package com.homedepot.bb.examples;

import com.homedepot.bb.ModelAndView;
import com.homedepot.bb.annotations.AutoWired;
import com.homedepot.bb.annotations.Controller;
import com.homedepot.bb.annotations.RequestMapping;
import com.homedepot.bb.annotations.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ExampleController {

    @Value("messagePrefix")
    private String messagePrefix;

    @AutoWired("ExampleBean")
    private ExampleBean exampleBean;

    @RequestMapping(path = "/ec")
    public ModelAndView service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return new ModelAndView(exampleBean, "example.html");

    }

}
