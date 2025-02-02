package DAO;

import Entidades.*;
import DB.Conexion;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static DAO.EntrenadorDAOImplementacion.buscarPorId;

public class CombateDAOImplementacion {

    private static CombateDAOImplementacion instancia;
    //private static DataSource dataSource;

    public CombateDAOImplementacion() {
        //this.dataSource = dataSource;
    }

    public static CombateDAOImplementacion getInstancia(DataSource dataSource) {
        if (instancia == null) {
            instancia = new CombateDAOImplementacion();
        }
        return instancia;
    }

    public static void crearCombate(Combate combate) {
        String sql = "INSERT INTO combates (fecha, idTorneo) VALUES (?, ?)";

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
        String sql = "SELECT fecha, id, idTorneo FROM combates WHERE id = ?";

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

    public Combate combatePorTorneo(int idTorneo) {
        Combate combate = new Combate();
        Entrenador entrenador1 = new Entrenador();
        Entrenador entrenador2 = new Entrenador();

        String sql = "SELECT id,fecha,idEntrenador1,idEntrenador2,idTorneo" +
                "FROM combate WHERE idTorneo = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idTorneo); // Usamos setInt para id que es un int
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
