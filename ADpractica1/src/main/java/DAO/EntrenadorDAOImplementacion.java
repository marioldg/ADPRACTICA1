package DAO;
import Entidades.*;

import DB.Conexion;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class EntrenadorDAOImplementacion {


    private static EntrenadorDAOImplementacion instancia;
    //private static DataSource dataSource;

    public EntrenadorDAOImplementacion() {
        //this.dataSource = dataSource;
    }

    public static EntrenadorDAOImplementacion getInstancia() {
        if (instancia == null) {
            instancia = new EntrenadorDAOImplementacion();
        }
        return instancia;
    }



    public static void crearEntrenador(Entrenador entrenador) {
        String sql = "INSERT INTO entrenador (nombre, nacionalidad) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entrenador.getNombre());
            statement.setString(2, entrenador.getNacionalidad());
            //statement.setLong(3, entrenador.getCarnet().getIdEntrenador());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Entrenador creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el entrenador.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al intentar crear el entrenador: " + e.getMessage());
        }

    }

    public Entrenador obtenerEntrenadorPorId(long id) {
        String sql = "SELECT id, nombre, nacionalidad, carnet_id FROM entrenador WHERE id = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearResultSetAEntrenador(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Entrenador mapearResultSetAEntrenador(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String nacionalidad = resultSet.getString("nacionalidad");
        long idCarnet = resultSet.getLong("id_carnet");

        // Obtener el carnet correspondiente utilizando el método adecuado
        Carnet carnet = obtenerCarnetPorId(idCarnet);

        if (carnet == null) {
            throw new SQLException("No se encontró el carnet con ID: " + idCarnet);
        }

        return new Entrenador(nombre, nacionalidad, carnet);
    }

    /**
     * AQUI METO LOS METODOS DE CARNETIMPLEMENTACIONDAO PORQUE NO SE
     * PORQUE NO ME DEJA IMPORTARLO DESDE LA CLASE
     * @param id
     * @return
     */

    public Carnet obtenerCarnetPorId(long id) {
        String sql = "SELECT idEntrenador, fechaExpedicion, puntos, numVictorias " +
                    "FROM carnets " +
                    "WHERE id_entrenador = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearResultSetACarnet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Carnet mapearResultSetACarnet(ResultSet resultSet) throws SQLException {
        long idEntrenador = resultSet.getLong("id_entrenador");
        Date fechaExpedicion = resultSet.getDate("fecha_expedicion");
        float puntos = resultSet.getFloat("puntos");
        int numVictorias = resultSet.getInt("num_victorias");

        return new Carnet(idEntrenador, ((java.sql.Date) fechaExpedicion).toLocalDate(), puntos, numVictorias);
    }


    public static Entrenador buscarPorId(int id) {
        Entrenador entrenador = null; // Inicializamos el objeto entrenador como null
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

        return entrenador; // Devolvemos el objeto entrenador
    }


}
