package Lector;

import DatabaseLink.Connector;

import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Lector.NombresInstruc.*;

public class CargadorArchivo {

    public static final String SEPARADOR = File.separator;
    private Lector lector;
    private ArrayList<String[]> instruccionesFinal;
    private ArrayList<String[]> piezasBP;
    private ArrayList<ErrorLectura> errores;
    private Connector connector;
    private ArrayList<String> instrucLeidas;

    public CargadorArchivo() {
        lector = new Lector();
        connector = new Connector();
        instruccionesFinal = new ArrayList<>();
        piezasBP = new ArrayList<>();
    }

    public void cargarArchivo() {
        //instrucLeidas = lector.leerDocumentoPorLineas(textoArchivo);
        for (int k = 0; k < instrucLeidas.size(); k++) {
            dividirInstru(instrucLeidas.get(k));
            procesarInstrucFinales();
        }
        connector.closeThis();
    }

    public void dividirInstru(String instruccion) {
        try {
            String[] primeraDivision = instruccion.split("\\(");
            String[] limpiar = primeraDivision[1].split("\\)");
            String[] segundaDivision = limpiar[0].split(",");
            for (int k = 0; k < segundaDivision.length; k++) {
                char[] conversion = segundaDivision[k].toCharArray();
                if (conversion[0] == ' ') {
                    char[] limpio = new char[conversion.length - 1];
                    for (int l = 0; l < conversion.length - 1; l++) {
                        limpio[l] = conversion[l + 1];
                    }
                    segundaDivision[k] = String.valueOf(limpio);
                }
            }
            separarInstruc(segundaDivision, primeraDivision[0]);
        }catch (NullPointerException|IndexOutOfBoundsException e){

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void separarInstruc(String[] elementos, String tipoInstru) throws Exception {
        NombresInstruc tipo = NombresInstruc.valueOf(tipoInstru);
        switch (tipo) {
            case PIEZA:
                ResultSet comp = connector.selectFrom("SELECT tipo, valor FROM pieza WHERE tipo = \'" + elementos[0]
                        + "\' AND valor = " + elementos[1]);
                try {
                    if (!comp.next()) {
                        connector.insertTo("INSERT INTO pieza (tipo,valor,cantidad)" + "VALUES (?, ?, ?);",
                                new String[]{elementos[0], elementos[1], "1"});
                    } else {
                        try {
                            ResultSet resultSet = (connector.selectFrom("SELECT cantidad FROM pieza WHERE tipo = \'" + elementos[0] + "\';"));
                            resultSet.next();
                            connector.update("UPDATE pieza SET cantidad = ? WHERE tipo = ?;",
                                    new String[]{""+(Integer.parseInt(resultSet.getString("cantidad"))+1),elementos[0]});
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case MUEBLE:
                connector.insertTo("INSERT INTO mueble_tipo(nombre,precio)" + "VALUES (?,?);", new String[]{elementos[0], elementos[1]});
                break;
            case ENSAMBLAR_MUEBLE:
                instruccionesFinal.add(elementos);
                break;
            case CLIENTE:
                System.out.println(elementos.length);
                if (elementos.length == 3) {
                    connector.insertTo("INSERT INTO cliente(nombre,nit,direccion)" + "VALUES (?,?,?);",
                            new String[]{elementos[0], elementos[1], elementos[2]});
                } else {
                    connector.insertTo("INSERT INTO cliente(nombre,nit,direccion,municipio,departamento)" + "VALUES (?,?,?,?,?);",
                            new String[]{elementos[0], elementos[1], elementos[2], elementos[3], elementos[4]});
                }
                break;
            case ENSAMBLE_PIEZAS:
                piezasBP.add(elementos);
                break;
            case USUARIO:
                connector.insertTo("INSERT INTO usuario(nombre_usuario,tipo,password)" + "VALUES (?,?,MD5(?));",new String[]
                        {elementos[0],elementos[2],elementos[1]});
        }
    }

    public void procesarInstrucFinales(){
        for (int k = 0;k < piezasBP.size();k++) {
            String[] elementos = piezasBP.get(k);
            connector.insertTo("INSERT INTO mueble_ensamble_parte(mueble,pieza,cantidad)" + "VALUES (?,?,?);",
                    new String[]{elementos[0], elementos[1], elementos[2]});
        }
        for (int i = 0;i < instruccionesFinal.size();i++){

        }
    }

    public void setInstrucLeidas(ArrayList<String> instrucLeidas) {
        this.instrucLeidas = instrucLeidas;
    }

    public ArrayList<String[]> getInstruccionesFinal() {
        return instruccionesFinal;
    }
}
