package User;

public enum Acceso {

    ADMIN(3),
    VENTAS(2),
    FABRICA(1),
    DENEGADO(0);

    private int levelAccess;

    Acceso(int levelAccess) {
        this.levelAccess = levelAccess;
    }

    public static Acceso seleccionarAcceso(int nivel) {
        switch (nivel) {
            case 0:
                return DENEGADO;
            case 1:
                return FABRICA;
            case 2:
                return VENTAS;
            case 3:
                return ADMIN;
            default:
                return null;
        }
    }

    public String seleccionarMainLink() {
        switch (this.levelAccess) {
            case 0:
                return "DENEGADO";
            case 1:
                return "../servlets/fabrica-servlet";
            case 2:
                return "../ventas/venta-servlet";
            case 3:
                return "../../servlets/admin/usuarios-servlet";
            default:
                return null;
        }
    }

    public String seleccionarSecondLink() {
        switch (this.levelAccess) {
            case 0:
                return "DENEGADO";
            case 1:
                return "../servlets/historial-ensamble-servlet";
            case 2:
                return "VENTAS";
            case 3:
                return "MUEBLES";
            default:
                return null;
        }
    }

    public String seleccionarThirdLink() {
        switch (this.levelAccess) {
            case 0:
                return "DENEGADO";
            case 1:
                return "../servlets/ensamblar-servlet";
            case 2:
                return "VENTAS";
            case 3:
                return "../../areas/admin/reportes.jsp";
            default:
                return null;
        }
    }

    public String seleccionarMainLabel() {
        switch (this.levelAccess) {
            case 0:
                return "DENEGADO";
            case 1:
                return "Ensamble";
            case 2:
                return "Nueva Venta";
            case 3:
                return "Usuarios";
            default:
                return null;
        }
    }

    public String seleccionarSecondLabel() {
        switch (this.levelAccess) {
            case 0:
                return "DENEGADO";
            case 1:
                return "Historial";
            case 2:
                return "Devoluciones";
            case 3:
                return "Tipos de Muebles";
            default:
                return null;
        }
    }

    public String seleccionarThirdLabel() {
        switch (this.levelAccess) {
            case 0:
                return "DENEGADO";
            case 1:
                return "Inventario";
            case 2:
                return "Consultas";
            case 3:
                return "Reportes";
            default:
                return null;
        }
    }

    public String seleccionarCerrarSesion() {
        switch (this.levelAccess) {
            case 0:
                return "DENEGADO";
            case 1:
                return "../servlets/";
            case 2:
                return "Consultas";
            case 3:
                return "Reportes";
            default:
                return null;
        }
    }

    public int getLevelAccess() {
        return levelAccess;
    }
}
