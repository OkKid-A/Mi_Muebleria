package Servlets.Fabrica;

import DatabaseLink.Connector;
import Fabrica.InventarioFabrica;
import Fabrica.RegistroEnsamble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HistorialEnsambleServlet", urlPatterns = {"/servlets/historial-ensamble-servlet"})
public class HistorialEnsambleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Connector connector = new Connector();
        InventarioFabrica inventarioFabrica = new InventarioFabrica();
        ArrayList<RegistroEnsamble> registros = inventarioFabrica.recolectarRegistrosEnsamble(connector);
        request.setAttribute("registros", registros);
        try {
            request.getRequestDispatcher("../areas/fabrica/historial-ensamble.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
