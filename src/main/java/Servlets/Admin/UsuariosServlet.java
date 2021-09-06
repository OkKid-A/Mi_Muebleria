package Servlets.Admin;

import DatabaseLink.Connector;
import User.Acceso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.HandshakeRequest;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "UsuariosServlet", urlPatterns = {"/servlets/admin/usuarios-servlet"})
public class UsuariosServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connector connector = new Connector();
        ResultSet usuarios = connector.selectFrom("SELECT nombre_usuario FROM usuario");
        ArrayList<String> usuariosNombre = new ArrayList<>();
        try {
            while (usuarios.next()) {
                usuariosNombre.add(usuarios.getString("nombre_usuario"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connector.closeThis();
        request.setAttribute("lista-usuarios",usuariosNombre);
        try {
            request.getRequestDispatcher("../../areas/admin/admin.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Connector connector = new Connector();
        connector.update("UPDATE usuario SET tipo = ? WHERE nombre_usuario = ?;",
                new String[]{""+(request.getParameter("acceso")),request.getParameter("nombre")});
        try {
            response.sendRedirect("usuarios-servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
