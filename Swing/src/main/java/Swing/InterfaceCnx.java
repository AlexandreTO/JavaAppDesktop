package Swing;

import java.awt.EventQueue;

/**
 * InterfaceCnx
 */

public class InterfaceCnx {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UICnx frame = new UICnx();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}