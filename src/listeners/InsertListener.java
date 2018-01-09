package listeners;

import tabbedPanels.panels.tabs.InsertTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InsertListener implements ActionListener {
    private InsertTab insertTab;

    public InsertListener(InsertTab insertTab) {
        this.insertTab = insertTab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Object> newValues = new ArrayList<>();
        for (JTextField t : this.insertTab.getTextFields()) {
            newValues.add(t.getText());
        }
        this.insertTab.getDatabase().insertRecord(newValues, insertTab.getTableName());
    }

}