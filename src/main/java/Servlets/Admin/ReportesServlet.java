package Servlets.Admin;

import Admin.OrganizadorReporte;
import Admin.Reporte;
import DatabaseLink.Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "ReportesServlet",urlPatterns = {"/servlets/admin/reportes-servlet"})
public class ReportesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        Connector connector = new Connector();
        String tipoReporte = request.getParameter("tipo-reporte");
        String fechaInicio = "";
        String fechaFinal = "";
        Reporte reporte = null;
        fechaInicio = String.valueOf(request.getParameter
                ("fecha-inicial"));
        System.out.println(fechaInicio);
        System.out.println((request.getParameter
                ("fecha-inicial")));
        fechaFinal = String.valueOf((request.getParameter
                ("fecha-final")));
        try{
            reporte = new OrganizadorReporte().organizarReporte(tipoReporte,connector,fechaInicio,fechaFinal);
            request.setAttribute("reporte",reporte);
            request.setAttribute("listo",true);
            request.getRequestDispatcher("../../areas/admin/reportes.jsp").forward(request,response);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
