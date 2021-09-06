<%@ page import="Admin.Reporte" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Ventas.Venta" %>
<%@ page import="static Admin.TipoReporte.*" %><%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 6/09/2021
  Time: 02:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Reporte reporte = null;
    String[] headers = new String[0];
    ArrayList<Venta> lista = null;
    try {
        if (request.getAttribute("listo") != null) {
            reporte = (Reporte) request.getAttribute("reporte");
            headers = reporte.getReporteHeaders();
            switch (reporte.getTipoReporte()) {
                case VENTAS:
                    lista = reporte.getVentas();
                case GANANCIAS:
                    lista = reporte.getVentas();
            }
        }
    } catch (NullPointerException e) {

    }
%>
<html>
<jsp:include page="/includes/resources.jsp"/>
<head>
    <title>Reportes</title>
</head>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<h1 class="pt-5 text-center ml-2 mt-5">Reportes</h1>
<div class=" pt-5 container justify-content-center ">
    <div class="row">
        <form class="form" method="get" action="../../servlets/admin/reportes-servlet">
            <div class="form-row align-items-center ">
                <div class="col-auto ">
                    <label for="tipo-reporte" class="col-form-label">Informe sobre:</label>
                    <select class="custom-select form-control" id="tipo-reporte" name="tipo-reporte">
                        <option selected value="ventas">Todas las Ventas</option>
                        <option value="devoluciones">Todas las Devoluciones</option>
                        <option value="ganancias">Todas las Ganancias</option>
                        <option value="ventas-usuario">El Empleado con Más Ventas</option>
                        <option value="ganancias-usuario">El Empleado con Mayor Ganancia</option>
                        <option value="mueble-mas">El Mueble Más Vendido</option>
                        <option value="mueble-menos">El Mueble Menos Vendido</option>
                    </select>
                </div>
                <div class="col-auto pt-4">
                    <label for="fecha-inicial" class="col-form-label">Desde</label>
                    <input type="datetime-local" class="datepicker" id="fecha-inicial" name="fecha-inicial">
                </div>
                <div class="col-auto pt-4">
                    <label for="fecha-final" class="col-form-label">Hasta</label>
                    <input type="datetime-local" class="datepicker" id="fecha-final" name="fecha-final">
                </div>
                <div class="col-auto pt-4">
                    <button type="submit" class="btn btn-success">Confirmar</button>
                </div>
            </div>
        </form>
    </div>
    <div class="row">
        <div class="container-md">
            <table class="table-bordered text-center table">
                <thead class="thead-dark">
                <tr>
                        <%
                for (String header: headers ) {
                            out.print("<th scope=\"col\">"+header+"</th>");
                        }%>
                </thead>
                <tbody>
                <%
                    if (reporte != null) {
                        switch (reporte.getTipoReporte()) {
                            case VENTAS:
                                for (Venta venta : lista) {
                                    out.print("<tr><th scope = \"row\">" + venta.getFechaCompra() + "</th>");
                                    out.print("<td>" + venta.getId() + "</td>");
                                    out.print("<td>" + venta.getNombreProducto() + "</td>");
                                    out.print("<td>" + venta.getValor() + "</td></tr>");
                                }
                            case GANANCIAS:
                                for (Venta venta : lista) {
                                    out.print("<tr><th scope = \"row\">" + venta.getFechaCompra() + "</th>");
                                    out.print("<td>" + venta.getId() + "</td>");
                                    out.print("<td>" + venta.getNombreProducto() + "</td>");
                                    out.print("<td>" + venta.getValor() + "</td></tr>");
                                }
                        }
                    }
                    out.print("<hr>");
                    out.print("<h2 class=\"pt-1 text-center\" style=\"font-family: 'Arial Black'\">Total = " +
                            reporte.getTotal() + "</h2>");
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

