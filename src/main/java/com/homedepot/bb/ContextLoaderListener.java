package com.homedepot.bb;

import com.homedepot.bb.examples.ExampleListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ExampleListener.class.getName());

    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        ApplicationContext appContext = new ApplicationContext();

        context.setAttribute(ApplicationContext.APP_CONTEXT_KEY, appContext);

        LOGGER.log(Level.INFO, "ApplicationContext initialized!");

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
