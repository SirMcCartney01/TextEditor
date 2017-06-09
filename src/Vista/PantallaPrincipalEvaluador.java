package Vista;

import Control.GestorDeArchivos;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cesar on 24/04/17.
 */
public class PantallaPrincipalEvaluador extends JPanel {

    public PantallaPrincipalEvaluador() {
        initPanel(this);

    }

    private void initPanel(JPanel panel) {
        panel.setBackground(Color.DARK_GRAY);

        JLabel eval = new JLabel();
        eval.setBounds(new Rectangle(800, 10, 600, 45));
        eval.setFont(new Font(eval.getFont().getName(), Font.BOLD, 50));
        eval.setPreferredSize(new Dimension(200, 200));
        eval.setText("Evaluador");
        panel.add(eval);

        JButton openFile = new JButton("Mostrar textos");
        openFile.setBounds(15, 60, 150, 40);
        panel.setLayout(null);
        panel.add(openFile);

        JButton evaluar = new JButton("Evaluar");
        evaluar.setBounds(200, 60, 150, 40);




        // Create the StyleContext, the document and the pane
        StyleContext sc = new StyleContext();
        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane textArea = new JTextPane(doc);
        String text = textArea.getText();



        //JTextPane textArea = new JTextPane();
        textArea.setBounds(15,130,1850,850);

        panel.add(evaluar);
        panel.add(textArea);
        evaluar.setVisible(false);
        textArea.setVisible(false);




        // Create and add the style
        final Style heading2Style = sc.addStyle("Heading2", null);
        heading2Style.addAttribute(StyleConstants.Foreground, Color.black);
        heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
        heading2Style.addAttribute(StyleConstants.FontFamily, "serif");
        heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        // Add the text to the document
                        doc.insertString(0, text, null);

                        // Finally, apply the style to the heading
                        doc.setParagraphAttributes(0, 1, heading2Style, false);
                    } catch (BadLocationException e) {
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Exception when constructing document: " + e);
            System.exit(1);
        }



        GestorDeArchivos gestorDeArchivos = new GestorDeArchivos();

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fileRout = "/home/cesar/Documents/FCC/Primavera 2017/Ingenieria de Software/Proyecto Final/src/Textos a Evaluar/";

                String file = gestorDeArchivos.GestorDeArchivos(fileRout);

                String chosenFile = JOptionPane.showInputDialog(null,file,"Elija archivo",JOptionPane.QUESTION_MESSAGE);
                /**
                 * Awful cheating
                 */
                try{
                    PrintWriter writer = new PrintWriter("/home/cesar/Documents/FCC/Primavera 2017/Ingenieria de Software/ActiveFile.txt");
                    writer.println(chosenFile);
                    writer.close();
                } catch (IOException e) {
                    // do something
                }
                chosenFile = fileRout+""+chosenFile+".txt";

                String text = gestorDeArchivos.BuscaArchivo(chosenFile);
                if(text.equals("failure")){
                    JOptionPane.showMessageDialog(null,"¡No se pudo abrir el archivo, por favor verifique datos!","Error",JOptionPane.ERROR_MESSAGE);
                    evaluar.setVisible(false);
                    textArea.setVisible(false);

                }else{

                    evaluar.setVisible(true);
                    textArea.setVisible(true);
                    textArea.setText(text);

                }
            }


        });

        evaluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                String selectedText = textArea.getSelectedText();

                if(selectedText == null){
                    JOptionPane.showMessageDialog(null,"Antes de poder evaluar necesitas seleccionar texto","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    Object[] options = {"Juicio",
                            "Apreciacion",
                            "Afecto"};

                    int evaluation = JOptionPane.showOptionDialog(null,"¿Cual es su evaluacion?","Evaluar",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

                    String givenEval = Integer.toString(evaluation);
                    /**
                     * Afecto == 2
                     * Apreciacion == 1
                     * Juicio == 0
                     * Cerrar == -1
                     */

                    String result = gestorDeArchivos.Evaluacion(selectedText, givenEval);
                    if(result.equals("failure")){
                        JOptionPane.showMessageDialog(null,"No fue posible guardar la evaluacion ya que no pude acceder a la ruta, el problema se puede arreglar con reiniciar la sesion","¡Error!",JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });


    }
}
