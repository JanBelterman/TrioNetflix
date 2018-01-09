package userInterface;

import database.Database;
import tabbedPanels.panels.InsertPanel;
import tabbedPanels.TabbedFrame;
import tabbedPanels.panels.OverviewPanel;
import tabbedPanels.panels.TablesPanel;
import tabbedPanels.panels.UpdatePanel;

import javax.swing.*;
import java.awt.*;

// Class UserInterface, creates a tabbedFrame and is able to add JPanels to it
public class UserInterface implements Runnable {
    private TabbedFrame tabbedFrame;
    private Database database;

    // Constructor
    public UserInterface(Database database) {
        this.database = database;
    }

    // Overridden method run (from the Runnable-interface), program is started from this method
    @Override
    public void run() {
        tabbedFrame = new TabbedFrame(); // Create a new TabbedFrame
        tabbedFrame.setPreferredSize(new Dimension(1000, 500)); // Set the JFrame's size
        tabbedFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Make the close button of the JFrame work
        tabbedFrame.setTitle("Netflix statistics"); // Set tabbedFrame's title (actually this calls the JFrame's setTitle method)
        createTabs(); // Call createTabs method
        tabbedFrame.pack(); // Pack the frame
        tabbedFrame.setVisible(true); // Make the tabbedFrame visible
    }

    // Method createTabs, adds tabs to the tabbedFrame (which are JPanel's)
    private void createTabs() {
        this.tabbedFrame.addNewOne(new TablesPanel(this.database), "Overview");
        this.tabbedFrame.addNewOne(new UpdatePanel(this.database), "Update / delete data");
        this.tabbedFrame.addNewOne(new InsertPanel(this.database), "Insert data");
        this.tabbedFrame.addNewOne(new OverviewPanel(this.database), "Overviews");
    }

}