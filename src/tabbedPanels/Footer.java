package tabbedPanels;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {

    public Footer() {
        createComponents();
    }

    private void createComponents() {
        setLayout(new BorderLayout());
        JLabel label1 = new JLabel("Netflix statistics");
        JLabel label2 = new JLabel("Informatica - 2018 - 23IVT1A1 - Chris Boer, Floris Botermans & Jan Belterman");

        label1.setFont(new Font("Verdana", Font.BOLD, 14));
        label2.setFont(new Font("Verdana", Font.BOLD, 14));

        add(label1, BorderLayout.WEST);
        add(label2, BorderLayout.EAST);
    }

}