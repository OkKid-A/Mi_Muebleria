<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 6/09/2021
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ArrayList<String> usuarios = (ArrayList<String>) request.getAttribute("lista-usuarios");
%>
<jsp:include page="/includes/resources.jsp"/>
<head>
    <title>Usuarios</title>
</head>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<h1 class="pt-5 text-center ml-2 mt-5">Usuarios</h1>
<div class=" pt-5 container justify-content-center ">
    <h2 class="pt-2 text-left">Modificar</h2>
    <div class="row">
        <form class="form" method="post" action="../../servlets/admin/usuarios-servlet">
            <div class="form-row align-items-center ">
                <div class="col-auto ">
                    <label for="nombre" class="col-form-label">Selecciona un Usuario</label>
                    <select class="custom-select form-control" id="nombre">
                        <option selected
                                value="<%out.print(usuarios.get(0));%>"><%out.print(usuarios.get(0));%></option>
                        <%
                            for (int k = 1; k < usuarios.size(); k++) {
                                out.print("<option value=\"" + usuarios.get(k) + "\">" + usuarios.get(k) + "</option>");
                            }
                        %>
                    </select>
                </div>
                <div class="col-auto ">
                    <label for="acceso" class="col-form-label">Nivel de Acceso</label>
                    <select class="custom-select" id="acceso" name="acceso">
                        <option selected value="1">Fabrica</option>
                        <option value="2">Sala de Ventas</option>
                        <option value="3">Administracion</option>
                        <option value="0">Bloquear</option>
                    </select>
                </div>
                <div class="col-auto pt-4">
                    <button type="submit" class="btn btn-warning">Confirmar</button>
                </div>
            </div>
        </form>
    </div>
    <hr>
    <h3 class="pt-2 text-left">Crear</h3>
    <div class="row">
        <form class="form ml-5 align-items-center" action="../../servlets/admin/nuevo-usuario-servlet" method="post">
            <div class="form-row align-items-center">
                <div class="col-auto ">
                    <label for="nombre-nuevo" class="col-form-label">Nombre de Usuario:</label>
                    <input type="text" maxlength="25" class="form-inline" required name="nombre-nuevo"
                           id="nombre-nuevo" placeholder="Ingresa el Nuevo Nombre de Usuario">
                </div>
                <div class="col-auto">
                    <label for="password" class="col-form-label">Contraseña:</label>
                    <input type="password" maxlength="32" class="form-inline" required name="password"
                           id="password" placeholder="Ingresa la Contraseña">
                </div>
                <div class="col-auto form-group pt-2">
                    <label for="acceso-nuevo">Nivel de Acceso</label>
                    <select class="custom-select" id="acceso-nuevo" name="acceso-nuevo">
                        <option selected value="1">Fabrica</option>
                        <option value="2">Sala de Ventas</option>
                        <option value="3">Administracion</option>
                        <option value="0">Bloquear</option>
                    </select>
                </div>
            </div>
            <div class="form-row container-fluid col-auto">
                <button type="submit" class="btn btn-success">Confirmar</button>
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>
