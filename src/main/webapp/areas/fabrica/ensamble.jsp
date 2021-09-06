<%@ page import="java.util.ArrayList" %>
<%@ page import="Fabrica.MuebleEnsamble" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="/includes/resources.jsp"/>
<%
    ArrayList<MuebleEnsamble> lista = ((ArrayList<MuebleEnsamble>) (request.getAttribute("tipos-muebles")));
%>
<head>
    <title>Ensamble</title>
</head>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<div class="pt-5 flex-column container-fluid pt-lg-5">
    <h1 class="text-center ml-2 mt-5"> Ensamble de muebles </h1>
    <div class="container-fluid row justify-content-center ">
        <div class="col-auto">
            <table class="table-bordered text-center">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Mueble</th>
                    <th scope="col">Piezas Necesarias</th>
                    <th scope="col">Ensamblar</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <%
                        for (MuebleEnsamble muebleEnsamble : lista) {
                            out.print("<tr><th scope = \"row\">" + muebleEnsamble.getNombreMueble() + "</th>");
                            out.print("<td>" + muebleEnsamble.formarMaterialesNecesarios() + "</td>");
                            out.print("<td><form class=\"form-check-inline  \" method=\"post\" action=\"../servlets/ensamblar-servlet?action=" +
                                    muebleEnsamble.getNombreMueble() + "\">" + "<button class=\"btn btn-outline-warning btn-block align-center\" type=\"submit\">" +
                                    "                            Ensamblar" +
                                    "                        </button>" +
                                    "                    </form></td></tr>");
                        }
                    %>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
