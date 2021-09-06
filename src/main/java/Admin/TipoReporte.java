package Admin;

public enum TipoReporte {

    VENTAS("ventas"),
    GANANCIAS("ganancias");

    private String tipo;

    TipoReporte(String tipo){
        this.tipo = tipo;
    }

    public static TipoReporte seleccionarTipo(String tipoEnviado){
        switch (tipoEnviado){
            case "ventas":
                return VENTAS;
            case "ganancias":
                return GANANCIAS;
            default:
                return null;
        }
    }

    public static String[] seleccionarHeader(TipoReporte tipoReporte){
        switch (tipoReporte){
            case VENTAS:
                return new String[]{"Fecha de Venta","ID","Tipo de Mueble","Precio de Venta"};
            case GANANCIAS:
                return new String[]{"Fecha de Venta","ID","Tipo de Mueble","Ganancia Obtenida"};
            default:
                return null;
        }
    }
}
