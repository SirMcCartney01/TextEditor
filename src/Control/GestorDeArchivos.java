package Control;

import Modelo.Archivos;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by cesar on 3/05/17.
 */
public class GestorDeArchivos {

    Archivos archivos = new Archivos();
    public String GestorDeArchivos(String fileRout){


        String file = archivos.Archivos(fileRout);

        return file;
    }

    public String BuscaArchivo(String chosenFile){

        String text = archivos.AbrirArchivo(chosenFile);

        return text;
    }

    public String Evaluacion(String text, String evaluation){

        Scanner reading;
        String userName = "", fileName = "";
        try {
            reading = new Scanner(new File(new File("./src").getAbsolutePath() + "/ActiveUser.txt"));
            while (reading.hasNextLine()) {
                Scanner word = new Scanner(reading.nextLine());
                while (word.hasNextLine()) {
                    userName = word.nextLine();
                }
            }
            reading.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"THIS MESSAGE SHOULD NEVER BE DISPLAYED","Error",JOptionPane.ERROR_MESSAGE);
        }

        try {
            reading = new Scanner(new File(new File("./src").getAbsolutePath() + "/ActiveFile.txt"));
            while (reading.hasNextLine()) {
                Scanner word = new Scanner(reading.nextLine());
                while (word.hasNextLine()) {
                    fileName = word.nextLine();
                }
            }
            reading.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"THIS MESSAGE SHOULD NEVER BE DISPLAYED","Error",JOptionPane.ERROR_MESSAGE);
        }

        String rout = new File("./src").getAbsolutePath() + "/Textos Evaluados/";
        rout += userName +"/" + fileName + "/";

        File directory = new File(rout);
        if(!directory.exists()) {
            directory.mkdir();
        }

        switch (evaluation){
            case "0":
                rout += "Juicio.txt";
                break;

            case "1":
                rout += "Apreciacion.txt";
                break;

            case "2":
                rout += "Afecto.txt";
                break;
        }
        String result = archivos.GuardarEvaluacion(text, rout);

        return result;
    }

    public String EvaluacionesCompletas(){

        String fileRout = new File("./src").getAbsolutePath() + "/Textos Evaluados/";
        File folder = new File(fileRout);
        File[] listOfFiles = folder.listFiles();

        String failure = "false";
        String userName;
        int numberOfFolders = 0, numberOfFiles, evaluationPossible = 0;

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isDirectory()) {
                numberOfFiles = 0;
                String dynamicRout = new File("./src").getAbsolutePath() + "/Textos Evaluados/";
                userName = listOfFile.getName();
                dynamicRout += userName;
                String files = archivos.BuscaArchivosDeEvaluacion(dynamicRout);
                numberOfFiles += Integer.parseInt(files);
                if (numberOfFiles > 0) {
                    evaluationPossible++;
                }
                numberOfFolders++;
                System.out.println("User " + userName + " has evaluated " + numberOfFiles + " files");
            }
        }

        if(numberOfFolders < 2 || evaluationPossible < 2){
            failure = "true";
        }

        return failure;
    }
}
