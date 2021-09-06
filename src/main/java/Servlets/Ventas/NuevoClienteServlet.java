package Servlets.Ventas;

import DatabaseLink.Connector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/servlets/ventas/cliente-servlet"})
public class NuevoClienteServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Connector connector = new Connector();
        String nombre = request.getParameter("nombre");
        String nit = request.getParameter("nit");
        String direccion = request.getParameter("address");
        try {
            String municipio = request.getParameter("municipio");
            String departamento = request.getParameter("departamento");
            connector.insertTo("INSERT INTO cliente( nombre, nit,direccion,municipio,departamento) VALUES (?,?,?,?,?);",
                    new String[]{nombre, nit, direccion, municipio, departamento});
            System.out.println("done");
        } catch (NullPointerException e) {
            connector.insertTo("INSERT INTO cliente( nombre, nit,direccion) VALUES (?,?,?);", new String[]{nombre, nit, direccion});
            System.out.println("done");
        }
        connector.closeThis();
        try {
            response.sendRedirect("factura-servlet?listo=true&nit="+nit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
