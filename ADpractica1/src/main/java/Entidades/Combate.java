package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;

import static ControlFicheros.leerCredenciales.nombresPaises;

public class Combate {

    private LocalDate fecha;
    private long id;
    private int idTorneo;
    private static ArrayList<Entrenador> luchadores = new ArrayList<>();
    private int idGanador = 0;

    public Combate(LocalDate fecha, int idTorneo) {
        this.fecha = fecha;
        this.idTorneo = idTorneo;
    }

    public Combate(){

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

    public void setLuchadores(ArrayList<Entrenador> luchadores) {
        this.luchadores = luchadores;
    }

    public void setIdGanador(int idGanador) {
        this.idGanador = idGanador;
    }

    public ArrayList<Entrenador> getLuchadores() {
        return luchadores;
    }

    public int getIdGanador() {
        return idGanador;
    }

    public static String participantes(){
        String sol = "\nluchadores{\n";
        for(Entrenador x : luchadores){
            sol += "luchador{\n";
            sol+= "nombre luchador=" + x.getNombre();
            sol += "\n id luchador=" + x.getId();
            sol += "\n}";

        }
        return sol;
    }
}
