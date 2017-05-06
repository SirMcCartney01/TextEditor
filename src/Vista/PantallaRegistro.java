package Vista;

import Control.Registrar;

import javax.swing.*;

/**
 * Created by cesar on 2/05/17.
 */
public class PantallaRegistro {
    public void PantallaRegistro() {

        JTextField user = new JTextField();
        JTextField password = new JPasswordField();
        JTextField country = new JTextField();
        JTextField birthDate = new JTextField();
        JRadioButton female = new JRadioButton("Femenino");
        JRadioButton male = new JRadioButton("Masculino");
        ButtonGroup gender = new ButtonGroup();
        gender.add(female);
        gender.add(male);
        JRadioButton eval = new JRadioButton("Evaluador");
        JRadioButton administrator = new JRadioButton("Administrador");
        ButtonGroup adminORnot = new ButtonGroup();
        adminORnot.add(eval);
        adminORnot.add(administrator);
        female.setSelected(true);
        eval.setSelected(true);

        final JPanel registry = new JPanel();
        final JPanel privilages = new JPanel();


        Object[] message = {
                "Ingrese usuario:", user,
                "Asigne contraseña:", password,
                "Pais:", country,
                "Fecha de nacimiento:", birthDate,
                "Sexo:", registry,
                "Tipo de usuario:", privilages,
        };

        registry.add(female);
        registry.add(male);
        privilages.add(eval);
        privilages.add(administrator);


        boolean error;
        int option;
        do {
            option = JOptionPane.showConfirmDialog(null, message, "Registrar", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {

                if (!user.getText().isEmpty() && !password.getText().isEmpty() && !country.getText().isEmpty() && !birthDate.getText().isEmpty()) {
                    /**
                     * Clase Registrar, agrega al usuario
                     * */
                    String usu = user.getText();
                    String contra = password.getText();
                    String pais = country.getText();
                    String cumple = birthDate.getText();
                    String genero;
                    String privilegios;
                    if(female.isSelected()){
                        genero = "Femenino";
                    }else{
                        genero = "Masculino";
                    }

                    if(eval.isSelected()){
                        privilegios = "Evaluador";
                    }else{
                        privilegios = "Administrador";
                    }

                    Registrar registrar = new Registrar();

                    String foundIt = registrar.Registrar(usu,contra,pais,cumple,genero,privilegios);
                    if(foundIt.equals("false")){
                        JOptionPane.showMessageDialog(null, "Bienvenido " + user.getText(), "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                        error = false;
                    }else{
                        JOptionPane.showMessageDialog(null,"El usuario "+usu+" ya existe!","Error",JOptionPane.ERROR_MESSAGE);
                        error = true;

                        user.setText(null);
                        password.setText(null);
                        country.setText(null);
                        birthDate.setText(null);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡Faltan datos! revise por favor", "¡Error!", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
            } else {
                System.out.println("Registro cancelado");
                error = true;
            }
        } while (error && JOptionPane.CANCEL_OPTION != option && JOptionPane.CLOSED_OPTION != option);
    }
}
