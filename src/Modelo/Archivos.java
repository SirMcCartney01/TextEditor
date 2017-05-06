package Modelo;

import java.io.*;
import java.util.Scanner;

/**
 * Created by cesar on 3/05/17.
 */
public class Archivos {

    public String Archivos(String fileRout){

        File folder = new File(fileRout);
        File[] listOfFiles = folder.listFiles();

        String filesAvailable = "";

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filesAvailable += listOfFiles[i].getName()+"\n";
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Ruta " + listOfFiles[i].getName());
            }
        }
        return filesAvailable;
    }

    public String AbrirArchivo(String chosenFile){

        Scanner reading;
        String line = "";
        try {
            reading = new Scanner(new File(chosenFile));

            while (reading.hasNextLine()) {
                Scanner word = new Scanner(reading.nextLine());
                while (word.hasNext()) {
                    line = line+"\n"+word.nextLine();
                }
            }
            reading.close();
        } catch (Exception e) {
            System.out.print("No pude acceder a ruta"+chosenFile);
            line = "failure";
        }

        return line;
    }

    public String GuardarEvaluacion(String text, String rout){

        try(FileWriter fw = new FileWriter(rout, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(text);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            System.out.println("No pude escribir");
        }

        return "";
    }
}
