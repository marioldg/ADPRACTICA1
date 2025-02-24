package DAO;

import DB.Conexion;
import Entidades.Combate;
import Entidades.Entrenador;
import Entidades.Torneo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static DAO.EntrenadorDAOImplementacion.buscarPorId;

public class TorneoDAOImplementacion {
    public static CombateDAOImplementacion combate = new CombateDAOImplementacion();

    private TorneoDAOImplementacion() {
    }


    public static List<Torneo> obtenerTodosLosTorneos() {
        List<Torneo> torneos = new ArrayList<>();
        String sql = "SELECT id, nombre, codRegion, puntosVictoria, idAdmin FROM torneo";

        try (Connection connection = Conexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                torneos.add(mapearResultSetATorneo(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los torneos: " + e.getMessage());
        }
        return torneos;
    }

    public  static boolean crearTorneo(Torneo torneo) {
        String sql = "INSERT INTO torneo (nombre, codRegion, puntosVictoria, idAdmin) VALUES (?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, torneo.getNombre());
            statement.setString(2, String.valueOf(torneo.getCodRegion()));
            statement.setFloat(3, torneo.getPuntosVictoria());
            statement.setString(4, String.valueOf(torneo.getIdAdmin()));

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear el torneo: " + e.getMessage());
        }
        return false;
    }

    public static Torneo obtenerTorneoPorId(int id) {
        String sql = "SELECT id, nombre, codRegion, puntosVictoria, idAdmin FROM torneo WHERE id = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearResultSetATorneo(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el torneo: " + e.getMessage());
        }
        return null;
    }

    public boolean inscribirEntrenadorEnTorneo(int idTorneo, int idEntrenador) {
        String sql = "INSERT INTO torneo_entrenadores (id_torneo, id_entrenador) VALUES (?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idTorneo);
            statement.setInt(2, idEntrenador);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al inscribir entrenador en torneo: " + e.getMessage());
        }
        return false;
    }

    private static Torneo mapearResultSetATorneo(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        char codRegion = resultSet.getString("codRegion").charAt(0);
        float puntosVictoria = resultSet.getFloat("puntosVictoria");
        String nomAdmin = resultSet.getString("idAdmin");

        return new Torneo(id, nombre, codRegion, puntosVictoria, nomAdmin);
    }

    public static Torneo buscarPorTorneoId(int id){
        Torneo torneo = new Torneo();

        String sql = "SELECT id,nombre,codRegion,puntosVictoria,idAdmin" +
                " FROM torneo WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    torneo.setId(resultSet.getInt("id"));
                    torneo.setNombre(resultSet.getString("nombre"));
                    torneo.setCodRegion(resultSet.getString("codRegion").charAt(0));
                    torneo.setPuntosVictoria(resultSet.getFloat("puntosVictoria"));
                    torneo.setCombates(combate.combatesPorTorneo(torneo.getId()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }





        return torneo;

    }
    /*
    String sql = "SELECT id, nombre, nacionalidad FROM entrenador WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id); // Usamos setInt para id que es un int
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Crear el objeto entrenador con los datos obtenidos
                    entrenador = new Entrenador();
                    entrenador.setId(resultSet.getInt("id"));
                    entrenador.setNombre(resultSet.getString("nombre"));
                    entrenador.setNacionalidad(resultSet.getString("nacionalidad"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     */

    public Torneo añadirCombates(Torneo torneo) {
        String sql = "SELECT id, nombre, codRegion, puntosVictoria, idAdmin FROM combate WHERE idTorneo = ?";
        CombateDAOImplementacion combate = new CombateDAOImplementacion();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, torneo.getId()); // Usamos setInt para id que es un int

            try (ResultSet resultSet = statement.executeQuery()) {
                // Mientras haya resultados en el ResultSet
                while (resultSet.next()) {
                    // Por cada resultado, obtenemos el ID del combate y lo agregamos a la lista de combates del torneo
                    int combateId = resultSet.getInt("id");
                    torneo.getCombates().add(combate.combatePorId(combateId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return torneo; // Retornamos el torneo con los combates añadidos
    }

    public static void mostrarTorneos(){
        List<Torneo> a = obtenerTodosLosTorneos();
       for(Torneo i : a){
           System.out.println(i.toString());
       }

    }


    }
