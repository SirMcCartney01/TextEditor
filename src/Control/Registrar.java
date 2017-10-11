package Control;


import java.io.*;

/**
 * Created by cesar on 2/05/17.
 */
public class Registrar {

    public String Registrar(String usu, String contra, String  pais, String cumple, String genero, String privilegios){

        String failure;
        String repeated;
        String file;

        if(privilegios.equals("Evaluador")){
            file = new File("./src") + "/evaluatorDataBase.txt";
        } else {
            file = new File("./src/") + "/adminDataBase.txt";
        }
        /*
         * New admin
        */
        PerfilDisponible perfilDisponible = new PerfilDisponible();

        repeated = perfilDisponible.perfilDisponible(usu);
        if(privilegios.equals("Administrador")){
            if(repeated.equals("1")){
                failure = "true";
            }else{
                failure = "false";
            }
        }else{
            if(repeated.equals("1")){
                failure = "true";
            }else{
                failure = "false";
            }
        }

        if(failure.equals("false")){

            /**
             * Agregar nuevo usuario
             */
            try(FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(usu+"\t"+contra+"\t"+pais+"\t"+cumple+"\t"+genero+"\t");
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }
        return failure;
    }

}
