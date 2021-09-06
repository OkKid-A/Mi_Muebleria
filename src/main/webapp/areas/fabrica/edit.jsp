<%@ page import="Fabrica.Pieza" %><%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 3/09/2021
  Time: 01:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Pieza pieza = (Pieza) request.getAttribute("pieza");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/includes/resources.jsp"/>
    <title>Editando...</title>
</head>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<div class="card-body">
    <div class="col-auto    ">
        <form class="justify-content-center" method="post" action="../servlets/ensamblar-servlet">
            <div class="mt-5 mb-3">
                <label for="idPieza">ID:</label>
                <label class="form-control" id="idPieza"><%out.print(pieza.getId());%></label>
                <input type="hidden" name="id" value="<%out.print(pieza.getId());%>"/>
            </div>
            <div class="mb-3">
                <label for="nombre">Tipo de Pieza</label>
                <input type="text" class="form-control" maxlength="25" id="nombre" name="nombre" placeholder="Tipo de Pieza" required
                       value="<%out.print(pieza.getNombre());%>">
            </div>
            <div class="mb-3">
                <label for="valor">Valor</label>
                <input type="number" min="0"  class="form-control" id="valor" name="valor" placeholder="Valor" required
                       value="<%out.print(pieza.getValor());%>">
            </div>
            <div class="mb-3">
                <label for="cantidad">Cantidad</label>
                <input type="number" min="0" class="form-control" id="cantidad" name="cantidad" placeholder="Cantidad" required
                       value="<%out.print(pieza.getCantidad());%>">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Guardar Cambios</button>
        </form>
    </div>
</div>
</body>
</html>
