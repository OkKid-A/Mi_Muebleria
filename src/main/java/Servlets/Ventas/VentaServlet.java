package Servlets.Ventas;

import DatabaseLink.Connector;
import Excepciones.ExistenteException;
import Ventas.CarroVentas;
import Ventas.MuebleProducto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "VentaServlet", urlPatterns = {"/servlets/ventas/venta-servlet"})
public class VentaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Connector connector = new Connector();
        CarroVentas carroVentas = new CarroVentas();
        ArrayList<MuebleProducto> listaProductos = carroVentas.recolectarProductos(connector);
        request.setAttribute("productos", listaProductos);
        connector.closeThis();
        try {
            request.getRequestDispatcher("../../areas/ventas/ventas.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Optional<CarroVentas> idsCarrito = (Optional<CarroVentas>) request.getSession(true).getAttribute("carrito");
        CarroVentas carrito = null;
        try{
            if (idsCarrito.isPresent()) {
                System.out.println(12);
                carrito = idsCarrito.get();
            }
        }catch (NullPointerException e){
            carrito = new CarroVentas();
        }
        try {
            carrito.agregarProducto(Integer.parseInt(request.getParameter("id")));
            System.out.println("?");
            idsCarrito = Optional.of(carrito);
            request.getSession().setAttribute("carrito", idsCarrito);
            request.getSession().setAttribute("excepcion-id", "nuevo");
            response.sendRedirect("venta-servlet");
        } catch (ExistenteException e) {
            request.getSession().setAttribute("excepcion-id", "existente");
            try {
                response.sendRedirect("venta-servlet");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
