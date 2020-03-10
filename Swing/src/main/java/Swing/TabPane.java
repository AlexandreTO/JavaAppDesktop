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

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * TabPane
 */
public class TabPane extends JFrame implements ListSelectionListener {

    /**
     *
     */
    private static final long serialVersionUID = -3600359177074176957L;

    String[] columnName;
    DefaultTableModel model = new DefaultTableModel(columnName, 0);
    JTable table = new JTable(model);
    String[] data = { "Clients", "Produits", "Plus vendus" };
    JList<String> list = new JList<String>(data);

    public TabPane() {
        super("App");

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
    private void CnxDatabaseQuery() {
        String sql = "select DISTINCT Nom, Prenom, Email,Adresse, Telephone from clients";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projet", "root", "root");
            PreparedStatement state = con.prepareStatement(sql);
            ResultSet rs = state.executeQuery();

            // Allows us to work on the results of the query
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

            // Get the number of columns retrieved
            int count = metaData.getColumnCount();

            while (rs.next()) {
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String mail = rs.getString("Email");
                String adresse = rs.getString("Adresse");
                String tel = rs.getString("Telephone");
                model.addRow(new Object[] { nom, prenom, mail, adresse, tel });
            }
            /*
             * Change the header of the column in the table fetched with the query,j-1
             * because for getColumnCount, first index is 1 NOT 0
             */
            for (int j = 1; j <= count; j++) {
                table.getColumnModel().getColumn(j - 1).setHeaderValue(metaData.getColumnLabel(j));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // List listener on mouse click
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == 0) {
                // Update the table on click
                model.setRowCount(0);
                // Set the number of columns that will appear.
                model.setColumnCount(5);
                CnxDatabaseQuery();
            } else if (list.getSelectedIndex() == 1) {
                model.setRowCount(0);
                model.setColumnCount(3);
                DisplayProduct();
            } else if (list.getSelectedIndex() == 2) {
                model.setRowCount(0);
                model.setColumnCount(3);
                MostSoldProduct();
            }
        }
    }

    // Display the table for the products
    private void DisplayProduct() {
        String sql = "select DISTINCT Nom, Prix, Description FROM produit";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projet", "root", "root");
            PreparedStatement state = con.prepareStatement(sql);
            ResultSet rs = state.executeQuery();
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
            int count = metaData.getColumnCount();
            while (rs.next()) {
                String nom = rs.getString("Nom");
                double prix = rs.getDouble("Prix");
                String desc = rs.getString("Description");
                model.addRow(new Object[] { nom, prix, desc });
            }

            for (int j = 1; j <= count; j++) {
                table.getColumnModel().getColumn(j - 1).setHeaderValue(metaData.getColumnLabel(j));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private void MostSoldProduct() {
        String req = "select Nom, quantite, Vendu from produit order by Vendu desc";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/projet", "root", "root");
            PreparedStatement state = con.prepareStatement(req);
            ResultSet rs = state.executeQuery();
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
            int count = metaData.getColumnCount();

            while (rs.next()) {
                String nom = rs.getString("Nom");
                String qte = rs.getString("quantite");
                String vendu = rs.getString("Vendu");
                model.addRow(new Object[] { nom, qte, vendu });
            }

            for (int i = 1; i <= count; i++) {
                table.getColumnModel().getColumn(i - 1).setHeaderValue(metaData.getColumnLabel(i));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DisplayGui() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        TabPane frame = new TabPane();
        frame.pack();
        frame.setVisible(true);

    }
}