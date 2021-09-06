package Admin;

import Ventas.Venta;

import java.util.ArrayList;

public class Reporte{

    private String[] reporteHeaders;
    private ArrayList<Object> lineas;
    private ArrayList<Venta> ventas;
    private TipoReporte tipoReporte;
    private double total;

    public Reporte(){
    }

    public String[] getReporteHeaders() {
        return reporteHeaders;
    }

    public void setReporteHeaders(String[] reporteHeaders) {
        this.reporteHeaders = reporteHeaders;
    }

    public ArrayList<Object> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Object> lineas) {
        this.lineas = lineas;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public TipoReporte getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
