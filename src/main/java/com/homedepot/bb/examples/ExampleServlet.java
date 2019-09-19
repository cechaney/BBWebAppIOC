package com.homedepot.bb.examples;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
  name = "ExampleServlet",
  urlPatterns = {"/servlet"}
)
public class ExampleServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html lang=\"eng\"");
        writer.println("<head>");
        writer.println("    <title>Brown Bag Webapp </title>");
        writer.println("    <link href=\"styles/simple-grid.min.css\" rel=\"stylesheet\"/>");
        writer.println("    <style>");
        writer.println("        body {");
        writer.println("        }");
        writer.println("        #jspIcon{");
        writer.println("            width: 128px;");
        writer.println("            height: 128px;");
        writer.println("        }");
        writer.println("        .jumbotron{");
        writer.println("            color: white;");
        writer.println("            background: rgb(0,0,0);");
        writer.println("            background: linear-gradient(180deg, rgba(0,0,0,1) 0%, rgba(152,152,152,1) 100%);");
        writer.println("            padding-top: 16px;");
        writer.println("        }");
        writer.println("        .jumbotron h1, h2, h3 {");
        writer.println("            color: white");
        writer.println("        }");
        writer.println("    </style>");
        writer.println("<head>");
        writer.println("<body>");
        writer.println("    <div class=\"jumbotron\">");
        writer.println("        <div class=\"container\">");
        writer.println("            <div class=\"row\">");
        writer.println("                <div class=\"col-12 center\">");
        writer.println("                    <div class=\"font-light\"><img id=\"jspIcon\" src=\"images/servlet.png\"></div>");
        writer.println("                    <div style=\"color: white; font-size: 3.5em\">Java Servlet</div>");
        writer.println("                    <h2 class=\"font-light\">Servlets date from 1996 and are backbone of Java Web apps</h2>");
        writer.println("                </div>");
        writer.println("            </div>");
        writer.println("        </div>");
        writer.println("    </div>");
        writer.println("    <div>");
        writer.println("    <div class=\"container\">");
        writer.println("        <ul>");
        writer.println("            <li>");
        writer.println("                <p class=\"lead\">Using Servlets to produce HTML is a little tiring.  It's constant calls to \"writer.println\"</p>");
        writer.println("            </li>");
        writer.println("            <li>");
        writer.println("                <p class=\"lead\">It's much easier to combine me with a templating library like Mustache, Apache Velocity, Freemarker, etc</p>");
        writer.println("            </li>");
        writer.println("            <li>");
        writer.println("                <p class=\"lead\">JSP files are actually compiled into Servlets at runtime</p>");
        writer.println("            </li>");
        writer.println("            <li>");
        writer.println("                <p class=\"lead\">Technically speaking, JSP is a templating library too.  I can include the output of a JSP in my code</p>");
        writer.println("            </li>");
        writer.println("        </ul>");
        writer.println("        <hr/>");
        writer.println("    </div>");

        writer.println("    <div>");
        request.getRequestDispatcher("/lifecycle.jsp").include(request, response);
        writer.println("    </div>");

        request.getRequestDispatcher("/links.jsp").include(request, response);

        writer.println("</body>");
        writer.println("</html");

    }

}
