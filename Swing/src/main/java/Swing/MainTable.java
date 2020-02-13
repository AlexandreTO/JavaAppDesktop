package Swing;

import javax.swing.JFrame;

/**
 * MainTable
 */
public class MainTable extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -2735139649880905419L;

    public static void main(String[] args) {
        JFrame frame = new DisplayCl();
        frame.setTitle("Affichage clients");
        frame.setSize(800, 800);
        // frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}