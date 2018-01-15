package listeners;

import tabbedPanels.panels.tabs.UpdateTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateListener implements ActionListener {
    private UpdateTab updateTab;

    public UpdateListener(UpdateTab updateTab) {
        this.updateTab = updateTab;
    }

    public void actionPerformed(ActionEvent e) {
        String originalKey = updateTab.getComboBoxValue();
        ArrayList<Object> newValues = new ArrayList<>();
        for (JTextField t : updateTab.getTextFields()) {
            newValues.add(t.getText());
            t.setText("");
        }
        updateTab.getDatabase().updateRecord(updateTab.getTableName(), newValues, originalKey);
        updateTab.updateComboBox();

    }

}