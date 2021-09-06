package Servlets.Ventas;

import DatabaseLink.Connector;
import Ventas.Caja;
import Ventas.Factura;

import javax.print.attribute.standard.MediaSize;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistroServlet",urlPatterns = {"/servlets/ventas/registro-servlet"})
public class RegistroServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        Caja caja = new Caja();
        Connector connector = new Connector();
        Factura factura = (Factura) request.getSession().getAttribute("factura");
        request.getSession().removeAttribute("factura");
        caja.actualizarProductos(factura,connector);
        request.removeAttribute("listo");
        connector.closeThis();
        request.setAttribute("complete","Se Ha registrado la compra");
        request.getSession().removeAttribute("excepcion-id");
        request.getSession().removeAttribute("carrito");
        try {
            response.sendRedirect("venta-servlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
