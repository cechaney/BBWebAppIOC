package com.homedepot.bb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class.getName());

    private ApplicationContext context = null;

    @Override
    public void init() throws ServletException {

        super.init();

        this.context = (ApplicationContext) this.getServletContext().getAttribute(ApplicationContext.APP_CONTEXT_KEY);

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
