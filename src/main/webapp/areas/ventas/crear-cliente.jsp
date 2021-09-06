<%@ page import="Ventas.Factura" %><%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 5/09/2021
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/resources.jsp"/>
<html>
<%
    String nit = request.getParameter("nit");
%>
<head>
    <title>Crear Cliente</title>
</head>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<div class="card-body">
    <h1 class="pt-5 text-center ml-2 mt-5">Nuevo Cliente</h1>
    <div class="col-aut mt-5 pt-2">
        <form class="justify-content-center step-by-step" method="post" action="../../servlets/ventas/cliente-servlet">
            <div class="mt-5 mb-3">
                <label for="nit">Nit:</label>
                <input class="form-control" name="nit" readonly id="nit" value="<%out.print(nit);%>">
            </div>
            <div class="mb-3">
                <label for="nombre">Nombre Completo</label>
                <input type="text" class="form-control" maxlength="40" id="nombre" name="nombre"
                       placeholder="Nombre Completo" required>
            </div>
            <div class="row container-fluid form-inline">
                <div class="mb-3 col-md-5">
                    <label for="address">Direccion:</label>
                    <input type="text" maxlength="20" class="form-inline" id="address" name="address"
                           placeholder="Direccion" required>
                </div>
                <div class="mb-3 col-md-5">
                    <label for="municipio">Municipio:</label>
                    <input type="text" maxlength="20" class="step form-inline" id="municipio" name="municipio"
                           placeholder="Municipio">
                </div>
                <div class="mb-3 col-md-5">
                    <label for="departamento">Departamento:</label>
                    <input type="text" maxlength="20"  class="step form-inline" id="departamento" name="departamento"
                           placeholder="Departamento">
                </div>
            </div>
            <button type="submit" onclick="" class="btn btn-primary btn-block">Guardar Cliente</button>
        </form>
    </div>
</div>
</body>
</html>
