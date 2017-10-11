package Modelo;

import java.io.*;
import java.util.Scanner;

/**
 * Created by cesar on 3/05/17.
 */
public class Archivos {

    public String Archivos(String fileRout) {

        File folder = new File(fileRout);
        File[] listOfFiles = folder.listFiles();

        String filesAvailable = "";

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                filesAvailable += listOfFile.getName() + "\n";
            } else if (listOfFile.isDirectory()) {
                System.out.println("Ruta " + listOfFile.getName());
            }
        }
        return filesAvailable;
    }

    public String BuscarUsuarios(String TypeOfUser) {

        String rout;
        if(TypeOfUser.equals("evaluator")){
            rout= new File("./src").getAbsolutePath() + "/evaluatorDataBase.txt";
        } else {
            rout = new File("./src").getAbsolutePath() + "/adminDataBase.txt";
        }

        return rout;
    }

    public String AbrirArchivo(String chosenFile) {

        Scanner reading;
        String line = "";
        try {
            reading = new Scanner(new File(chosenFile));

            while (reading.hasNextLine()) {
                Scanner word = new Scanner(reading.nextLine());
                while (word.hasNext()) {
                    line = line + "\n" + word.nextLine();
                }
            }
            reading.close();
        } catch (Exception e) {
            System.out.print("No pude acceder a ruta" + chosenFile);
            line = "failure";
        }

        return line;
    }

    public String GuardarEvaluacion(String text, String rout){

        String result;
        try (FileWriter fw = new FileWriter(rout, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(text);
            result = "success";
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            System.out.println("No pude escribir");
            result = "failure";
        }

        return result;
    }

    public String BuscaArchivosDeEvaluacion(String rout) {

        int numberOfFiles = 0;

        File folder = new File(rout);
        File[] listOfFiles = folder.listFiles(); // Contains evaluated texts eg. Mexico, Alienware
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isDirectory()) {

                String myRout = rout;
                String rout2 = myRout += "/" + listOfFile.getName() + "/";
                System.out.println(myRout);

                File files = new File(rout2);
                File[] listOfFiles2 = files.listFiles(); // Contains files Judgement, Appreciation and affection
                // arrayOfFiles = new int[listOfFiles2.length];
                for (File aListOfFiles2 : listOfFiles2) {
                    if (aListOfFiles2.isFile()) {
                        System.out.println(aListOfFiles2.getName());
                    }
                }
                numberOfFiles++;
            }
        }

        return Integer.toString(numberOfFiles);
    }
}
