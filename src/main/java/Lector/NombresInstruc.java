package Lector;

public enum NombresInstruc {
    ENSAMBLE_PIEZAS("ENSAMBLE_PIEZAS"),
    ENSAMBLAR_MUEBLE("ENSAMBLAR_MUEBLE"),
    CLIENTE("CLIENTE"),
    PIEZA("PIEZA"),
    USUARIO("USUARIO"),
    MUEBLE("MUEBLE");

    private String tipo;

    NombresInstruc(String identificador){
        tipo = identificador;
    }

    public String getTipo() {
        return tipo;

    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
