package Vista;


import Control.GestorDeArchivos;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

/**
 * Created by cesar on 26/04/17.
 */
public class PantallaPrincipalAdministrador extends JPanel{

    public PantallaPrincipalAdministrador() {
        initPanel(this);
    }

    private void initPanel(JPanel panel) {
        panel.setBackground(Color.GRAY);

        JLabel admin = new JLabel();
        admin.setBounds(new Rectangle(700, 10, 600, 45));
        admin.setFont(new Font(admin.getFont().getName(), Font.BOLD, 50));
        admin.setPreferredSize(new Dimension(200, 200));
        admin.setText("Administrador");

        JLabel afecto = new JLabel("Afecto");
        afecto.setBounds(new Rectangle(15, 150, 200, 45));
        afecto.setFont(new Font(afecto.getFont().getName(), Font.ITALIC, 20));
        afecto.setPreferredSize(new Dimension(200,200));

        JLabel apreciacion = new JLabel("Apreciacion");
        apreciacion.setBounds(new Rectangle(700, 150, 200, 45));
        apreciacion.setFont(new Font(apreciacion.getFont().getName(), Font.ITALIC, 20));
        apreciacion.setPreferredSize(new Dimension(200,200));

        JLabel juicio = new JLabel("Juicio");
        juicio.setBounds(new Rectangle(1300, 150, 200, 45));
        juicio.setFont(new Font(juicio.getFont().getName(), Font.ITALIC, 20));
        juicio.setPreferredSize(new Dimension(200,200));


        panel.setLayout(null);

        // Create the StyleContext, the document and the pane
        StyleContext sc = new StyleContext();
        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        JTextPane textArea = new JTextPane(doc);
        String text = textArea.getText();

        textArea.setBounds(15,200,600,800);
        panel.add(textArea);
        textArea.setVisible(false);

        // Create and add the style
        final Style heading2Style = sc.addStyle("Heading2", null);
        heading2Style.addAttribute(StyleConstants.Foreground, Color.black);
        heading2Style.addAttribute(StyleConstants.FontSize, 16);
        heading2Style.addAttribute(StyleConstants.FontFamily, "serif");
        heading2Style.addAttribute(StyleConstants.Bold, true);

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        // Add the text to the document
                        doc.insertString(0, text, null);

                        // Finally, apply the style to the heading
                        doc.setParagraphAttributes(0, 1, heading2Style, false);
                    } catch (BadLocationException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Exception when constructing document: " + e);
            System.exit(1);
        }

        // Create the StyleContext, the document and the pane
        StyleContext sc2 = new StyleContext();
        final DefaultStyledDocument doc2 = new DefaultStyledDocument(sc2);
        JTextPane textArea2 = new JTextPane(doc2);
        String text2 = textArea2.getText();

        textArea2.setBounds(650,200,600,800);
        panel.add(textArea2);
        textArea2.setVisible(false);

        // Create and add the style
        final Style heading2Style2 = sc.addStyle("Heading2", null);
        heading2Style2.addAttribute(StyleConstants.Foreground, Color.black);
        heading2Style2.addAttribute(StyleConstants.FontSize, 16);
        heading2Style2.addAttribute(StyleConstants.FontFamily, "serif");
        heading2Style2.addAttribute(StyleConstants.Bold, true);

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        // Add the text to the document
                        doc2.insertString(0, text2, null);

                        // Finally, apply the style to the heading
                        doc2.setParagraphAttributes(0, 1, heading2Style2, false);
                    } catch (BadLocationException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Exception when constructing document: " + e);
            System.exit(1);
        }


        // Create the StyleContext, the document and the pane
        StyleContext sc3 = new StyleContext();
        final DefaultStyledDocument doc3 = new DefaultStyledDocument(sc3);
        JTextPane textArea3 = new JTextPane(doc3);
        String text3 = textArea3.getText();

        textArea3.setBounds(1300,200,600,800);
        panel.add(textArea3);
        textArea3.setVisible(false);

        // Create and add the style
        final Style heading2Style3 = sc.addStyle("Heading2", null);
        heading2Style3.addAttribute(StyleConstants.Foreground, Color.black);
        heading2Style3.addAttribute(StyleConstants.FontSize, 16);
        heading2Style3.addAttribute(StyleConstants.FontFamily, "serif");
        heading2Style3.addAttribute(StyleConstants.Bold, true);

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    try {
                        // Add the text to the document
                        doc3.insertString(0, text3, null);

                        // Finally, apply the style to the heading
                        doc3.setParagraphAttributes(0, 1, heading2Style3, false);
                    } catch (BadLocationException e) {
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Exception when constructing document: " + e);
            System.exit(1);
        }


        JButton registrar = new JButton("Registrar");
        registrar.setBounds(15, 60, 100, 40);
        panel.add(registrar);
        panel.add(admin);
        panel.add(afecto);
        panel.add(apreciacion);
        panel.add(juicio);
        afecto.setVisible(false);
        juicio.setVisible(false);
        apreciacion.setVisible(false);

        JButton compareEvaluations = new JButton("Comparar evaluaciones");
        compareEvaluations.setBounds(150, 60, 200, 40);
        panel.add(compareEvaluations);

        JButton showEvaluations = new JButton(("Mostrar evaluaciones"));
        showEvaluations.setBounds(400, 60, 200, 40);
        panel.add(showEvaluations);



        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PantallaRegistro registro = new PantallaRegistro();
                registro.PantallaRegistro();
            }
        });

        compareEvaluations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GestorDeArchivos evaluaciones = new GestorDeArchivos();
                String failure = evaluaciones.EvaluacionesCompletas();

                if(failure.equals("true")){
                    JOptionPane.showMessageDialog(null,"¡No hay suficientes evaluaciones para generar el archivo!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showEvaluations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String text = "", text2 = "", text3 = "";
                Scanner reading, reading2, reading3;
                String afectoFile = "Afecto.txt", juicioFile = "Juicio.txt", apreciacionFile = "Apreciacion.txt";
                String rout = new File("./src").getAbsolutePath() + "/Union de textos/";
                try {
                    reading = new Scanner(new File(rout+afectoFile));
                    while (reading.hasNextLine()) {
                        Scanner word = new Scanner(reading.nextLine());
                        while (word.hasNext()) {
                            text += word.nextLine()+"\n";
                        }
                    }
                    try {
                        reading2 = new Scanner(new File(rout + apreciacionFile));
                        while (reading2.hasNextLine()) {
                            Scanner word2 = new Scanner(reading2.nextLine());
                            while (word2.hasNext()) {
                                text2 += word2.nextLine() + "\n";
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                        try {
                            reading3 = new Scanner(new File(rout + juicioFile));
                            while (reading3.hasNextLine()) {
                                Scanner word3 = new Scanner(reading3.nextLine());
                                while (word3.hasNext()) {
                                    text3 += word3.nextLine() + "\n";
                                }
                            }
                        } catch (Exception e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    afecto.setVisible(true);
                    juicio.setVisible(true);
                    apreciacion.setVisible(true);
                    textArea.setText(text);
                    textArea2.setText(text2);
                    textArea3.setText(text3);
                    textArea.setVisible(true);
                    textArea2.setVisible(true);
                    textArea3.setVisible(true);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"No hay evaluaciones unidas aun!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
