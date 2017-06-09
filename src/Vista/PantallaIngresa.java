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

        JTextField user = new JTextField();
        JTextField password = new JPasswordField();
        Object[] message = {
                "Usuario",user,
                "Contraseña;",password
        };


        int option;
        option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        String action = Integer.toString(option);

        ControlAcceso acces = new ControlAcceso();
        String isAdmin = acces.ControlAcceso(user.getText(),password.getText(),action);


        if(isAdmin.equals("invalid")){
            JOptionPane.showMessageDialog(null,"Usuario y/o contraseña no validos!","¡Error!",JOptionPane.ERROR_MESSAGE);
        }

        if(isAdmin.equals("failure")){
            JOptionPane.showMessageDialog(null,"Hubo un problema al intentar accdeder a la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
        }


        if(!isAdmin.equals("0") && !isAdmin.equals("failure") && !isAdmin.equals("invalid")){ //I know... the window still pops up, but gotta follow the sequence diagram
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            Container container = frame.getContentPane();
            container.setLayout(new BorderLayout());

            JOptionPane.showMessageDialog(null, "Bienvenido " + user.getText(), "Perfil usuario", JOptionPane.INFORMATION_MESSAGE);
            /**
             * Si el usuario es evaluador
             * */
            if(isAdmin == "1"){
                PantallaPrincipalEvaluador principalEvaluador = new PantallaPrincipalEvaluador();
                container.add(principalEvaluador, BorderLayout.CENTER);
            }

            /**
             * Si el usuario es administrador
             * */
            if(isAdmin == "2"){
                PantallaPrincipalAdministrador principalAdministrador = new PantallaPrincipalAdministrador();
                container.add(principalAdministrador, BorderLayout.CENTER);

            }
        }else{
            Window w = SwingUtilities.getWindowAncestor(frame);
            w.dispose();
        }
    }
}
