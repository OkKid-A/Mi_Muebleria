package Servlets.Fabrica;

import DatabaseLink.Connector;
import Fabrica.InventarioFabrica;
import Fabrica.Pieza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditInventario", urlPatterns = {"/servlets/edit-inventario-servlet"})
public class EditInventarioServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InventarioFabrica inventarioFabrica = new InventarioFabrica();
        Pieza aMod = inventarioFabrica.buscarPieza(new Connector(),Integer. parseInt(request.getParameter("id")));
        request.setAttribute("pieza",aMod);
        request.getRequestDispatcher("../areas/fabrica/edit.jsp").forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Connector connector = new Connector();
        connector.insertTo("DELETE FROM piezas WHERE id = ?;", new String[]{request.getParameter("action")});
        connector.closeThis();
        try {
            response.sendRedirect("ensamblar-servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
