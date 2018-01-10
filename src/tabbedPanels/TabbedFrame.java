package tabbedPanels;

import javax.swing.*;
import java.awt.*;

// Class TabbedFrame, creates a JFrame with a JTabbedPane (which holds JPanels)
public class TabbedFrame extends JFrame {
    private JTabbedPane tabContainer;

    // Constructor
    public TabbedFrame() {
        this.getContentPane().setLayout(new BorderLayout());
        this.tabContainer = new JTabbedPane(); // Create JTabbedPane
        this.tabContainer.setFont(new Font("Verdana", Font.BOLD, 16)); // Set font for JTabbedPane
        getContentPane().add(tabContainer, BorderLayout.CENTER); // Add the JTabbedPane to the JFrame's contentPane (which is a JPanel)
        createFooter();
    }

    private void createFooter() {
        this.getContentPane().add(new Footer(), BorderLayout.SOUTH);
    }

    // Method addNewOne, adds a new JPanel (together with its title) to the JTabbedPane
    public void addNewOne(JPanel panel, String title) {
        this.tabContainer.addTab(title, panel);
    }

}