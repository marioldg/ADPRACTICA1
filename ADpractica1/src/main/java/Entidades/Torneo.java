package Entidades;

import java.util.ArrayList;

public class Torneo {
    private int id;
    private String nombre;
    private char codRegion;
    private float puntosVictoria = (int)(Math.random()*51)+50;
    private String nomAdmin;
    private ArrayList<Entrenador> entrenadores = new ArrayList<>();
    private ArrayList<Combate> combates = new ArrayList<>();

    public Torneo(int id, String nombre, char codRegion, float puntosVictoria,String nomAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        this.puntosVictoria = puntosVictoria;
        this.nomAdmin= nomAdmin;
    }

    public Torneo() {

    }
    public Torneo(String nombre, char codRegion, String nomAdmin) {
        this.nombre = nombre;
        this.codRegion = codRegion;
        this.nomAdmin = nomAdmin;
    }


    public String getNomAdmin() {
        return nomAdmin;
    }

    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }

    public char getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(char codRegion) {
        this.codRegion = codRegion;
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

    public float getPuntosVictoria() {
        return puntosVictoria;
    }

    public void setPuntosVictoria(float puntosVictoria) {
        this.puntosVictoria = puntosVictoria;
    }

    public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public void setCombates(ArrayList<Combate> combates) {
        this.combates = combates;
    }

    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public ArrayList<Combate> getCombates() {
        return combates;
    }

    @Override
    public String toString() {
        return "Torneo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", codRegion=" + codRegion +
                ", puntosVictoria=" + puntosVictoria +
                ", nomAdmin='" + nomAdmin + '\'' +
                ", entrenadores=" + entrenadores +
                ", combates=" + combates +
                '}';
    }
}
