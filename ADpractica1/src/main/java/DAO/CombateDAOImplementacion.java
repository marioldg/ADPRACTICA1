package DAO;

import Entidades.*;
import DB.Conexion;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static DAO.EntrenadorDAOImplementacion.buscarPorId;

public class CombateDAOImplementacion {

    public CombateDAOImplementacion() {

    }


    public static void crearCombate(Combate combate) {
        String sql = "INSERT INTO combate (fecha, idTorneo) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(combate.getFecha()));
            statement.setInt(2, combate.getIdTorneo());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Combate creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el combate.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al intentar crear el combate: " + e.getMessage());
        }
    }


    public Combate obtenerCombatePorId(long id) {
        String sql = "SELECT fecha, id, idTorneo FROM combate WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearResultSetACombate(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Combate mapearResultSetACombate(ResultSet resultSet) throws SQLException {
        LocalDate fecha = resultSet.getDate("fecha").toLocalDate();
        long id = resultSet.getLong("id");
        int idTorneo = resultSet.getInt("idTorneo");

        return new Combate(fecha, idTorneo);
    }

    public ArrayList<Combate> combatesPorTorneo(int idTorneo) {
        List<Combate> combates = new ArrayList<>(); // 🔹 Lista para almacenar los combates

        String sql = "SELECT id, fecha, idEntrenador1, idEntrenador2, idTorneo FROM combate WHERE idTorneo = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idTorneo);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {  // 🔹 Iteramos sobre todos los resultados
                    Combate combate = new Combate();
                    combate.setId(resultSet.getInt("id"));  // Si tienes un setter para ID
                    combate.setIdTorneo(resultSet.getInt("idTorneo"));
                    combate.setFecha(resultSet.getDate("fecha").toLocalDate()); // Convertimos SQL Date a LocalDate

                    Entrenador entrenador1 = buscarPorId(resultSet.getInt("idEntrenador1"));
                    Entrenador entrenador2 = buscarPorId(resultSet.getInt("idEntrenador2"));

                    combate.getLuchadores().add(entrenador1);
                    combate.getLuchadores().add(entrenador2);

                    combates.add(combate); // 🔹 Añadir combate a la lista
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (ArrayList<Combate>) combates; // 🔹 Devuelve la lista de combates
    }
    public Combate combatePorId(int id) {
        Combate combate = new Combate();
        Entrenador entrenador1 = new Entrenador();
        Entrenador entrenador2 = new Entrenador();

        String sql = "SELECT id,fecha,idEntrenador1,idEntrenador2,idTorneo" +
                "FROM combate WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id); // Usamos setInt para id que es un int
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    combate.setIdTorneo(resultSet.getInt("idTorneo"));
                    entrenador1 = buscarPorId(resultSet.getInt("idEntrenador1"));
                    entrenador2 = buscarPorId(resultSet.getInt("idEntrenador2"));
                    combate.getLuchadores().add(entrenador1);
                    combate.getLuchadores().add(entrenador2);
                    combate.setFecha(LocalDate.parse(resultSet.getString("fecha")));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return combate;

    }
}
