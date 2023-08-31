package Admin;

import DatabaseLink.Connector;
import Ventas.Venta;
import com.mysql.cj.protocol.Resultset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrganizadorReporte {


    public OrganizadorReporte() {

    }

    public Reporte organizarReporte(String tipoReporte, Connector connector, String fechaInicio, String fechaFin) throws SQLException {
        TipoReporte tipo = TipoReporte.seleccionarTipo(tipoReporte);
        Reporte reporte = new Reporte();
        reporte.setTipoReporte(tipo);
        reporte.setReporteHeaders(TipoReporte.seleccionarHeader(tipo));
        switch (tipo) {
            case VENTAS:
                Double total = 0.00;
                ArrayList<Venta> idVentas = new ArrayList<>();
                ResultSet idsVEnta = null;
                if (fechaFin == "" || fechaFin == ":00" || fechaInicio == "" || fechaInicio == ":00") {
                    idsVEnta = connector.selectFrom("SELECT id_producto, id_factura FROM venta;");
                } else {
                    idsVEnta = connector.selectFrom("SELECT id_producto, id_factura FROM venta WHERE id_factura IN (SELECT numero_factura FROM factura WHERE fecha_compra BETWEEN STR_TO_DATE('2021-09-01T15:25','%Y-%m-%dT%H:%i') AND STR_TO_DATE('2021-09-06T15:25','%Y-%m-%dT%H:%i'));");
                }
                while (idsVEnta.next()) {
                    Venta venta = new Venta();
                    venta.setId(String.valueOf(idsVEnta.getInt("id_producto")));
                    venta.setIdFactura(String.valueOf(idsVEnta.getString("id_factura")));
                    ResultSet datosFactura = connector.selectFrom("SELECT fecha_compra FROM factura " +
                            "WHERE numero_factura = \'" + venta.getIdFactura() + "\';");
                    venta.setFechaCompra(datosFactura.getString("fecha_compra"));
                    ResultSet datosProducto = connector.selectFrom("SELECT nombre, precio FROM mueble_tipo" +
                            "WHERE nombre = (SELECT tipo_mueble FROM mueble_producto WHERE id = \'" + venta.getId() + "\');");
                    venta.setValor(datosProducto.getString("precio"));
                    total += Double.parseDouble(venta.getValor());
                    venta.setNombreProducto(datosProducto.getString("nombre"));
                    idVentas.add(venta);
                }
                reporte.setVentas(idVentas);
                reporte.setTipoReporte(TipoReporte.VENTAS);
                reporte.setTotal(total);
                return reporte;
            case GANANCIAS:
                total = 0.00;
                idVentas = new ArrayList<>();
                idsVEnta = null;
                if (fechaFin.equals("") || fechaFin.equals(":00") || fechaInicio.equals("") || fechaInicio.equals(":00")) {
                    idsVEnta = connector.selectFrom("SELECT id_producto, id_factura FROM venta;");
                } else {
                    idsVEnta = connector.selectFrom("SELECT id_producto, id_factura FROM venta WHERE id_factura IN (SELECT numero_factura FROM factura WHERE fecha_compra BETWEEN STR_TO_DATE(\'"+fechaInicio+"\','%Y-%m-%dT%H:%i') AND STR_TO_DATE(\'"+fechaFin+"\','%Y-%m-%dT%H:%i'));");
                }
                while (idsVEnta.next()) {
                    Venta venta = new Venta();
                    venta.setId(String.valueOf(idsVEnta.getInt("id_producto")));
                    venta.setIdFactura(String.valueOf(idsVEnta.getString("id_factura")));
                    try (ResultSet datosFactura = connector.selectFrom("SELECT fecha_compra FROM factura " +
                            "WHERE numero_factura = \'" + venta.getIdFactura() + "\';")) {
                        System.out.println(datosFactura.getString("fecha_compra"));
                        venta.setFechaCompra(datosFactura.getString("fecha_compra"));
                    }
                    ResultSet datosProducto = connector.selectFrom("SELECT nombre, precio FROM mueble_tipo" +
                            "WHERE nombre = (SELECT tipo_mueble FROM mueble_producto WHERE id = \'" + venta.getId() + "\');");
                    ResultSet costos = connector.selectFrom("SELECT precio FROM mueble_producto Where id = \'" + venta.getId() + "\');");
                    venta.setValor(String.valueOf(Double.parseDouble(venta.getValor()) - Double.parseDouble(costos.getString("precio"))));
                    total += Double.parseDouble(venta.getValor()) - Double.parseDouble(costos.getString("precio"));
                    venta.setNombreProducto(datosProducto.getString("nombre"));
                    idVentas.add(venta);
                }
                reporte.setVentas(idVentas);
                reporte.setTipoReporte(TipoReporte.VENTAS);
                reporte.setTotal(total);
            default:
                return null;
        }
    }


}

