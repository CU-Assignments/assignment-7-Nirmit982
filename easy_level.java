import java.sql.*;

public class EasyLevelJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Company";
        String user = "root";
        String password = "your_password_here";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            // Execute SQL query
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            System.out.println("Employee Records:");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(id + " | " + name + " | " + salary);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
