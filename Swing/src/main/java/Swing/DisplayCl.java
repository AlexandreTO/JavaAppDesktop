package Swing;

import java.sql.*;
import javax.swing.*;
import java.awt.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * Display of all clients informations that are registered from the website and
 * the total numbers of clients
 */
public class DisplayCl extends JFrame {

    /**
     * 
     *
     */
    private static final long serialVersionUID = 1420656477655832537L;
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable table = new JTable(model);
    String count;

    public DisplayCl() {

        // Adding the columns and their names
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("Email");
        model.addColumn("Adresse");
        model.addColumn("Telephone");

        try {
            // Class.forName("com.mysql.jdbc.Driver");
            // Connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projet", "root", "root");
            PreparedStatement state = con.prepareStatement("select Nom, Prenom, Email,Adresse, Telephone from clients");
            ResultSet rs = state.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5) });
            }

            // Making the 3rd column wider
            TableColumnModel column = table.getColumnModel();
            column.getColumn(3).setPreferredWidth(100);

            PreparedStatement state1 = con.prepareStatement("select count(*) as total from clients");
            ResultSet rs1 = state1.executeQuery();
            while (rs1.next()) {
                count = rs1.getString("total");
            }
            con.close();

        } catch (Exception es) {
            System.out.println(es.getMessage());
        }

        // SwingConstants.CENTER let the label from JLabel to be center in the window
        JScrollPane pg = new JScrollPane(table);
        JLabel label = new JLabel("Client", SwingConstants.CENTER);
        JLabel label1 = new JLabel("Nombre de clients : " + count, SwingConstants.CENTER);

        // Affichage des éléments dans la fenêtre.
        cnt.setLayout(new BorderLayout());
        cnt.add(label, BorderLayout.PAGE_START);

        pg.setPreferredSize(new Dimension(500, 300));
        cnt.add(pg, BorderLayout.CENTER);

        cnt.add(label1, BorderLayout.PAGE_END);
        // this.pack();
    }
}