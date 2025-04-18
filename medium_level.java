import java.sql.*;
import java.util.Scanner;

public class MediumLevelCRUD {
    static final String url = "jdbc:mysql://localhost:3306/Inventory";
    static final String user = "root";
    static final String password = "your_password_here";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con.setAutoCommit(false); // Begin transaction
            int choice;

            do {
                System.out.println("\n1. Insert\n2. View\n3. Update\n4. Delete\n5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> insertProduct(con, sc);
                    case 2 -> viewProducts(con);
                    case 3 -> updateProduct(con, sc);
                    case 4 -> deleteProduct(con, sc);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice.");
                }

            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertProduct(Connection con, Scanner sc) {
        try (PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Product VALUES (?, ?, ?, ?)")) {

            System.out.print("Enter ProductID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter ProductName: ");
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.executeUpdate();

            con.commit();
            System.out.println("Product inserted successfully.");
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            System.out.println("Error inserting product.");
        }
    }

    static void viewProducts(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
        System.out.println("ProductID | ProductName | Price | Quantity");
        while (rs.next()) {
            System.out.printf("%d | %s | %.2f | %d\n",
                    rs.getInt(1), rs.getString(2),
                    rs.getDouble(3), rs.getInt(4));
        }
    }

    static void updateProduct(Connection con, Scanner sc) {
        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?")) {

            System.out.print("Enter ProductID to update: ");
            int id = sc.nextInt();
            System.out.print("New Price: ");
            double price = sc.nextDouble();
            System.out.print("New Quantity: ");
            int qty = sc.nextInt();

            ps.setDouble(1, price);
            ps.setInt(2, qty);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            con.commit();

            if (rows > 0) System.out.println("Product updated.");
            else System.out.println("Product not found.");

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            System.out.println("Error updating product.");
        }
    }

    static void deleteProduct(Connection con, Scanner sc) {
        try (PreparedStatement ps = con.prepareStatement(
                "DELETE FROM Product WHERE ProductID = ?")) {

            System.out.print("Enter ProductID to delete: ");
            int id = sc.nextInt();
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            con.commit();

            if (rows > 0) System.out.println("Product deleted.");
            else System.out.println("Product not found.");

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            System.out.println("Error deleting product.");
        }
    }
}
