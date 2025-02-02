package DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class CombateDTO implements Serializable {
    private LocalDate fecha;
    private long id;
    private int idTorneo;

    public CombateDTO() {
        super();
    }

    public CombateDTO(LocalDate fecha, int idTorneo) {
        this.fecha = fecha;
        this.idTorneo = idTorneo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    @Override
    public String toString() {
        return "CombateDTO [fecha=" + fecha + ", id=" + id + ", idTorneo=" + idTorneo + "]";
    }

    /**
     * Método para exportar los datos del combate en formato XML.
     * Se le puede pasar una ruta, si no se proporciona una, se asigna una por defecto.
     * En este caso, la ruta por defecto es "src/main/Files/".
     *
     * @param ruta la ruta donde se desea exportar el archivo XML.
     */
    public void exportarA_XML(String ruta) {
        if (ruta == null || ruta.isEmpty()) {
            ruta = "src/main/Files/";
        }

        // Aquí implementar la lógica para exportar los datos del combate en formato XML
        // utilizando la librería de XML que prefieras (ej. JAXB, XStream, etc.)
    }
}
