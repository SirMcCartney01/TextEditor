package Control;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by cesar on 2/05/17.
 */
public class PerfilDisponible {
    public String perfilDisponible(String userID) {

        String routeAdmin = new File("./src").getAbsolutePath() + "/adminDataBase.txt";
        String routeEval = new File("./src").getAbsolutePath() + "/evaluatorDataBase.txt";

        String line;
        boolean userORpass = false;
        String fountIt = "0";
        String id = userID;

        Scanner reading = null;
        try {
            reading = new Scanner(new File(routeAdmin));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Hubo un problema al intentar accdeder a la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
        }
        while (reading.hasNextLine()) {
            Scanner word = new Scanner(reading.nextLine());
            while (word.hasNext()) {
                line = word.next();
                if(!userORpass){
                    userORpass = true;
                    if(id.equals(line)){
                        userORpass = false;
                        fountIt = "1";
                        break;
                    }
                }else{
                    userORpass = false;
                }
            }
        }
        reading.close();


        if(!fountIt.equals("1")) {
            Scanner reading2 = null;
            try {
                reading2 = new Scanner(new File(routeEval));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Hubo un problema al intentar accdeder a la base de datos!","Error",JOptionPane.ERROR_MESSAGE);
            }
            while (reading2.hasNextLine()) {
                Scanner word = new Scanner(reading2.nextLine());
                while (word.hasNext()) {
                    line = word.next();
                    if(!userORpass){
                        userORpass = true;
                        if(id.equals(line)){
                            userORpass = false;
                            fountIt = "1";
                            break;
                        }
                    } else {
                        userORpass = false;
                    }
                }
            }
            reading.close();
        }
        return fountIt;
    }
}
