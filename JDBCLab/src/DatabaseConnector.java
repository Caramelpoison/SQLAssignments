import java.sql.*;

public class DatabaseConnector {

    public Connection connect() {
        Connection connection = null;
        String url = "jdbc:sqlite:C://sqlite/BookStore.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(connection);
        return connection;
    }

    public void selectAllBooks() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            connection = this.connect();
            String sql = "SELECT * FROM Books";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("BookID") + "\t" +
                                resultSet.getString("Author") + "\t" +
                                resultSet.getString("Price")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection to SQLite has been closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
