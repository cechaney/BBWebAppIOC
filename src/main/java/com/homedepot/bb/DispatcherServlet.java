package com.homedepot.bb;

import com.homedepot.bb.util.ControllerMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(
  name = "DispatcherServlet",
  urlPatterns = {"/mvc/*"}
)
public class DispatcherServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class.getName());

    private static final String RESOURCE_DIR = "/templates/";

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

                    ModelAndView mav = (ModelAndView) method.invoke(instance, request, response);

                    View view = new View(RESOURCE_DIR + mav.getViewName());

                    view.render(response, mav.getModel());

                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.log(Level.SEVERE, "Unable to invoke controller method", e);
                }

            }

        }

    }

}
