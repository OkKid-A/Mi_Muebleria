package Servlets;

import Lector.CargadorArchivo;
import Lector.Lector;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "UploadServlet", urlPatterns = {"/servlets/upload-servlet"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

        CargadorArchivo cargadorArchivo = new CargadorArchivo();
        InputStream archivo = null;
        try {
            archivo = (request.getPart("pathFile")).getInputStream();
            BufferedReader red = new BufferedReader(new InputStreamReader(archivo, StandardCharsets.UTF_8));
            Lector lector = new Lector();
            cargadorArchivo.setInstrucLeidas(lector.leerDocumentoPorLineas(red));
            cargadorArchivo.cargarArchivo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("../login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
