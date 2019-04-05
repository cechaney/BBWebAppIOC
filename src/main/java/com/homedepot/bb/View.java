package com.homedepot.bb;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.codes.DefaultMustache;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View {

    private static final Logger LOGGER = Logger.getLogger(View.class.getName());

    private static final MustacheFactory mustacheFactory = new DefaultMustacheFactory();

    private Mustache mustache;

    public View(String content){
        this.mustache = View.mustacheFactory.compile(content);
    }

    public void render(HttpServletResponse response, Object model){

        try {

            response.setContentType("text/html");

            this.mustache.execute(response.getWriter(), model);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to render view", e);
        }
    }
}
