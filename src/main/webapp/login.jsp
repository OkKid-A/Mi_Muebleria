<%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 25/08/2021
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mi Muebleria - Login </title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<style>
    div.extra{
        text-align: center;
    }

    div.container {
        background: rgba(255,255,255,0)
    }

    a{
        text-align: center;
    }

    input, select, text, input:focus,::selection{
        color: black;
    }

    textarea:focus{
        color: #9adab2;
    }

    label {
        color: #FFFAFA;
    }

    label.copyright{
        color: white;
        opacity: 0.5;
        text-align: center;
    }

    body {
        background: #2e1631 !important;
        font-family: 'sans-serif';
    }

    h1 {
        color: #FFFAFA;
        padding-bottom: 2rem;
        font-family: 'Impact';
    }

    .form-control:focus {
        color: #FFFAFA;
        background-color: #eeffef;
        border: 2px solid #E8D426;
        outline: 0;
        box-shadow: none;
    }

    .btn {
        background: #2b61b9;
        border: #E8D426;
    }

    .btn:hover {
        background: #6893e1;
        border: #E8D426;
    }
</style>
<body>
<div class="pt-5">
    <div container class="container">
        <h1 class="text-center"> Mi Muebleria </h1>
        <div class="row">
            <div class="col-md-5 mx-auto">
                <div class="card-transparent card-body">
                    <form id="submitForm" action="servlets/elector-servlet" method="post" data-parsley-validate=""
                          data-parsley-errors-messages-disabled="true" novalidate="false">
                        <div class="form-group required">
                            <label class="text" for="username" > Ingresa tu Usuario</label>
                            <input type="text" class="form-control text" id="username" required name="username" style=
                                    "color: black" placeholder="Usuario">
                        </div>
                        <div class="form-group required">
                            <label class="d-flex flex-row align-items-center" for="password"> Ingresa tu Contraseña</label>
                            <input type="password" class="form-control" required id="password" name="password" style=
                                    "color: black" placeholder="Contraseña">
                        </div>
                        <div class="form-group pt-0">
                            <button class="btn btn-primary btn-block" type="submit"> Iniciar Sesion</button>
                            <a href="areas/upload.jsp" class="text-center">Subir Archivo de Records</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="pb-5 extra">
    <label class="text-align-center copyright">@ 2021 Code ‘n Bugs</label>
</div>
</body>
</html>
