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
                <div class="font-light"><img id="icon" src="images/bag.png"></div>
                <div style="color: white; font-size: 3.5em">Brown Bag #1</div>
                <h2 class="font-light">Java Web Apps * IOC/Spring * MVC/Spring</h2>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-12">
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