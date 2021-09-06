package Ventas;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;

public class Factura {

    private ArrayList<MuebleProducto> listaVendida ;
    private String nit;
    private String nombre;
    private String vendedor;
    private int id;
    private double total;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVendedor() {
        return vendedor;
    }

    public ArrayList<MuebleProducto> getListaVendida() {
        return listaVendida;
    }

    public void setListaVendida(ArrayList<MuebleProducto> listaVendida) {
        this.listaVendida = listaVendida;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
