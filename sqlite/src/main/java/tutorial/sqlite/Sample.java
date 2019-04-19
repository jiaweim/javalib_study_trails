package tutorial.sqlite;

import java.sql.*;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 07 Apr 2019, 1:31 PM
 */
public class Sample
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        statement.executeUpdate("drop table if exists person");
        statement.executeUpdate("create table person (id integer, name string)");
        statement.executeUpdate("insert into person values(1, 'leo')");
        statement.executeUpdate("insert into person values(2, 'yui')");

        ResultSet rs = statement.executeQuery("select * from person");
        while (rs.next()) {
            System.out.println("name = " + rs.getString("name"));

        }

        connection.close();
    }
}
