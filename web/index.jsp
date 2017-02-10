<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="dgava.controller.MainController" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.02.17
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>

<%--<jsp:getProperty name="lizt" property="result"/>--%>

<%--<c:set var="str" value="${MainController.doGet}"/>--%>

<%--<%= request.getAttribute("result") %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id = "beans" class = "dgava.controller.MainController" scope = "request">
  <%--<jsp:getProperty name="bean" property="list" />--%>
</jsp:useBean>

<html>
  <head>
    <title>$Title$</title>
  </head>


  <br>
  <table border="3" rules="all">

    <tr><TD> id </TD><TD> event_time </TD><TD> host </TD> <TD> ip </TD><TD> trigger </TD><TD> item </TD><TD> status </TD><TD> priority </TD></tr>

  <c:forEach items="${beans.list}" var="row" varStatus="status">

    <tr><TD> ${row.col1} </TD><TD> ${row.col2} </TD><TD> ${row.col3} </TD> <TD> ${row.col4} </TD><TD> ${row.col5} </TD><TD> ${row.col6} </TD><TD> ${row.col7} </TD><TD> ${row.col8} </TD></tr>

  </c:forEach>
  </table>

  <table border="5">
    <%--<c:forEach var="host" items="${result}">--%>

      <% if (Calendar.getInstance ().get (Calendar.AM_PM) == Calendar.AM) { %>
      Good Morning
      <% } else { %>
      Good Afternoon
      <% } %>
      <br>
      <%  out.println(new java.util.Date ()); %>
  <br>
      <tr><TD>ФИО</TD><TD>Отдел</TD><TD>Стаж</TD></tr>
      <%--<%while (res.next()) {%>--%>
      <%--<tr>--%>
        <%--<td><%=res%></td>--%>
        <%--<td><%=rs.getString(3)%></td>--%>
        <%--<td><%=rs.getString(4)%></td>--%>
      <%--</tr>--%>
      <%--<%}%>--%>

    <%--<tr>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
      <%--<td></td>--%>
    <%--</tr>--%>
    <%--</c:forEach>--%>
  </table>
  </body>
</html>