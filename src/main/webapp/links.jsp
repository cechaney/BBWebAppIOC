<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
  <div class="row">
      <div class="col-12">
          </br>
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