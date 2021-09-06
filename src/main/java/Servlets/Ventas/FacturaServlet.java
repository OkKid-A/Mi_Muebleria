package Servlets.Ventas;

import DatabaseLink.Connector;
import User.Usuario;
import Ventas.Caja;
import Ventas.CarroVentas;
import Ventas.Factura;
import Ventas.MuebleProducto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@WebServlet(name = "FacturaServlet", urlPatterns = {"/servlets/ventas/factura-servlet"})
public class FacturaServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connector connnector = new Connector();
        Optional<CarroVentas> carrito = (Optional<CarroVentas>) request.getSession().getAttribute("carrito");
        Caja caja = new Caja();
        String nit = request.getParameter("nit");
        System.out.println(nit);
        ArrayList<Integer> mueblesFactura = carrito.get().getIdsEnCarro();
        try {
            if (caja.revisarNit(nit, connnector)||Boolean.valueOf(request.getParameter("listo"))) {
                Factura aCancelar = caja.procesarFactura(nit, ((Usuario) request.getSession(true).getAttribute
                        ("actual-user")).getNombre(), mueblesFactura, connnector);
                aCancelar.setFechaCompra(Date.from(Instant.now()));
                request.setAttribute("factura",aCancelar);
                request.getSession(true).setAttribute("factura",aCancelar);
                request.getRequestDispatcher("../../areas/ventas/factura.jsp").forward(request, response);
            } else {
                request.setAttribute("nit", request.getParameter("int"));
                connnector.closeThis();
                request.getRequestDispatcher("../../areas/ventas/crear-cliente.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
