package Ventas;

import DatabaseLink.Connector;
import Excepciones.ExistenteException;

import java.nio.charset.MalformedInputException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarroVentas {

    private ArrayList<Integer> idsEnCarro;

    public CarroVentas(){
        idsEnCarro = new ArrayList<>();
    }

    public ArrayList<MuebleProducto> recolectarProductos(Connector connector){
        ArrayList<MuebleProducto> listaProductos = new ArrayList<>();
        ResultSet lista = connector.selectFrom("SELECT id,tipo_mueble,precio FROM mueble_producto WHERE vendido = 0");
        try {
            while (lista.next()){
                ResultSet paraPrecio = connector.selectFrom("SELECT precio FROM mueble_tipo WHERE nombre = \'"+lista.getString
                        ("tipo_mueble")+"\';");
                paraPrecio.next();
                MuebleProducto muebleProducto = new MuebleProducto();
                muebleProducto.setNombreProducto(lista.getString("tipo_mueble"));
                muebleProducto.setId(lista.getInt("id"));
                muebleProducto.setPrecio(paraPrecio.getDouble("precio"));
                listaProductos.add(muebleProducto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listaProductos;
    }

    public boolean encontrarID(int id){
        for (int k = 0; k < idsEnCarro.size();k++){
            System.out.println(idsEnCarro.get(k) + ""+id);
            if (idsEnCarro.get(k).compareTo(Integer.valueOf(id))==0) {
                return true;
            }
        }
        return false;
    }

    public void agregarProducto(int id) throws ExistenteException {
        System.out.println("mmm");
        boolean hmph = encontrarID(id);
        if (!hmph) {
            System.out.println('k');
            this.idsEnCarro.add(id);
        }else {
            throw new ExistenteException("El producto con ID: " + id + " ya esta en el carro de compras");
        }
    }

    public ArrayList<Integer> getIdsEnCarro() {
        return idsEnCarro;
    }

    public void setIdsEnCarro(ArrayList<Integer> idsEnCarro) {
        this.idsEnCarro = idsEnCarro;
    }
}
