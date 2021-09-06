<%--
  Created by IntelliJ IDEA.
  User: Zemoge23
  Date: 25/08/2021
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Mi Muebleria - Upload</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<style

></style>
<body>
<div class="pt-5">
    <div class="row">
        <div class="col-sm flex-column">
            <div class="container">
                <form class="was-validated" method="POST" action="../servlets/upload-servlet"
                      enctype="multipart/form-data">
                    <div class="form-group required">
                        <%--@declare id="archivo"--%><label class="text" for="archivo"> Selecciona tu Archivo de
                        Records</label>
                    </div>
                    <div class="form-group custom-file">
                        <input type="file" id="pathFile" name="pathFile" accept="text/csv, .txt" required>
                        <label class="custom-file-label" for="pathFile">Escoge tu Archivo...</label>
                    </div>
                    <button type="submit" class="btn btn-primary-outline btn-block">Subir</button>
                </form>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg">
            <div>

            </div>
        </div>
    </div>
    <script>
        $('#pathFile').on('change', function () {
            //get the file name
            let fileName = $(this).val();
            //replace the "Choose a file" label
            $(this).next('.custom-file-label').html(fileName);
        })
    </script>
</div>
</body>
</html>
