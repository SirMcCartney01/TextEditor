package Control;

import Modelo.Archivos;

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

    public String ControlAccesoDirection(String user, String password, String action) {
        int option = Integer.parseInt(action);
        boolean error = true;
        boolean wrongPass = false;
        String isitAdmin = "0"; //0 not found, 1 Evaluator, 2 Admin, failure Can't open file, invalid Incorrect data

        if (option == JOptionPane.OK_OPTION) {
            /*
             * Lectura y escritura de archivos para simular una base de datos
            */
            Archivos rout = new Archivos();
            String evaluatorDataBase = rout.BuscarUsuarios("evaluator");

            String line;
            String id = user, pass = password;

            Scanner reading;
            try {
                reading = new Scanner(new File(evaluatorDataBase));
                while (reading.hasNextLine() && !wrongPass) {
                    Scanner word = new Scanner(reading.nextLine());
                    while (word.hasNext()) {
                        line = word.next();
                        if(id.equals(line)){
                            line = word.next();
                            if(pass.equals(line)) {
                                /*
                                 * Creates directory
                                 */
                                String direction = new File("./src").getAbsolutePath() + "/Textos Evaluados/";
                                direction += user + "/";
                                File directory = new File(direction);
                                if(!directory.exists()) {
                                    directory.mkdir();
                                }

                                /*
                                 * Awful cheating
                                 */
                                try{
                                    PrintWriter writer = new PrintWriter(new File("./src").getAbsoluteFile() + "/Ingenieria de Software/ActiveUser.txt");
                                    writer.println(user);
                                    writer.close();
                                } catch (IOException e) {
                                    System.err.println("Error: " + e.getMessage());
                                }

                                error = false;
                                isitAdmin = "1";
                                break;
                            }else{
                                wrongPass = true;
                                break;
                            }
                        }else{
                            line = word.next();
                            line = word.next();
                            line = word.next();
                            line = word.next();
                        }
                    }
                }
                reading.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                isitAdmin = "failure";
            }

            String adminDataBase = rout.BuscarUsuarios("admin");
            /*
             * Administradores
            */
            if(error) {
                Scanner reading2;
                try {
                    reading2 = new Scanner(new File(adminDataBase));
                    while (reading2.hasNextLine() && !wrongPass) {
                        Scanner word = new Scanner(reading2.nextLine());
                        while (word.hasNext()) {
                            line = word.next();
                            if(id.equals(line)) {
                                line = word.next();
                                if (pass.equals(line)) {
                                    error = false;
                                    isitAdmin = "2";
                                    break;
                                }else{
                                    wrongPass = true;
                                    break;
                                }
                            }else{
                                line = word.next();
                                line = word.next();
                                line = word.next();
                                line = word.next();
                            }
                        }
                    }
                    reading2.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    isitAdmin = "failure";
                }
            }
            if(error){
                isitAdmin = "invalid";
            }
        } else {
            System.out.println("Login canceled");
        }
        return isitAdmin;
    }
}
