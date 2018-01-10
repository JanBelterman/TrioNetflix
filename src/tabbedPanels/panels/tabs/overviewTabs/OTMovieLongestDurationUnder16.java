package tabbedPanels.panels.tabs.overviewTabs;

import tabbedPanels.panels.OverviewPanel;

import javax.swing.*;
import java.awt.*;

public class OTMovieLongestDurationUnder16 extends JPanel {
    private OverviewPanel overviewPanel;
    private JLabel errorLabel;

    public OTMovieLongestDurationUnder16(OverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
        createComponents();
    }

    private void createComponents() {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("");
        label.setFont(new Font("Verdana", Font.BOLD, 12));
        add(label);
    }
}
