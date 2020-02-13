package Swing;

/**
 * test
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConnexionTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Driver loaded");
        Connection con = null;
        ResultSet resultats = null;
        ResultSet resultat1 = null;
        String requete = "";
        String req = "";
        {

            // Try to connect
            con = DriverManager.getConnection("jdbc:mysql://localhost/projet", "root", "root");

            System.out.println("It works!");

            requete = "SELECT Nom,Prenom, Email from clients where Username ='Net'";
            try {
                Statement stmt = con.createStatement();
                resultats = stmt.executeQuery(requete);

            } catch (Exception e) {
                System.out.println("Arret lors de l'éxécution de la requete");

            }

            try {
                ResultSetMetaData rsmd = resultats.getMetaData();
                int nbCols = rsmd.getColumnCount();
                boolean encore = resultats.next();
                while (encore) {

                    for (int i = 1; i <= nbCols; i++) {
                        System.out.println(resultats.getString(i) + " ");
                    }
                    encore = resultats.next();
                }
                resultats.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            req = "SELECT PWD from clients where Username = 'Net'";

            Statement st = con.createStatement();
            System.out.println("Votre mdp ? ");
            String test = scanner.next();
            resultat1 = st.executeQuery(req);
            while (resultat1.next()) {
                String pwd = resultat1.getString("PWD");
                if (BCrypt.checkpw(test, pwd)) {
                    System.out.println("Meme mdp ! ");
                } else {
                    System.out.println("Mauvais mdp !");
                }

            }
            scanner.close();
            con.close();
        }

    }
}
