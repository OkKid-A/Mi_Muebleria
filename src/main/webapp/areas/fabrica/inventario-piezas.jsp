<%@ page import="Fabrica.Pieza" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 31/08/2021
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ArrayList<Pieza> lista = (ArrayList<Pieza>) request.getAttribute("inventario");
%>
<jsp:include page="/includes/resources.jsp"/>
<head>
    <title>Inventario</title>
</head>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<div class="pt-5 flex-column container-fluid pt-lg-5">
    <h1 class="mt-5 text-center">Inventario</h1>
    <div class="container-fluid row justify-content-center ">
        <table class="table-bordered text-center">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Pieza</th>
                <th scope="col">Valor</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Editar</th>
                <th scope="col">Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <%
                    for (Pieza pieza : lista) {
                        out.print("<tr><th scope = \"row\">" + pieza.getNombre() + "</th>");
                        out.print("<td>" + pieza.getValor() + "</td>");
                        out.print("<td>" + pieza.getCantidad() + "</td>");
                        out.print("<td><form class=\"form-check-inline  \" method=\"get\" action=\"../servlets/edit-inventario-servlet\">" +
                                "<input type=\"hidden\" name=\"id\" id=\"id\" value=\""+pieza.getId()+"\">" +
                                "<button class=\"btn " +
                                "btn-outline-warning btn-block align-center\" type=\"submit\">" +
                                "                            Editar" +
                                "                        </button>" +
                                "                    </form></td>");
                        out.print("<td><form class=\"form-check-inline\" method=\"post\" action=\"../servlets/edit-inventario-servlet?action=" + pieza.getId() + "\">" +
                                "                    <button type=\"submit\" class=\"btn btn-block btn-outline-danger\">Eliminar</button>\n" +
                                "                    </form>\n" +
                                "                </td></tr>");
                    }
                %>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
