package Swing;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * TabPane
 */
public class TabPane extends JFrame implements ListSelectionListener {

    /**
     *
     */
    private static final long serialVersionUID = -3600359177074176957L;

    String[] columnName = { "Nom", "Prenom", "Email", "Adresse", "Telephone" };
    DefaultTableModel model = new DefaultTableModel(columnName, 0);
    JTable table = new JTable(model);
    String[] data = { "Clients", "Produits" };
    JList<String> list = new JList<String>(data);

    public TabPane() throws Exception {
        super("JTab");

        this.setLayout(new BorderLayout());
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Left side of the window

        list.addListSelectionListener(this);
        JScrollPane pg = new JScrollPane(table);
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, pg);

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.add(split, BorderLayout.CENTER);
    }

    // Connexion to the database and query
    private void CnxDatabaseQuery() throws SQLException {
        String sql = "select DISTINCT Nom, Prenom, Email,Adresse, Telephone from clients";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projet", "root", "root");

        PreparedStatement state = con.prepareStatement(sql);
        ResultSet rs = state.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("Nom");
            String prenom = rs.getString("Prenom");
            String mail = rs.getString("Email");
            String adresse = rs.getString("Adresse");
            String tel = rs.getString("Telephone");
            model.addRow(new Object[] { nom, prenom, mail, adresse, tel });
        }

        TableColumnModel column = table.getColumnModel();
        column.getColumn(3).setPreferredWidth(100);

    }

    // List listener on mouse click
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == 0) {
                System.out.println("clients");
                try {

                    // Update the table on click
                    model.setRowCount(0);
                    CnxDatabaseQuery();

                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
            } else if (list.getSelectedIndex() == 1) {
                System.out.println("produits");

                try {
                    model.setRowCount(0);
                    DisplayProduct();

                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    // Display the table for the products
    private void DisplayProduct() throws SQLException {
        String sql = "select DISTINCT Nom, Prix, Description FROM produit";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projet", "root", "root");
        PreparedStatement state = con.prepareStatement(sql);
        ResultSet rs = state.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("Nom");
            double prix = rs.getDouble("Prix");
            String desc = rs.getString("Description");
            model.addRow(new Object[] { nom, prix, desc });
        }
        con.close();
    }

    public void DisplayGui() throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        TabPane frame = new TabPane();
        frame.pack();
        frame.setVisible(true);

    }
}