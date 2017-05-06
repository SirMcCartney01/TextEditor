package Control; /**
 * Created by cesar on 24/04/17.
 */

import Vista.PantallaIngresa;

import javax.swing.*;

public class Main {
    public static void main (String args[]){

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("Error: Failed to load Look and Feel");
            e.printStackTrace();
        }

        PantallaIngresa window = new PantallaIngresa();
        window.setVisible(true);

    }
}
