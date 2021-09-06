package Fabrica;

import java.util.ArrayList;

public class MuebleEnsamble {

    private ArrayList<String> piezasNombre;
    private ArrayList<Integer> piezasCantidad;
    private boolean ensamblable;
    private String nombreMueble;
    private String matNecesarios;

    public MuebleEnsamble(ArrayList<String> piezasNombre, ArrayList<Integer>piezasCantidad, String nombreMueble){
        this.piezasNombre = piezasNombre;
        this.piezasCantidad = piezasCantidad;
        this.nombreMueble = nombreMueble;
        matNecesarios = formarMaterialesNecesarios();
    }

    public String formarMaterialesNecesarios(){
        String completa = "";
        for (int i = 0;i < piezasNombre.size();i++){
            completa = completa + piezasNombre.get(i)+" x "+piezasCantidad.get(i);
            if (i<piezasNombre.size()-1){
                completa = completa+",";
            }
        }
        return completa;
    }

    public ArrayList<String> getPiezasNombre() {
        return piezasNombre;
    }

    public void setPiezasNombre(ArrayList<String> piezasNombre) {
        this.piezasNombre = piezasNombre;
    }

    public ArrayList<Integer> getPiezasCantidad() {
        return piezasCantidad;
    }

    public void setPiezasCantidad(ArrayList<Integer> piezasCantidad) {
        this.piezasCantidad = piezasCantidad;
    }

    public String getMatNecesarios() {
        return matNecesarios;
    }

    public void setMatNecesarios(String matNecesarios) {
        this.matNecesarios = matNecesarios;
    }

    public boolean isEnsamblable() {
        return ensamblable;
    }

    public void setEnsamblable(boolean ensamblable) {
        this.ensamblable = ensamblable;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }
}
