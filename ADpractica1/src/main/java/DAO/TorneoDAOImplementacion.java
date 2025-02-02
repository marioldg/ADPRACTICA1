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

    private static TorneoDAOImplementacion instancia;

    private TorneoDAOImplementacion() {
    }

    public static TorneoDAOImplementacion getInstancia() {
        if (instancia == null) {
            instancia = new TorneoDAOImplementacion();
        }
        return instancia;
    }

    public List<Torneo> obtenerTodosLosTorneos() {
        List<Torneo> torneos = new ArrayList<>();
        String sql = "SELECT id, nombre, cod_region, puntos_victoria, nom_admin FROM torneo";

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
        String sql = "INSERT INTO torneo (nombre, codRegion, puntosVictoria, nomAdmin) VALUES (?, ?, ?, ?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, torneo.getNombre());
            statement.setString(2, String.valueOf(torneo.getCodRegion()));
            statement.setFloat(3, torneo.getPuntosVictoria());
            statement.setString(4, torneo.getNomAdmin());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear el torneo: " + e.getMessage());
        }
        return false;
    }

    public static Torneo obtenerTorneoPorId(int id) {
        String sql = "SELECT id, nombre, cod_region, puntos_victoria, nom_admin FROM torneo WHERE id = ?";
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
        char codRegion = resultSet.getString("cod_region").charAt(0);
        float puntosVictoria = resultSet.getFloat("puntos_victoria");
        String nomAdmin = resultSet.getString("nom_admin");

        return new Torneo(id, nombre, codRegion, puntosVictoria, nomAdmin);
    }

    public Torneo buscarPorTorneoId(int id){
        Torneo torneo = new Torneo();

        String sql = "SELECT id,nombre,codRegion,puntosVictoria,idAdmin" +
                "FROM torneo WHERE id = ?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id); // Usamos setInt para id que es un int
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    torneo.setId(resultSet.getInt("id"));
                    torneo.setNombre(resultSet.getString("nombre"));
                    torneo.setCodRegion(resultSet.getString("codRegion").charAt(0));
                    torneo.setPuntosVictoria(resultSet.getFloat("puntosVictoria"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return torneo;

    }

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


    }
