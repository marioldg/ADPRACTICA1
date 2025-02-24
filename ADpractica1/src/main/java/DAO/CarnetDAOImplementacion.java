package DAO;
import Entidades.*;
import DB.Conexion;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CarnetDAOImplementacion{

    public CarnetDAOImplementacion() {
    }


    public static void crearCarnet(Carnet carnet) {
        String sql = "INSERT INTO carnet (idEntrenador, fechaExpedicion, puntos, numVictorias) VALUES (?, ?, ?, ?)";
        System.out.println(sql);
        System.out.println(carnet.toString());
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, carnet.getIdEntrenador());
            statement.setDate(2, java.sql.Date.valueOf(carnet.getFechaExpedicion()));
            statement.setFloat(3, carnet.getPuntos());
            statement.setInt(4, carnet.getNumVictorias());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Carnet creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el carnet.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al intentar crear el carnet: " + e.getMessage());
        }
    }

    public Carnet obtenerCarnetPorId(long id) {
        String sql = "SELECT idEntrenador, fechaExpedicion, puntos, numVictorias FROM carnet WHERE idEntrenador = ?";

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
        long idEntrenador = resultSet.getLong("idEntrenador");
        Date fechaExpedicion = resultSet.getDate("fechaExpedicion");
        float puntos = resultSet.getFloat("puntos");
        int numVictorias = resultSet.getInt("numVictorias");

        return new Carnet(idEntrenador, ((java.sql.Date) fechaExpedicion).toLocalDate(), puntos, numVictorias);
    }




    @Override
    public String toString() {
        return "CarnetDAOImplementacion{}";
    }
}



