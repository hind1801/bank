package presentation.vue.palette;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class TableModel<T> extends AbstractTableModel {

    protected String[]    columnsNames;
    protected Object[][]  data;


    public TableModel(List<T> objects, String...columnsNames) {
        initColumns(columnsNames);
        initData(objects);
    }

    public void initColumns(String... colNames){
        columnsNames = new String[colNames.length];

        for (int i=0; i<colNames.length ; i++)
                     columnsNames[i] = colNames[i];
    }
    public abstract void initData(List<T> objects);

    @Override
    public String getColumnName(int col) { return columnsNames[col];}
    @Override
    public int getRowCount() { return data.length;}
    @Override
    public int getColumnCount() { return columnsNames.length;}
    @Override
    public Object getValueAt(int row, int col) {return data[row][col];}
}
