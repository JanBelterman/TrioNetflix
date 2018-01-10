package tabbedPanels;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {

    public Footer() {
        createComponents();
    }

    private void createComponents() {
        setLayout(new BorderLayout());
        add(new JLabel("Netflix statistics"), BorderLayout.WEST);
        add(new JLabel("Informatica - 2018 - 23IVT1A1 - Chris Boer, Floris Botermans & Jan Belterman"), BorderLayout.EAST);
    }

}