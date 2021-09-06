package User;

public class Usuario {

    public String nombre;
    public Acceso acceso;

    public Usuario(String nombre, Acceso acceso){
        this.nombre = nombre;
        this.acceso = acceso;
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAcceso(Acceso acceso) {
        this.acceso = acceso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
