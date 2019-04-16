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
        .jumbotron h1, h2, h3 {
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
                <div class="">
                    <img src="images/book.jpg" style="width: 128px;"/>
                </div>
                <div style="color: white; font-size: 3.5em">Spring History Lesson</div>
                <h2 class="font-light">2002 - 2019</h2>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-12">
            <ul>
                <li>
                    <p class="lead">Rod Johnson disrupted the entire J2EE Enterprise server industry with one book</p>
                </li>
                <li>
                    <p class="lead">Spring was a reaction to the complexity of J2EE (Enterprise Java Beans especially)</p>
                </li>
                <li>
                    <p class="lead">16 years of development = a TON of features/capabilities.</p>
                </li>
                <li>
                    <p class="lead">It's impossible to be an expert in everything Spring</p>
                </li>
                <li>
                    <p class="lead">Knowing the fundamentals of IOC is key to mastering Spring</p>
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
        </div>
    </div>
</div>
</body>
</html>