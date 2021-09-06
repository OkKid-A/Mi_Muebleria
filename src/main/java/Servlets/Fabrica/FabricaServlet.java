package Servlets.Fabrica;

import DatabaseLink.Connector;
import Fabrica.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FabricaServlet", urlPatterns = {"/servlets/fabrica-servlet"})
public class FabricaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MuebleEnsamble> tiposMuebles = new ArrayList<>();
        Fabrica fabrica = new Fabrica();
        tiposMuebles = fabrica.obtenerBPs();
        request.setAttribute("tipos-muebles",tiposMuebles);
        request.getRequestDispatcher("../areas/fabrica/ensamble.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connector connector = new Connector();
        String tipo = (String) request.getParameter("nombre");
        String cantidad = (String) request.getParameter("cantidad");
        String valor = (String) request.getParameter("valor");
        connector.update("UPDATE piezas SET valor = ?, cantidad = ?, tipo = ? WHERE id = ?;",new String[]{valor,cantidad,tipo});
        response.sendRedirect("ensamblar-servlet.jsp");
    }
}
