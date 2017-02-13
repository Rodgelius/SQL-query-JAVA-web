<%@ page import="dgava.controller.MainController" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id = "beans" class = "dgava.controller.MainController" scope = "session">
</jsp:useBean>

<html>
  <head>
    <meta charset="utf-8">
    <title>DB Zabbix Query</title>
  </head>
<body>

<!-- FORM -->
<span style="float:left;">
    <form method="post" action="maincontroller">
      Hostname <input name="host" value="<%=MainController.name%>"/>
      DateFrom: <input type="text" id="datetimepicker" name="from" value="<%=MainController.timeFromTxt%>"/>
      DateTo: <input type="text" id="datetimepicker1" name="to" value="<%=MainController.timeToTxt%>"/>
      <input type="Submit" value="Search"/>
    </form>
</span>
<span style="float:right;">
    <form method="post" action="export">
      <input type="Submit" value="Export CSV" />
    </form>
</span>
<!-- FORM -->
<br>
<br>

<!-- TABLE -->
<span style="float:left;">
<table border="3" rules="all">
  <tr><TD> id </TD><TD> event_time </TD><TD> host </TD> <TD> ip </TD><TD> trigger </TD><TD> item </TD><TD> status </TD><TD> priority </TD></tr>
  <c:forEach items="${beans.list}" var="row" varStatus="status">
    <tr><TD> ${row.col1} </TD><TD> ${row.col2} </TD><TD> ${row.col3} </TD> <TD> ${row.col4} </TD><TD> ${row.col5} </TD><TD> ${row.col6} </TD><TD> ${row.col7} </TD><TD> ${row.col8} </TD></tr>
  </c:forEach>
</table>
</span>
<!-- TABLE -->
  </body>
  <!-- Jquery DateTimePicker Script -->
  <link rel="stylesheet" href="./jquery.datetimepicker.css">
  <script src="./jquery.js"></script>
  <script src="./jquery.datetimepicker.full.js"></script>
  <script>
      $('#datetimepicker').datetimepicker({
          format: 'Y-m-d H.i.s'
      });
      $('#datetimepicker1').datetimepicker({
          format: 'Y-m-d H.i.s'
      });
  </script>
  <!-- Jquery DateTimePicker Script -->
</html>