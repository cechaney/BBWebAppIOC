<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <title>Brown Bag #1</title>

    <link href="styles/simple-grid.min.css" rel="stylesheet"/>
    <link href="styles/style.css" rel="stylesheet"/>

</head>
<body>
<div class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-12 center">
                <div class="font-light"><img id="icon" src="images/bag.png"></div>
                <div style="color: white; font-size: 3.5em">Brown Bag #1</div>
                <h2 class="font-light">Spring, Java Web Apps, & Dependency Injection</h2>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="col-2 center">
                <a href="./history.jsp">Spring History</a>
            </div>
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
                <a href="./mvc/example">DI | MVC | Templates</a>
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