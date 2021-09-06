<%@ page import="Ventas.Factura" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Ventas.MuebleProducto" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 5/09/2021
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Factura factura = (Factura) request.getAttribute("factura");
    ArrayList<MuebleProducto> lista = factura.getListaVendida();
%>
<jsp:include page="/includes/resources.jsp"/>
<head>
    <title>Factura</title>
</head>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<h1 class="pt-5 text-center ml-2 mt-5">Factura</h1>
<div class="card-body">
    <div class="row align-content-center">
        <div class="col-md-5 card-columns ">
            <h3 class="pt-2 text-left">Cliente: <%out.print(factura.getNombre());%></h3>
        </div>
        <div class="col-md-5 card-columns">
            <h3 class="pt-2 text-right">Nit: <%out.print(factura.getNit());%></h3>
        </div>
    </div>
    <div class="row align-items-center">
        <div class="col-md-5 card-columns">
            <h5 class="pt-1 text-left">Fecha: <%out.print(factura.getFechaCompra());%></h5>
        <input type="hidden" value="<%out.print(factura.getFechaCompra());%>" id="date" name="date">
        </div>
        <div class="col-md-5 card-columns">
            <h4 class="pt-2 text-right ">Le Atendio: <%out.print(factura.getVendedor());%></h4>
        </div>
    </div>
    <table class="table-bordered text-center table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Mueble</th>
            <th scope="col">Precio</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (MuebleProducto muebleProducto : lista) {
                out.print("<tr><th scope = \"row\">" + muebleProducto.getId() + "</th>");
                out.print("<td>" + muebleProducto.getNombreProducto() + "</td>");
                out.print("<td>" + muebleProducto.getPrecio() + "</td></tr>");
            }
        %>
        </tbody>
        </thead>
    </table>
    <hr/>
    <h2 class="pt-1 text-center" style="font-family: 'Arial Black'">Total = <%out.print(factura.getTotal());%></h2>
</div>
<div class="justify-content-center align-content-center col">
<form action="../../servlets/ventas/registro-servlet" method="post">
    <button class="btn btn-primary container-fluid" type="submit">Completar Compra</button>
</form>
</div>
</body>
</html>
