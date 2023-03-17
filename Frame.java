import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Frame extends JFrame {
    private JTable jTable;
    private JButton openCSV;
    private JFileChooser fileChooser;
    private  JToolBar toolBar;


    TableModel tableModel;
    public Frame() throws HeadlessException {
        this.setVisible(true);
        this.setBounds(100, 100, 900, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        init();
    }

    private void init() {
        openCSV = new JButton("Select CSV");
        setAction();
        fileChooser = new JFileChooser();
        toolBar = new JToolBar();
        toolBar.add(openCSV);
        this.add(toolBar, BorderLayout.NORTH);
        jTable = new JTable();
        this.add(new JScrollPane(jTable), BorderLayout.CENTER);
        tableModel = new TableModel();
        jTable.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    private void setAction() {
        openCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = fileChooser.showOpenDialog(Frame.this);
                if(i == JFileChooser.APPROVE_OPTION) {
                    System.out.println("file choosed");
                    File file = fileChooser.getSelectedFile();
                    System.err.println(file);
                    ArrayList<String[]> rows = readCSV(file);
                    TableModel tableModel = new TableModel();
                    jTable.setModel(tableModel);

                    tableModel.setValues(rows);
                    tableModel.fireTableDataChanged();
                }
            }
        });
    }
    private ArrayList<String[]> readCSV(File file) {
        ArrayList<String[]> data = new ArrayList<>();
        try {
            FileReader fileReader =  new FileReader(file);
            CSVReader csvReader = new CSVReader(fileReader);
            Iterator<String[]> rows =  csvReader.iterator();
            while(rows.hasNext()) {
                String[] cols = rows.next();
                data.add(cols);
            }
            System.out.println("Frame.readCSV: " + data.size());

        }catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        return data;
    }
}
