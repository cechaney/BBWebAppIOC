<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <title>Brown Bag Webapp </title>
    </head>
    <body>
        <div class="jumbotron p-4 p-md-5 text-white rounded bg-dark">
            <div class="col-md-12 px-0">
                <h1 class="display-4 font-italic">Hi!  I'm a Java Server Page.</h1>
                <p class="lead">People used to build powerful apps with me, but eventually Spring killed me off in favor "simple" template frameworks</p>
                <p class="lead">The biggest complaint about me was that I encouraged the mixing of code and markup.  Designers couldn't see their design through all of the code snippets</p>
                <p class="lead">But I guess what's old is new again.  You're probably mixing code and markup together in your React.js app</p>
                <p class="lead">Like that whipper-snapper Vue.js people used lots of custom tags to use inside my modules.  Here's a sample</p>
                <hr/>
                <div>
                    <c:set var="now" value="<%=new java.util.Date()%>"/>
                    <b>The current date is:</b> <fmt:formatDate value="${now}"/>
                </div>
            </div>
        </div>
    </body>
</html>
