package Swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class
 */
public class Class {

    public static int calculer(int a, int b) {
        int res = a + b;
        return res;
    }

    public static int testCnx() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/projet", "root", "root");
            return 1;
        } catch (SQLException e) {
            return 0;
        }

    }
}