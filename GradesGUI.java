package Lab9;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

 public class  GradesGUI extends JFrame implements ActionListener {
    Container container = getContentPane();

    // Text Area
    JTextArea textArea = new JTextArea();
    // Menu bar component
    JMenuBar menuBar = new JMenuBar();

    // 'File' JMenu and its JMenuItems
    JMenu File = new JMenu("File");
    JMenuItem New = new JMenuItem("New");
    JMenuItem Open = new JMenuItem("Open");
    JMenuItem Save = new JMenuItem("Save");
    JMenuItem Exit = new JMenuItem("Exit");

    // 'Tools' JMenu and its JMenuItems
    JMenu Tools = new JMenu("Tools");
    JMenuItem GenerateTable = new JMenuItem("Generate Table");
    JMenuItem ComputeAverage = new JMenuItem("Compute Average");
    JMenuItem FindMaximum = new JMenuItem("Find Maximum");
    JMenuItem FindMinimum = new JMenuItem("Find Minimum");

    // 'Help' JMenu and its JMenuItems
    JMenu Help = new JMenu("Help");
    JMenuItem About = new JMenuItem("About");
    JMenuItem HowTo = new JMenuItem("How To");

    GradesGUI() {
        setLayout();
        addComponents();
        addEvents();
    }
    public void setLayout() {
        // Set Layout of JFrame Container
        container.setLayout(new BorderLayout());
    }

    public void addEvents() {

        // 'New' JMenuItem Event
        New.addActionListener(this);
        // 'Open' JMenuItem Event
        Open.addActionListener(this);
        // 'Save' JMenuItem Event
        Save.addActionListener(this);
        // 'Exit' JMenuItem Event
        Exit.addActionListener(this);

        // 'Generate Table' JMenuItem Event
        GenerateTable.addActionListener(this);
        // 'Compute Average' JMenuItem Event
        ComputeAverage.addActionListener(this);
        // 'Find Maximum' JMenuItem Event
        FindMaximum.addActionListener(this);
        // 'Find Minimum' JMenuItem Event
        FindMinimum.addActionListener(this);

        // 'About' JMenuItem Event
        About.addActionListener(this);
        // 'How To' JMenuItem Event
        HowTo.addActionListener(this);
    }

    public void addComponents() {

        // menu bar
        container.add(menuBar,BorderLayout.NORTH);

        // Menu Bar Components
        menuBar.add(File);
        File.add(New);
        File.add(Open);
        File.add(Save);
        File.add(Exit);
        menuBar.add(Tools);
        Tools.add(GenerateTable);
        Tools.add(ComputeAverage);
        Tools.add(FindMaximum);
        Tools.add(FindMinimum);
        menuBar.add(Help);
        Help.add(About);
        Help.add(HowTo);
    }

    public void actionPerformed(ActionEvent actionEvent) {

        // 'New' Action Event
        if (actionEvent.getSource() == New) {
            // text area
            container.add(textArea,FlowLayout.CENTER);
            textArea.setText("");
        }

        // 'Open' Action Event
        else if (actionEvent.getSource() == Open) {
            // text area
            container.add(textArea,FlowLayout.CENTER);
            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
            jFileChooser.setAcceptAllFileFilterUsed(false);
            jFileChooser.setDialogTitle("Select a .txt file");

            // Only .txt files may be used
            FileNameExtensionFilter restrictTxt = new FileNameExtensionFilter("Only .txt file","txt");
            jFileChooser.addChoosableFileFilter(restrictTxt);

            // show text from selected text file
            int r = jFileChooser.showOpenDialog(null);

            // When/if user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try {

                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    textArea.read(bufferedReader,file);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(container,e.getMessage());
                }
            }

        }

        // 'Save' Action Event
        else if (actionEvent.getSource() == Save) {
            JFileChooser jFileChooser = new JFileChooser();
            int r = jFileChooser.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(jFileChooser.getSelectedFile().getAbsolutePath());

                try {
                    // Create File Writer
                    FileWriter writer = new FileWriter(fi,false);

                    // Create buffered writer to write with
                    BufferedWriter bufferedWriter = new BufferedWriter(writer);

                    // Write to file
                    bufferedWriter.write(textArea.getText());
                    bufferedWriter.flush();
                    bufferedWriter.close();

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(container,e.getMessage());
                }
            }
        }

        // 'Exit' Action Event
        else if (actionEvent.getSource() == Exit) {
            System.exit(0);
        }

        // 'Generate Table' Action Event
        if (actionEvent.getSource() == GenerateTable) {
            // separate JFrame to display JTable
            new TestGradesTable(textArea.getText());
        }

        // 'Compute Average' Action Event
        else if (actionEvent.getSource() == ComputeAverage) {
            // Fill data array of strings with individual lines from text area
            String[][] data = new String[textArea.getText().split("\\n").length][4];
            int i = 0;
            for (String line : textArea.getText().split("\\n")) {
                String[] infoArray = line.split(";");
                data[i][0] = infoArray[0];
                data[i][1] = infoArray[1];
                data[i][2] = infoArray[2];
                data[i][3] = infoArray[3];
                i++;
            }
            new TestGradesTable(textArea.getText()).averageEvent(data);
        }

        // 'Find Maximum' Action Event
        else if (actionEvent.getSource() == FindMaximum) {
            // Fill data array of strings with individual lines from text area
            String[][] data = new String[textArea.getText().split("\\n").length][4];
            int i = 0;
            for (String line : textArea.getText().split("\\n")) {
                String[] infoArray = line.split(";");
                data[i][0] = infoArray[0];
                data[i][1] = infoArray[1];
                data[i][2] = infoArray[2];
                data[i][3] = infoArray[3];
                i++;
            }
            new TestGradesTable(textArea.getText()).maxEvent(data);
        }

        // 'Find Minimum' Action Event
        else if (actionEvent.getSource() == FindMinimum) {
            // Fill data array of strings with individual lines from text area
            String[][] data = new String[textArea.getText().split("\\n").length][4];
            int i = 0;
            for (String line : textArea.getText().split("\\n")) {
                String[] infoArray = line.split(";");
                data[i][0] = infoArray[0];
                data[i][1] = infoArray[1];
                data[i][2] = infoArray[2];
                data[i][3] = infoArray[3];
                i++;
            }
            new TestGradesTable(textArea.getText()).minEvent(data);
        }

        // 'About' Action Event
        else if (actionEvent.getSource() == About) {
            // text area
            container.add(textArea,FlowLayout.CENTER);

            String AboutText = "\t\tAbout\n\n" +
                    "\tThis program uses the Java Swing GUI Widget Toolkit to create a basic text editor\n" +
                    "and text file handler to process data about student data in a course. The program also\n allows" +
                    " the user to do statistical work with the information read from the text \nfiles using the tools" +
                    "provided in the 'tool' menu on the top menu bar. \n\n\tFunctionalities include creating a new text" +
                    "file, saving to a text file, opening \ntext files on the host machine, the ability to generate" +
                    " a table representing \nprocessed data from the text files, and calculator functions to find the" +
                    "Average \nTest score, Maximum Test Score, and Minimum Test Score in the course.\n " +
                    "For instructions on using the program in more detail, see \nthe " +
                    "'How to' option under the Help Menu \n\n\n\tAuthor: Bowen T Kruse, April 12, 2020";
            textArea.setText(AboutText);
        }

        // 'How To' Action Event
        else if (actionEvent.getSource() == HowTo) {
            // text area
            container.add(textArea,FlowLayout.CENTER);
            String HowTo = "\t\tHow To\n\nCreating a new file: Under 'File' selected 'New'. Then enter data following the\n" +
                    "below format:\nID;Name;Test;Grade\nwhere the fields of data are separated by semicolons ';'\n" +
                    "\nSaving a file: Under 'File' selected 'Save' and choose a file name and location, ensuring\n" +
                    "that the file is save as a .txt type file.\n\n" +
                    "Opening a file: Under 'File select 'Open' and choose a file to open, then click 'open' in the\n" +
                    "File Chooser window\n\nStatistic Tools: Under the menu 'tools' the 'Generate Table' option parses " +
                    "the data \ncurrently being displayed on the main screen, either typed or opened from an " +
                    "\nexisting file. The 'generate table' option opens a window in which a variety of tools are \n" +
                    "available along with a table display of the information provided. The three options\n" +
                    "below 'generate table' are short cuts to the respective calculations, shown in a separate\n" +
                    "window, just as the 'generate table' option provides. ";
            textArea.setText(HowTo);
        }
    }

    public static void main(String[] args) {
        GradesGUI ClassGradesFrame = new GradesGUI();
        ClassGradesFrame.setTitle("Class Grades");
        ClassGradesFrame.setVisible(true);
        ClassGradesFrame.setSize(500,500);
        ClassGradesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
