package Servlets.Fabrica;

import DatabaseLink.Connector;
import Fabrica.*;
import User.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EnsamblarServlet" ,urlPatterns = {"/servlets/ensamblar-servlet"})
public class EnsamblarServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        Connector connector = new Connector();
        InventarioFabrica inventario = new InventarioFabrica();
        request.setAttribute("inventario",inventario.recolectarPiezas(connector));
        connector.closeThis();
        try {
            request.getRequestDispatcher("../areas/fabrica/inventario-piezas.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        Connector connector = new Connector();
        Fabrica fabrica = new Fabrica();
        fabrica.ensamblar(connector,request.getParameter("action"),((Usuario) request.getSession().getAttribute("actual-user")).getNombre());
        try {
            response.getWriter().write("Se ha ensamblado "+request.getParameter("action"));
            response.sendRedirect("fabrica-servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
