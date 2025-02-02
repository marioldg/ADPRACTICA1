package DTO;

import java.io.Serializable;

public class EntrenadorDTO implements Serializable {
    private long id;
    private String nombre;
    private String nacionalidad;
    private CarnetDTO carnet;

    public EntrenadorDTO() {
        super();
    }

    public EntrenadorDTO(String nombre, String nacionalidad, CarnetDTO carnet) {
        this.id = carnet.getIdEntrenador();
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.carnet = carnet;
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

    public CarnetDTO getCarnet() {
        return carnet;
    }

    public void setCarnet(CarnetDTO carnet) {
        this.carnet = carnet;
    }

    @Override
    public String toString() {
        return "EntrenadorDTO [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", carnet=" + carnet + "]";
    }

    /**
     * Método para exportar los datos del entrenador en formato XML.
     * Se le puede pasar una ruta, si no se proporciona una, se asigna una por defecto.
     * En este caso, la ruta por defecto es "src/main/Files/".
     *
     * @param ruta la ruta donde se desea exportar el archivo XML.
     */
    public void exportarA_XML(String ruta) {
        if (ruta == null || ruta.isEmpty()) {
            ruta = "src/main/Files/";
        }

        // Aquí implementar la lógica para exportar los datos del entrenador en formato XML
        // utilizando la librería de XML que prefieras (ej. JAXB, XStream, etc.)
    }
}
