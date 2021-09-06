package Ventas;

import DatabaseLink.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Caja {
    public Caja() {

    }

    public Factura procesarFactura(String nit, String usuario, ArrayList<Integer> idsCompra, Connector connector) {
        Factura factura = new Factura();
        factura.setNit(nit);
        factura.setFechaCompra(Date.from(Instant.now()));
        factura.setVendedor(usuario);
        ArrayList<MuebleProducto> listadoCompleto = new ArrayList<>();
        Double total = 0.00;
        try {
            for (int k = 0; k < idsCompra.size(); k++) {
                MuebleProducto producto = new MuebleProducto();
                ResultSet compra = connector.selectFrom("SELECT tipo_mueble, precio FROM mueble_producto WHERE id = " + idsCompra.get(k) + ";");
                compra.next();
                producto.setPrecio(Double.parseDouble(compra.getString("precio")));
                total += producto.getPrecio();
                producto.setNombreProducto(compra.getString("tipo_mueble"));
                producto.setId(idsCompra.get(k));
                listadoCompleto.add(producto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        factura.setListaVendida(listadoCompleto);
        factura.setTotal(total);
        ResultSet clienteInfo = connector.selectFrom("SELECT nombre FROM cliente WHERE nit = \'" + nit + "\';");
        try {
            clienteInfo.next();
            factura.setNombre(clienteInfo.getString("nombre"));
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return factura;
    }

    public void actualizarProductos(Factura factura, Connector connector) {
        ArrayList<MuebleProducto> muebleProductos = factura.getListaVendida();
        for (int k = 0; k < muebleProductos.size(); k++) {
            ResultSet siguienteAuto = connector.selectFrom("SHOW TABLE STATUS LIKE 'factura'");
            try {
                siguienteAuto.next();
                int id = siguienteAuto.getInt("Auto_increment");
                factura.setId(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connector.update("UPDATE mueble_producto SET vendido = true WHERE id = ?;", new String[]{muebleProductos.get(k).getId() + ""});
            connector.insertTo("INSERT INTO venta(id_producto,id_factura) VALUES(?,?);", new String[]{muebleProductos.get(k).getId() + "", factura.getId() + ""});
            connector.insertTo("INSERT INTO factura(cliente,total,fecha_compra,vendedor) VALUES (?,?,?,?);",
                    new String[]{factura.getNit(), factura.getTotal() + "", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(factura.getFechaCompra()),
                            factura.getVendedor()});
        }
    }

    public boolean revisarNit(String nit, Connector connector) {
        ResultSet datosCliente = connector.selectFrom("SELECT nit FROM cliente");
        try {
            while (datosCliente.next()) {
                System.out.println(datosCliente.getString("nit"));
                if (datosCliente.getString("nit").equals(nit)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
