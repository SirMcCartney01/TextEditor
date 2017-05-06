package Control;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by cesar on 26/04/17.
 */
public class ControlAcceso {
    public int ControlAcceso(){

        JTextField user = new JTextField();
        JTextField password = new JPasswordField();
        Object[] message = {
                "Usuario",user,
                "Contraseña;",password
        };

        boolean error = true;
        int isitAdmin = 0; //0 not found, 1 Evaluator and 2 Admin
        int option;

        do{
            option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                /**
                 * Lectura y escritura de archivos para simular una base de datos
                 * */
                String evaluatorDataBase = "/home/cesar/Documents/FCC/Primavera 2017/Ingenieria de Software/Proyecto Final/src/evaluatorDataBase.txt";
                String adminDataBase = "/home/cesar/Documents/FCC/Primavera 2017/Ingenieria de Software/Proyecto Final/src/adminDataBase.txt";
                String line;
                boolean userORpass = false;
                String id = user.getText(), pass = password.getText();

                Scanner reading = null;
                try {
                    reading = new Scanner(new File(evaluatorDataBase));
                    while (reading.hasNextLine()) {
                        Scanner word = new Scanner(reading.nextLine());
                        while (word.hasNext()) {
                            line = word.next();
                            if(!userORpass){
                                userORpass = true;
                                if(id.equals(line)){
                                    line = word.next();
                                    userORpass = false;
                                    if(pass.equals(line)) {
                                        JOptionPane.showMessageDialog(null, "Bienvenido " + user.getText(), "Perfil usuario", JOptionPane.INFORMATION_MESSAGE);
                                        /**
                                         * Creates directory
                                         */
                                        String direction = "/home/cesar/Documents/FCC/Primavera 2017/Ingenieria de Software/Proyecto Final/src/Textos Evaluados/";
                                        direction += user.getText()+"/";
                                        File directory = new File(direction);
                                        if(!directory.exists()) {
                                            directory.mkdir();
                                        }

                                        /**
                                         * Awful cheating
                                         */
                                        try{
                                            PrintWriter writer = new PrintWriter("/home/cesar/Documents/FCC/Primavera 2017/Ingenieria de Software/ActiveUser.txt");
                                            writer.println(user.getText());
                                            writer.close();
                                        } catch (IOException e) {
                                            // do something
                                        }

                                        error = false;
                                        isitAdmin = 1;
                                        break;
                                    }
                                    line = word.next();
                                    line = word.next();
                                    line = word.next();
                                }
                            }else{
                                userORpass = false;
                            }
                        }
                    }
                    reading.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Hubo un problema al intentar accdeder a la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
                }


                /**
                 *Administradores
                 */
                if(error){
                    Scanner reading2 = null;
                    try {
                        reading2 = new Scanner(new File(adminDataBase));
                        while (reading2.hasNextLine()) {
                            Scanner word = new Scanner(reading2.nextLine());
                            while (word.hasNext()) {
                                line = word.next();
                                if(!userORpass){
                                    userORpass = true;
                                    if(id.equals(line)){
                                        line = word.next();
                                        userORpass = false;
                                        if(pass.equals(line)){
                                            JOptionPane.showMessageDialog(null,"Bienvenido "+user.getText(),"Perfil usuario", JOptionPane.INFORMATION_MESSAGE);
                                            error = false;
                                            isitAdmin = 2;
                                            break;
                                        }
                                        line = word.next();
                                        line = word.next();
                                        line = word.next();
                                    }
                                }else{
                                    userORpass = false;
                                }
                            }
                        }
                        reading2.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null,"Hubo un problema al intentar accdeder a la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }

                //}
                if(error){
                    JOptionPane.showMessageDialog(null,"Usuario y/o contraseña no validos!","¡Error!",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("Login canceled");
                error = true;
            }
            user.setText(null);
            password.setText(null);
        }while(error && JOptionPane.CANCEL_OPTION != option && JOptionPane.CLOSED_OPTION != option);
        return isitAdmin;
    }
}
