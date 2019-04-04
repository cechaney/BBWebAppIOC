package com.homedepot.bb.examples;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExampleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html lang=\"eng\"");
        writer.println("<head>");
        writer.println("    <title>Brown Bag Webapp </title>");
        writer.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        writer.println("    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>");
        writer.println("<head>");
        writer.println("<body>");

        writer.println("    <div class=\"jumbotron p-4 p-md-5 text-white rounded bg-dark\">");
        writer.println("        <div class=\"col-md-12 px-0\">");
        writer.println("            <h1 class=\"display-4 font-italic\">Hi!  I'm a Java Servlet</h1>");
        writer.println("        </div>");
        writer.println("        <p class=\"lead\">I'm the backbone of the Java Servlet API</p>");
        writer.println("        <p class=\"lead\">Using me to produce HTML is a little tiring.  It's constant calls to \"writer.println\"");
        writer.println("        <p class=\"lead\">I ended up being coupled with templating libraries like Apache Velocity, Freemarker, etc");
        writer.println("        <p class=\"lead\">Technically speaking, JSP is a templating library too.  I can include the output of a JSP in my code");
        writer.println("    </div>");

        writer.println("    <div>");

        request.getRequestDispatcher("/snip.jsp").include(request, response);

        writer.println("    </div>");

        writer.println("</body>");
        writer.println("</html");

    }

}
