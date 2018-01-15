package listeners;

import tabbedPanels.panels.tabs.UpdateTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteListener implements ActionListener {
    private UpdateTab updateTab;

    public DeleteListener(UpdateTab updateTab) {
        this.updateTab = updateTab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTab.getDatabase().deleteRecord(updateTab.getTableName(), updateTab.getComboBoxValue());
        updateTab.updateComboBox();
        for (JTextField t : updateTab.getTextFields()) {
            t.setText("");
        }
    }

}