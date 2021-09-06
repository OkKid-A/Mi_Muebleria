package Servlets;

import DatabaseLink.Connector;
import User.Acceso;
import User.Usuario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ElectorServlet", urlPatterns = {"/servlets/elector-servlet"})
public class ElectorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Usuario user = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String query = String.format("SELECT * FROM usuario WHERE nombre_usuario = %s AND password = MD5(%s)", "'" + username + "'", "'" + password + "'");
        try {
            Connector connector = new Connector();
            ResultSet usuario = connector.selectFrom(query);
            if (!usuario.next()) {
            } else {
                user = new Usuario(usuario.getString("nombre_usuario"),
                        Acceso.seleccionarAcceso(Integer.parseInt(usuario.getString("tipo"))));

                request.getSession(true).setAttribute("actual-user", user);
                establecerLinks(request);
            }
            connector.closeThis();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            switch (user.getAcceso()) {
                case FABRICA:
                    response.sendRedirect("fabrica-servlet");
                    break;
                case VENTAS:
                    response.sendRedirect("ventas/venta-servlet");
                    break;
                case ADMIN:
                    response.sendRedirect("admin/usuarios-servlet");
                    break;
            }
        } catch (IOException |NullPointerException e) {
            try {
                response.sendRedirect("../login.jsp");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    protected void establecerLinks(HttpServletRequest request) {
        request.getSession(true).setAttribute("Main-header-link", ((Usuario) request.getSession(true).getAttribute("actual-user")).getAcceso().seleccionarMainLink());
        request.getSession(true).setAttribute("Main-header", ((Usuario) request.getSession(true).getAttribute("actual-user")).getAcceso().seleccionarMainLabel());
        request.getSession(true).setAttribute("Second-header-link", ((Usuario) request.getSession(true).getAttribute("actual-user")).getAcceso().seleccionarSecondLink());
        request.getSession(true).setAttribute("Second-header", ((Usuario) request.getSession(true).getAttribute("actual-user")).getAcceso().seleccionarSecondLabel());
        request.getSession(true).setAttribute("Last-header-link", ((Usuario) request.getSession(true).getAttribute("actual-user")).getAcceso().seleccionarThirdLink());
        request.getSession(true).setAttribute("Last-header", ((Usuario) request.getSession(true).getAttribute("actual-user")).getAcceso().seleccionarThirdLabel());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(true).removeAttribute("actual-user");
        request.getSession(true).removeAttribute("access");
        try {
            response.sendRedirect("../login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
