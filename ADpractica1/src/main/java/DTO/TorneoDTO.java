package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class TorneoDTO implements Serializable {
    private int id;
    private String nombre;
    private char codRegion;
    private float puntosVictoria;
    private String nomAdmin;

    public TorneoDTO() {
        super();
    }

    public TorneoDTO(int id, String nombre, char codRegion, float puntosVictoria, String nomAdmin) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        this.puntosVictoria = puntosVictoria;
        this.nomAdmin = nomAdmin;
    }

    public TorneoDTO(int id, String nombre, char codRegion, String nomAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        this.nomAdmin = nomAdmin;
        // puntosVictoria asignado por defecto a un valor aleatorio entre 50 y 100
        this.puntosVictoria = (int)(Math.random()*51)+50;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(char codRegion) {
        this.codRegion = codRegion;
    }

    public float getPuntosVictoria() {
        return puntosVictoria;
    }

    public void setPuntosVictoria(float puntosVictoria) {
        this.puntosVictoria = puntosVictoria;
    }

    public String getNomAdmin() {
        return nomAdmin;
    }

    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }

    @Override
    public String toString() {
        return "TorneoDTO [id=" + id + ", nombre=" + nombre + ", codRegion=" + codRegion + ", puntosVictoria="
                + puntosVictoria + ", nomAdmin=" + nomAdmin + "]";
    }
}
