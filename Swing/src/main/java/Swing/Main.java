package Swing;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    TabPane test = new TabPane();
                    test.DisplayGui();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}