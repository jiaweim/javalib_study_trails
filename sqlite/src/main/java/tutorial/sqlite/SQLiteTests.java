package tutorial.sqlite;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 08 Apr 2019, 6:06 PM
 */
public class SQLiteTests
{
    @Test
    public void testCreate()
    {
        String url = "jdbc:sqlite:D:\\code\\test\\test.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData metaData = conn.getMetaData();
                assertEquals(metaData.getDriverName(), "SQLite JDBC");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCreateTable()
    {
        String url = "jdbc:sqlite:D:\\code\\test\\tests.db";
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (" +
                "id integer PRIMARY KEY, " +
                "name text NOT NULL, " +
                "capacity real);";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInsert()
    {
        insert("Raw Materials", 3000);
        insert("Semifinished Goods", 4000);
        insert("Finished Goods", 5000);

    }

    private Connection connect()
    {
        String url = "jdbc:sqlite:D:\\code\\test\\tests.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return con;
    }

    void insert(String name, double value)
    {
        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";

        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, value);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    void testSelect()
    {
        String sql = "SELECT id, name, capacity FROM warehouses";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testSelectGreater()
    {
        getCapacityGreaterThan(3600);
    }

    /**
     * Get the warehouse whose capacity greater than a specified capacity
     */

    void getCapacityGreaterThan(double capacity)
    {
        String sql = "SELECT id, name, capacity "
                + "FROM warehouses WHERE capacity > ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setDouble(1, capacity);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void update(int id, String name, double capacity)
    {
        String sql = "UPDATE warehouses SET name = ? , "
                + "capacity = ? "
                + "WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    @Test
    void testUpdate()
    {
        update(3, "Finished Products", 5500);
    }

    /**
     * Delete a warehouse specified by the id
     */
    void delete(int id)
    {
        String sql = "DELETE FROM warehouses WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testDelete()
    {
        delete(3);
    }

    @Test
    void testTransaction()
    {
        String sql = "CREATE TABLE IF NOT EXISTS materials (" +
                "id integer PRIMARY KEY," +
                "description text NOT NULL" +
                ")";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql2 = "CREATE TABLE IF NOT EXISTS inventory (" +
                "warehouse_id integer," +
                "material_id integer," +
                "qty real," +
                "PRIMARY KEY (warehouse_id, material_id)," +
                "FOREIGN KEY (warehouse_id) REFERENCES warehouses (id)," +
                "FOREIGN KEY (material_id) REFERENCES materials (id)" +
                ");";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void testTransaction2()
    {

    }
}
