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
                        <div style="color: white; font-size: 3.5em">Java Server Pages</div>
                        <h2 class="font-light">JSP is a templating system for Java web applications</h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <ul>
                        <li>
                            <p class="lead">Devs used to build powerful apps with JSP. These days there's a perference for "simple" template frameworks like Mustache.</p>
                        </li>
                        <li>
                            <p class="lead">The biggest complaint about JSP was that it encouraged the mixing of code and markup.  Designers couldn't see their design through all of the code snippets.</p>
                        </li>
                        <li>
                            <p class="lead">What's old is new again.  You're probably mixing code and markup together in your React.js app. >:P</p>
                        </li>
                        <li>
                            <p class="lead">Custom JSP tags libraries were popular as well.  The date at the bottom of the page is a Date Format tag.</p>
                        </li>
                    </ul>
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
