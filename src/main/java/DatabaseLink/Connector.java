package DatabaseLink;

import java.sql.*;
import java.util.ArrayList;

public class Connector {

    private String usuario = "mi_nuevo_usuario";
    private String portUrl = "jdbc:mysql://localhost:3306/mi_muebleria";
    private String password = "abc123";
    private Connection connection;
    private ResultSet resultados;

    public Connector() {
        realizarConneccion();
    }

    public void realizarConneccion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(portUrl, usuario, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectFrom(String query) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = statement.executeQuery(query);
            return resultados;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void update(String query, String[] valores) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int k = 0; k < valores.length; k++) {
                preparedStatement.setString(k + 1, valores[k]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertTo(String query, String[] valores) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int k = 0; k < valores.length; k++) {
                preparedStatement.setString(k + 1, valores[k]);
            }
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeThis() {
        try {
            if (resultados != null) {
                resultados.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
