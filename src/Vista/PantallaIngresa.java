package Vista;

import Control.ControlAcceso;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cesar on 24/04/17.
 */
public class PantallaIngresa extends JFrame {


    public PantallaIngresa() {
        super("Pantalla ingresa");
        initComponents(this);
    }

    private void initComponents(JFrame frame) {

        ControlAcceso acces = new ControlAcceso();
        int isAdmin = acces.ControlAcceso();

        if(isAdmin != 0){ //I know... the window still pops up, but gotta follow the sequence diagram
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            Container container = frame.getContentPane();
            container.setLayout(new BorderLayout());

            /**
             * Si el usuario es evaluador
             * */
            if(isAdmin == 1){
                PantallaPrincipalEvaluador principalEvaluador = new PantallaPrincipalEvaluador();
                container.add(principalEvaluador, BorderLayout.CENTER);
            }

            /**
             * Si el usuario es administrador
             * */
            if(isAdmin == 2){
                PantallaPrincipalAdministrador principalAdministrador = new PantallaPrincipalAdministrador();
                container.add(principalAdministrador, BorderLayout.CENTER);

            }
        }
    }
}
