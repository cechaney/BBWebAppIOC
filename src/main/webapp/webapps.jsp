<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <title>Brown Bag #1</title>

    <link href="styles/simple-grid.min.css" rel="stylesheet"/>

    <style>
        body {

        }
        #icon{
            width: 128px;
            height: 128px;
        }
        .jumbotron{
            color: white;
            background: rgb(0,0,0);
            background: linear-gradient(180deg, rgba(0,0,0,1) 0%, rgba(152,152,152,1) 100%);
            padding-top: 16px;
        }
        .jumbotron h2 {
            color: white
        }
        a, u {
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-12 center">
                <div class="font-light"><img id="icon" src="images/jsp.png"></div>
                <div style="color: white; font-size: 3.5em">Java Web Apps</div>
                <h2 class="font-light">How does all this stuff work?</h2>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-12">
            <ul>
                <li>
                    <p class="lead">In May 1996 the Java Servlets API standard was proposed</p>
                </li>
                <li>
                    <p class="lead">The Servlets API laid out a standard way for a java class to respond to network (mostly HTTP) requests</p>
                </li>
                <li>
                    <p class="lead">Servlets need an application called a "Servlet Container" to run them.</p>
                </li>
                <li>
                    <p class="lead">Apache Tomcat, Jetty, and Glassfish are examples of containers</p>
                </li>
                <li>
                    <p class="lead">The Spring Boot apps you use depend on the servlet API and Servlet Containers to function</p>
                </li>
                <li>
                    <p class="lead">Servlet Containers use a Pool of Threads to handle incoming requests.  1 request = 1 thread</p>
                </li>
                <li>
                    <p class="lead">Servlet Containers also handle session storage, security, clustering</p>
                </li>
                <li>
                    <p class="lead">Spring Boot apps use an "Embedded" version of a Servlet container that doesn't require you to maintain a copy of the container application</p>
                </li>
            </ul>
            <hr>
            <div class="col-6 center">
                <h4>Here's a picture of the most basic structure a Java Web Application can have.</h4>
                <img src="./images/structure.png"/>
            </div>
            <div class="col-6 center">
                <h4>To run this web application we build that structure into a .war (Web application archive) file and copy it into the "webapps" folder of a copy of Apache Tomcat</h4>
                <img src="./images/tomcat.png"/>
            </div>
            <p class="lead">Modern alternatives to the Servlet API / Spring Boot like Play, Ratpack, and Micronaut do away with Thread Pools and embedded containers. These alternatives are superior in performance to Spring Boot and non JVM alternatives like Node.js</p>
            <div class="col-3 center">
                <a href="./webapps.jsp">Java Web Apps</a>
            </div>
            <div class="col-2 center">
                <a href="./jsp.jsp">JSP</a>
            </div>
            <div class="col-2 center">
                <a href="./servlet">Servlets</a>
            </div>
            <div class="col-3 center">
                <a href="./mvc/example">IOC | MVC | Templates</a>
            </div>
            <div class="col-2 center">
                <a href="./history.jsp">Spring History</a>
            </div>
            <hr/>
            <div class="right">
                <c:set var="now" value="<%=new java.util.Date()%>"/>
                <b>The current date is:</b> <fmt:formatDate value="${now}"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>