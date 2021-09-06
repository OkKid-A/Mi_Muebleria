<%@ page import="Fabrica.RegistroEnsamble" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 31/08/2021
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ArrayList<RegistroEnsamble> lista = (ArrayList<RegistroEnsamble>) request.getAttribute("registros");
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
                <th scope="col">Tipo de Mueble</th>
                <th scope="col">Costo de Fabricacion</th>
                <th scope="col">Ensamblador</th>
                <th scope="col">Fecha de Creacion</th>
            </tr>
            <%
                for (RegistroEnsamble registroEnsamble : lista) {
                    out.print("<tr><th scope = \"row\">" + registroEnsamble.getTipo() + "</th>");
                    out.print("<td>" + registroEnsamble.getCosto() + "</td>");
                    out.print("<td>" + registroEnsamble.getUsuarioCreador() + "</td>");
                    out.print("<td>" + registroEnsamble.getFechaCreacion() + "</td></tr>");
                }
            %>
            </thead>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
