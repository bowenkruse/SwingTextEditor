package Lab9;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TestGradesTable extends JFrame {
    JTable gradesTable;
    JFrame tableFrame;
    JPanel statsPanel;
    JTextField avgTextField;
    JTextField maxTestField;
    JTextField minTextField;
    JTextField classLabelTextField;

    TestGradesTable(String x) {

        // separate JFrame to display JTable
        tableFrame = new JFrame("Test Grades Table");
        tableFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        tableFrame.setLayout(new BorderLayout());

        // JTable column names
        String[] columnNames = {"ID","Name","Test","Grade"};

        // Fill data array of strings with individual lines from text area
        String[][] data = new String[x.split("\\n").length][4];
        int i = 0;
        for (String line : x.split("\\n")) {
            String[] infoArray = line.split(";");
            data[i][0] = infoArray[0];
            data[i][1] = infoArray[1];
            data[i][2] = infoArray[2];
            data[i][3] = infoArray[3];
            i++;
        }
        // create new JTable from data array
        gradesTable = new JTable(data,columnNames);
        gradesTable.setSize(500,200);

        // JScroll Pane with JTable
        JScrollPane gradesScrollPane = new JScrollPane(gradesTable);

        // JPanel for buttons and statistics work
        statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(4,2));
        statsPanel.setSize(500,200);

        // Components of statsPanel
        JLabel statsPanelLabel = new JLabel("Statistics");
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK,1);
        statsPanelLabel.setBorder(blackBorder);
        classLabelTextField = new JTextField();
        String studentCount = String.valueOf(x.split("\\n").length);
        classLabelTextField.setText("CMP 256 Sec 1 Students: "+studentCount);
        avgTextField = new JTextField();
        JButton average = new JButton("Average");
        average.addActionListener(actionEvent -> averageEvent(data));

        maxTestField = new JTextField();
        JButton maximum = new JButton("Maximum");
        maximum.addActionListener(actionEvent -> maxEvent(data));

        minTextField = new JTextField();
        JButton minimum = new JButton("Minimum");
        minimum.addActionListener(actionEvent -> minEvent(data));

        // adding components to statsPanel
        statsPanel.add(statsPanelLabel,BorderLayout.CENTER);
        statsPanel.add(classLabelTextField);
        statsPanel.add(average);
        statsPanel.add(avgTextField);
        statsPanel.add(maximum);
        statsPanel.add(maxTestField);
        statsPanel.add(minimum);
        statsPanel.add(minTextField);
        tableFrame.add(statsPanel,BorderLayout.SOUTH);
        tableFrame.add(gradesScrollPane, BorderLayout.NORTH);
        tableFrame.setBounds(501,0,500,400);
        tableFrame.setVisible(true);
    }

    public void averageEvent(String[][] data) {
        double sum = 0;
        for (String[] grade : data) {
            sum += Double.parseDouble(grade[3]);
        }
        double avg = sum/data.length;
        avgTextField.setText(avg +" %");
    }

    public void minEvent(String[][] data) {
        double min = Double.parseDouble(data[0][3]);
        for (String[] grade : data) {
            if (Double.parseDouble(grade[3]) < min)
                min = Double.parseDouble(grade[3]);
        }
        minTextField.setText(min +" %");
    }

    public void maxEvent(String[][] data) {
        double max = 0;
        for (String[] grade : data) {
            if (Double.parseDouble(grade[3]) > max) {
                max = Double.parseDouble(grade[3]);
            }
            maxTestField.setText(max +" %");
        }
    }
}
