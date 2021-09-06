<%@ page import="Ventas.MuebleProducto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
      User: Zemoge23
      Date: 4/09/2021
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/includes/resources.jsp"/>
<head>
    <title>Ventas</title>
</head>
<%ArrayList<MuebleProducto> lista = ((ArrayList<MuebleProducto>) (request.getAttribute("productos")));%>
<body>
<jsp:include page="/includes/general-header.jsp"/>
<script type="text/javascript">
    var mensaje = '<%=session.getAttribute("complete")%>';
    if (mensaje != "null") {
        function facturaPasada(){
            alert("Se ha registrado la factura");
            <%request.removeAttribute("complete");%>
        }
    }
</script>
<h1 class="pt-5 text-center ml-2 mt-5">Venta de Muebles</h1>
<div class=" pt-5 container justify-content-center ">
    <div class="row">
        <div class="col">
            <form class="form-group mr-5 mt-1" method="post" action="../../servlets/ventas/venta-servlet">
                <div class="form-row form-groupmt-2 col-md-7">
                    <label for="id">ID Mueble</label>
                    <input type="number" min="0" class="form-control" max="9999999999" name="id" id="id" required
                           placeholder="Ingresa el ID del mueble">
                </div>
                <div>
                    <%! String error = ""; %>
                    <% error = (String) session.getAttribute("excepcion-id");%>
                    <% if (error != null) {
                        if (error.equals("existente")) { %>
                    <div class="form-check-inline" style="color : red">Ese producto ya esta en el carrito.</div>
                    <% } else if (error.equals("nuevo")) { %>
                    <div class="form-check-inline" style="color : green">Se ha agregado el producto al carrito.</div>
                    <% }
                    } %>
                </div>
                <div class="form-row">
                    <button type="submit" class="btn btn-info mt-1">Agregar al Carrito</button>
                </div>
            </form>
        </div>
        <div class="col">
            <form class="form-group ml-5" action="../../servlets/ventas/factura-servlet" method="get">
                <div class="form-row">
                    <div class="col-md-7 text-left form-group mt-2">
                        <label for="id">NIT</label>
                        <input type="text" maxlength="10" class="form-control" required name="nit" id="nit"
                               placeholder="Ingresa el NIT del cliente">
                    </div>
                </div>
                <div class="form-row">
                    <button type="submit" class="btn btn-success">Continuar a Caja</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="container-md">
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
            </table>
        </div>
    </div>
</div>
<script type="text/javascript"> window.onload = facturaPasada; </script>
</body>
</html>
