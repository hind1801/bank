package presentation.vue.palette;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public  class TablePanel<Y extends TableModel > extends JPanel {

    protected JTable table;
    protected Y tableModel;
    protected JScrollPane scrollPane;
    protected SearchPanel searchPanel;


    public Y getTableModel(){
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }

    public TableCrudPanel getCrudPanel(){
        return searchPanel.getCrudPanel();
    }

    private void initTable(){
        table       = new JTable(tableModel);
        table.setFont(new Font("Optima", Font.BOLD, 17));
        table.setForeground(new Color(16, 44, 114));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Optima", Font.BOLD, 20));
        header.setForeground(new Color(198, 113, 34));
        header.setBackground(Color.WHITE);

        ((DefaultTableCellRenderer)header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);

        scrollPane = new JScrollPane(table);

        searchPanel = new SearchPanel(Color.white);

    }


    public TablePanel(){}

    public TablePanel(Y tableModel){
        this.tableModel  = tableModel;
        initTable();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
    }
}
