package Fabrica;

import DatabaseLink.Connector;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InventarioFabrica {

    public InventarioFabrica() {

    }

    public ArrayList<Pieza> recolectarPiezas(Connector connector) {
        ArrayList<Pieza> piezas = new ArrayList<>();
        ResultSet piezasEncontradas = connector.selectFrom("SELECT * FROM pieza");
        try {
            while (piezasEncontradas.next()) {
                Pieza piezaEncontrada = new Pieza();
                piezaEncontrada.setNombre(piezasEncontradas.getString("tipo"));
                piezaEncontrada.setCantidad(Integer.parseInt(piezasEncontradas.getString("cantidad")));
                piezaEncontrada.setValor(piezasEncontradas.getString("valor"));
                piezaEncontrada.setId(Integer.parseInt(piezasEncontradas.getString("id")));
                piezas.add(piezaEncontrada);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return piezas;
    }

    public void editarPieza(Connector connector, Pieza pieza) {
        connector.update("UPDATE pieza SET tipo = ?, valor = ?, cantidad = ? WHERE id = ?", new String[]{pieza.getNombre(), pieza.getValor(), String.valueOf(pieza.getCantidad()), pieza.getId() + ""});
    }

    public Pieza buscarPieza(Connector connector, int id) {
        ResultSet piezaEditar = connector.selectFrom("SELECT * FROM pieza WHERE id = " + id);
        Pieza aRegresar = null;
        try {
            if (piezaEditar.next()) {
                aRegresar = new Pieza();
                aRegresar.setNombre(piezaEditar.getString("tipo"));
                aRegresar.setCantidad(Integer.parseInt(piezaEditar.getString("cantidad")));
                aRegresar.setValor(piezaEditar.getString("valor"));
                aRegresar.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        aRegresar.setId(id);
        return aRegresar;
    }

    public ArrayList<RegistroEnsamble> recolectarRegistrosEnsamble(Connector connector) {
        ArrayList<RegistroEnsamble> registros = new ArrayList<>();
        ResultSet encontrados = connector.selectFrom("SELECT tipo_mueble,precio,usuario,fecha_creacion FROM mueble_producto ORDER BY id DESC");
        try {
            while (encontrados.next()) {
                RegistroEnsamble registro = new RegistroEnsamble();
                registro.setTipo(encontrados.getString("tipo_mueble"));
                registro.setCosto(Double.valueOf(encontrados.getString("costo")));
                registro.setUsuarioCreador(encontrados.getString("usuario"));
                registro.setFechaCreacion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(encontrados.getString("fecha_creacion")));
                registros.add(registro);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return registros;
    }
}
