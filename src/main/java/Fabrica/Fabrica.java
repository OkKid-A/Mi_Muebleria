package Fabrica;

import DatabaseLink.Connector;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Fabrica {

    Connector connector;

    public Fabrica() {
        connector = new Connector();
    }

    public List<MuebleEnsamble> obtenerBPs() {
        connector = new Connector();
        ResultSet main = connector.selectFrom("SELECT nombre FROM mueble_tipo;");
        ArrayList<MuebleEnsamble> muebles = new ArrayList<>();
        while (true) {
            try {
                if (!main.next()) break;
                String nombreMueble = main.getString(1);
                ResultSet partes = connector.selectFrom("SELECT pieza, cantidad FROM mueble_ensamble_parte WHERE mueble = \'" + nombreMueble + "\';");
                System.out.println(main.getString("nombre"));
                ArrayList<String> partesNombres = new ArrayList<>();
                ArrayList<Integer> partesCantidad = new ArrayList<>();
                while (partes.next()) {
                    partesNombres.add(partes.getString("pieza"));
                    partesCantidad.add(partes.getInt("cantidad"));
                }
                MuebleEnsamble muebleEnsamble = new MuebleEnsamble(partesNombres, partesCantidad, nombreMueble);
                muebles.add(muebleEnsamble);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connector.closeThis();
        return muebles;
    }

    public void ensamblar(Connector connector, String muebleBP, String usuarioSesion) {
        ResultSet planos = connector.selectFrom("SELECT pieza, cantidad FROM mueble_ensamble_parte WHERE mueble = \'" + muebleBP + "\';");
        boolean esEnsamblable = false;
        try {
            while (planos.next()) {
                esEnsamblable = esEnsamblable(planos.getString("pieza"), Integer.parseInt(planos.getString("cantidad")));
                System.out.println(esEnsamblable);
            }
            planos.beforeFirst();
            if (esEnsamblable) {
                int costoTotal = 0;
                while (planos.next()) {
                    ResultSet piezasATrabajar = connector.selectFrom("SELECT cantidad, valor FROM pieza  WHERE " +
                            "tipo = \'" + planos.getString("pieza") + "\' ORDER BY valor;");
                    int total = 0;
                    int cantidadPieza = Integer.parseInt(planos.getString("cantidad"));
                    while (piezasATrabajar.next() && total < cantidadPieza) {
                        int cantidadRow = Integer.parseInt(piezasATrabajar.getString("cantidad"));
                        String valorRow = piezasATrabajar.getString("valor");
                        if (cantidadRow < cantidadPieza - total) {
                            total += cantidadRow;
                            costoTotal += (cantidadRow * Double.parseDouble(valorRow));
                            connector.insertTo("DELETE FROM piezas WHERE valor = ? AND cantidad = ?", new String[]{valorRow, cantidadRow + ""});
                        } else {
                            costoTotal += ((cantidadPieza - total) * Double.parseDouble(valorRow));
                            connector.update("UPDATE pieza SET cantidad = cantidad - " + (cantidadPieza - total)
                                    + " WHERE valor = ? AND cantidad = ?", new String[]{valorRow, cantidadRow + ""});
                            total = cantidadPieza;
                        }
                    }
                }
                connector.insertTo("INSERT INTO mueble_producto(tipo_mueble, costo, usuario, vendido, fecha_creacion)" +
                        " VALUES (?,?,?,false,NOW());",new String[]{muebleBP, costoTotal+"",usuarioSesion});
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

        }
    }

    public boolean esEnsamblable(String pieza, int cantidad) {
        ResultSet piezasDisponibles = connector.selectFrom("SELECT cantidad FROM pieza WHERE tipo = \'" + pieza + "\';");
        int total = 0;
        try {
            while (piezasDisponibles.next()) {
                int cantidadParcial = Integer.parseInt(piezasDisponibles.getString("cantidad"));
                total += cantidadParcial;
                if (cantidadParcial >= cantidad || total >= cantidad) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public static int comprobarTamanio(ResultSet resultSet) {
        ResultSet muestra = resultSet;
        int tamanio = 0;
        try {
            int cont = 0;
            while (muestra.next()) {
                cont++;
            }
            tamanio = cont;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tamanio;
    }
}
