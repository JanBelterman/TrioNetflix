package listeners;

import tabbedPanels.panels.TablesPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SwitchTableListener implements ActionListener {
    private TablesPanel tablesPanel;
    private String tableName;

    public SwitchTableListener(TablesPanel tablesPanel, String tableName) {
        this.tablesPanel = tablesPanel;
        this.tableName = tableName;
    }

    public void actionPerformed(ActionEvent e) {

        this.tablesPanel.updateTable(tableName);
    }

}