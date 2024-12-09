package jdbc_trainin;

import java.sql.*;

public class App {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/TEST_TW";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            Class.forName(JDBC_DRIVER);

            createTable(connection);

            insertStudentDetails(connection);

            fetchAndDisplayStudentDetails(connection);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS Students (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                subject VARCHAR(50) NOT NULL,
                percentage DECIMAL(5, 2) NOT NULL
            );
        """;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'Students' created successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to create table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertStudentDetails(Connection connection) {
        String insertSQL = """
            INSERT INTO Students (name, subject, percentage)
            VALUES (?, ?, ?);
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
        	
            preparedStatement.setString(1, "Rohit");
            preparedStatement.setString(2, "Mathematics");
            preparedStatement.setDouble(3, 85.75);
            preparedStatement.addBatch();

            preparedStatement.setString(1, "Chase");
            preparedStatement.setString(2, "Physics");
            preparedStatement.setDouble(3, 78.90);
            preparedStatement.addBatch();

            preparedStatement.setString(1, "Rishi");
            preparedStatement.setString(2, "Chemistry");
            preparedStatement.setDouble(3, 88.25);
            preparedStatement.addBatch();


            preparedStatement.executeBatch();
            System.out.println("Student details inserted successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to insert student details: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void fetchAndDisplayStudentDetails(Connection connection) {
        String selectSQL = "SELECT * FROM Students;";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            System.out.println("\nStudent Details:");
            System.out.printf("%-5s %-15s %-15s %-10s\n", "ID", "Name", "Subject", "Percentage");
            System.out.println("------------------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String subject = resultSet.getString("subject");
                double percentage = resultSet.getDouble("percentage");

                System.out.printf("%-5d %-15s %-15s %-10.2f\n", id, name, subject, percentage);
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch student details: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
