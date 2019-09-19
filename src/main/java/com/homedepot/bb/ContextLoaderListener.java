package com.homedepot.bb;

import com.homedepot.bb.examples.ExampleListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.InputStream;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

  private static final Logger LOGGER = Logger.getLogger(ExampleListener.class.getName());

  public void contextInitialized(ServletContextEvent sce) {

    ServletContext context = sce.getServletContext();

    ApplicationContext appContext = new ApplicationContext();

    context.setAttribute(ApplicationContext.APP_CONTEXT_KEY, appContext);

    LOGGER.log(Level.INFO, "ApplicationContext initialized!");

    displayBanner(appContext);

  }

  public void contextDestroyed(ServletContextEvent sce) {

  }

  private void displayBanner(ApplicationContext appContext) {

    try{

      System.out.println("\n");

      InputStream input = ContextLoaderListener.class.getClassLoader().getResourceAsStream("banner.txt");

      byte[] buffer = new byte[8192];

      try {
        for (int length = 0; (length = input.read(buffer)) != -1;) {
          System.out.write(buffer, 0, length);
        }

        System.out.println("\n\n");

      } finally {
        input.close();
      }

    } catch(Exception e){
      LOGGER.log(Level.WARNING, "Application banner failed to display");
    }


  }
}
