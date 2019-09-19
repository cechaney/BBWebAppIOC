package com.homedepot.bb.examples;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(
  filterName = "ExampleFilter",
  urlPatterns = {"/*"},
  initParams = {
    @WebInitParam(name = "FilterParam1", value = "FilterParamValue2"),
    @WebInitParam(name = "FilterParam2", value = "FilterParamValue2")
  }

)
public class ExampleFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(ExampleFilter.class.getName());

    public void destroy() {
    }

    public void doFilter(
            ServletRequest req,
            ServletResponse resp,
            FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;

        LOGGER.log(Level.INFO, "Being request processing request for: " + httpServletRequest.getRequestURI());

        chain.doFilter(req, resp);

        LOGGER.log(Level.INFO, "Request processing complete");
    }

    public void init(FilterConfig config) throws ServletException {

        LOGGER.log(Level.INFO, "Configuring Filter Params...");
        LOGGER.log(Level.INFO, config.getInitParameter("FilterParam1"));
        LOGGER.log(Level.INFO, config.getInitParameter("FilterParam2"));

    }

}
