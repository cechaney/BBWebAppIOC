package com.homedepot.bb.examples;

import com.homedepot.bb.ModelAndView;
import com.homedepot.bb.annotations.AutoWired;
import com.homedepot.bb.annotations.Controller;
import com.homedepot.bb.annotations.RequestMapping;
import com.homedepot.bb.annotations.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.ConstructorProperties;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ExampleController {

    private static final Logger LOGGER = Logger.getLogger(ExampleController.class.getName());

    @Value("messagePrefix")
    private String messagePrefix;

    @AutoWired("ChrisBean")
    private ExampleBean exampleBean;

    private String constructorMessage;

    //Be aware that constructor Autowired only works if debug symbols are turned on OR we use the @ConstructorProperties annotation
    @AutoWired
    @ConstructorProperties({"constructorMessage"})
    public ExampleController(String constructorMessage){
        super();
        this.constructorMessage = constructorMessage;
    }

    @RequestMapping(path = "/example")
    public ModelAndView service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LOGGER.log(Level.INFO, "Constructor injected value: " + constructorMessage);

        return new ModelAndView(exampleBean, "example.html");

    }

}
