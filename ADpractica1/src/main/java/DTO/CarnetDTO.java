package DTO;

import java.io.Serializable;

public class CarnetDTO implements Serializable {
    private long idEntrenador;
    private String numeroCarnet;

    public CarnetDTO() {
        super();
    }

    public CarnetDTO(long idEntrenador, String numeroCarnet) {
        super();
        this.idEntrenador = idEntrenador;
        this.numeroCarnet = numeroCarnet;
    }

    public long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNumeroCarnet() {
        return numeroCarnet;
    }

    public void setNumeroCarnet(String numeroCarnet) {
        this.numeroCarnet = numeroCarnet;
    }

    @Override
    public String toString() {
        return "CarnetDTO [idEntrenador=" + idEntrenador + ", numeroCarnet=" + numeroCarnet + "]";
    }
}
