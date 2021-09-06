package Servlets.Admin;

import DatabaseLink.Connector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NuevoUsuarioServlet",urlPatterns = {"/servlets/admin/nuevo-usuario-servlet"})
public class NuevoUsuarioServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        Connector connector = new Connector();
        connector.insertTo("INSERT INTO usuario(nombre_usuario,tipo,password) VALUES (?,?,MD5(?));",new String[]{
                request.getParameter("nombre-nuevo"),""+request.getParameter("acceso-nuevo"),request.getParameter("password")
        });
        try {
            response.sendRedirect("usuarios-servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
