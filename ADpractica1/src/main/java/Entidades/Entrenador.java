package Entidades;


public class Entrenador {
    private long id;
    private String nombre;
    private String nacionalidad;

    public Entrenador(String nombre, String nacionalidad,Carnet carnet) {
        this.id = carnet.getIdEntrenador();
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        //this.carnet=carnet;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\''+
                '}';
    }

    public Entrenador(){

}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


}
