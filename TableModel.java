import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TableModel extends DefaultTableModel {
    private ArrayList<String[]> values;

    public void setValues(ArrayList<String[]> values) {
        this.values = values;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return values.get(row)[column];
    }

    @Override
    public int getRowCount() {
        if(values == null)
            return 0;
        return values.size();
    }

    @Override
    public String getColumnName(int column) {
        return "Column" + column;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
}
