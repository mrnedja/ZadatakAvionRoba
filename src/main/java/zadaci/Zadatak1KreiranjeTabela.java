package zadaci;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Zadatak1KreiranjeTabela {
    public static void main(String[] args) {

        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:avionRobaSQLite.db");


        } catch (Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
