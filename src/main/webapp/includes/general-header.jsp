<%@ page import="User.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 31/08/2021
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/resources.jsp" />
<nav class="navbar fixed-top navbar-expand-lg navbar-light " style="background-color: #c07a31">
    <a class="navbar-brand" href="">Mi Muebleria</a>
    <div class="collapse navbar-collapse nav-fill" id="navbarText">
        <ul class="navbar-nav mr-auto mr-2">
            <li class="nav-item active">
                <a class="nav-link" href="<%out.print(session.getAttribute("Main-header-link"));%>"><%out.print(session.getAttribute("Main-header"));%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%out.print(session.getAttribute("Second-header-link"));%>"><%out.print(session.getAttribute("Second-header"));%></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%out.print(session.getAttribute("Last-header-link"));%>"><%out.print(session.getAttribute("Last-header"));%></a>
            </li>
        </ul>
        <form method="get" action="/Mi_Muebleria/servlets/elector-servlet">
        <button class="btn btn-outline-warning my-2" type="submit" >Cerrar Sesión</button>
        </form>
    </div>
</nav>
