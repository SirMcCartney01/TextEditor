package Vista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JButton registrar = new JButton("Registrar");
        registrar.setBounds(15, 60, 100, 40);
        panel.setLayout(null);
        panel.add(registrar);
        panel.add(admin);

        JButton compareEvaluations = new JButton("Comparar evaluaciones");
        compareEvaluations.setBounds(150, 60, 200, 40);
        panel.add(compareEvaluations);


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

            }
        });

    }
}
