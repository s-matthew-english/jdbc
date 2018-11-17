// Import required packages
import java.sql.*;

public class FirstExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/";

    // Database credentials
    // static final String USER = "username";
    // static final String PASSWORD = "password";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL);

            // Execute a query
            System.out.println("Inserting records into the table...");
            statement = connection.createStatement();

            String sqlInsert = "INSERT INTO Employees VALUES (100, 18, 'Zara', 'Ali')";
            statement.executeUpdate(sqlInsert);

            // Execute a query
            System.out.println("Creating a statement...");
            statement = connection.createStatement();
            String sqlSelect = "SELECT id, first, last, age FROM Employees";
            ResultSet resultSet = statement.executeQuery(sqlSelect);

            // Extract data from result set
            while(resultSet.next()) {
                // Retrieve by column name
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                String first = resultSet.getString("first");
                String last = resultSet.getString("last");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", age:" + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }

            // Clean-up environment
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if(statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    } // End main
}
