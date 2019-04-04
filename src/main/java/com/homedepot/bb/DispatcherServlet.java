package com.homedepot.bb;

import com.homedepot.bb.util.ControllerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class.getName());

    private ApplicationContext context;
    private Map<String, ControllerMapping> controllerMap;

    @Override
    public void init() throws ServletException {

        super.init();

        this.context = (ApplicationContext) this.getServletContext().getAttribute(ApplicationContext.APP_CONTEXT_KEY);
        this.controllerMap = this.context.getControllerMap();

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();

        LOGGER.log(Level.INFO, "DispatcherServlet mapping request for " + path);

        ControllerMapping mapping = controllerMap.get(path);

        if(mapping != null){

            Object instance = mapping.getInstance();
            Method method = mapping.getMethod();

            if(instance != null && method != null){

                try {
                    method.invoke(instance, request, response);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.log(Level.SEVERE, "Unable to invoke controller method", e);
                }

            }

        }

    }
}
