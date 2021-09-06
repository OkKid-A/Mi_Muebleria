<%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 28/08/2021
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/resources.jsp" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<%
    out.print(session.getAttribute("sessname"));
%>
</body>
</html>
