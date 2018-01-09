package listeners;

import tabbedPanels.panels.tabs.UpdateTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateComboBoxListener implements ActionListener {
    private UpdateTab updateTab;
    private JComboBox comboBox;

    public UpdateComboBoxListener(UpdateTab updateTab, JComboBox comboBox) {
        this.updateTab = updateTab;
        this.comboBox = comboBox;
    }

    public void actionPerformed(ActionEvent e) {
        String uniqueRecord = (String)comboBox.getSelectedItem(); // This will be of format key:key:key e.t.c.

        int i = 0;
        for (Object o : updateTab.getDatabase().getRecord(updateTab.getTableName(), uniqueRecord)){
            this.updateTab.getTextFields().get(i).setText(o.toString());
            i++;
        }
    }

}