/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp1555.coursework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */
public class Tables extends JFrame {

    String[] parameterList = {"No. of Bathrooms", "Area of the Site(1000's sq. feet)", "Size of living space(1000's sq. feet)", "No. of Garages", "No. of Rooms", "No. of Bedrooms", "Age of Property(Years)"};

    JTable InputData;
    JTable DataSummaryI;
    JTable LR;
    JTable DataSummaryII;
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    Algorithm class2 = new Algorithm();
    private double meanX;
    private double meanY;

    DrawGraph class1 = new DrawGraph();

    public Tables() throws IOException {
        setLayout(new FlowLayout());
        setSize(600, 600);
        setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        String dataColA[] = {parameterList[Index()], "Selling Prices(X 100,000 Pounds)"};
        InputData = new JTable(DataA(), dataColA);
        InputData.setEnabled (false);
        InputData.setFillsViewportHeight(true);
        InputData.getTableHeader().setReorderingAllowed(false);
        InputData.setPreferredScrollableViewportSize(new Dimension(500, 160));
        JScrollPane tA = new JScrollPane(InputData);
        panel.setSize(1000, 400);
        panel.add(tA);
        add(panel);

        String dataColB[] = {"", parameterList[Index()], "Selling Prices(X 100,000 Pounds)"};
        DataSummaryI = new JTable(DataB(), dataColB);
        DataSummaryI.setEnabled (false);
        DataSummaryI.setFillsViewportHeight(true);
        DataSummaryI.getTableHeader().setReorderingAllowed(false);
        DataSummaryI.setPreferredScrollableViewportSize(new Dimension(500, 100));
        JScrollPane tB = new JScrollPane(DataSummaryI);
        panel1.add(tB);
        add(panel1);

        String dataColC[] = {"Data", "Values"};
        DataSummaryII = new JTable(DataC(), dataColC);
        DataSummaryII.setEnabled (false);
        DataSummaryII.setFillsViewportHeight(true);
        DataSummaryII.getTableHeader().setReorderingAllowed(false);
        DataSummaryII.setPreferredScrollableViewportSize(new Dimension(500, 100));
        JScrollPane tC = new JScrollPane(DataSummaryII);
        panel4.add(tC);
        add(panel4);

        String dataColD[] = {"R", "R^2", "Slope", "Y-Intercept"};
        LR = new JTable(DataD(), dataColD);
        LR.setEnabled (false);
        LR.setFillsViewportHeight(true);
        LR.getTableHeader().setReorderingAllowed(false);
        LR.setPreferredScrollableViewportSize(new Dimension(550, 25));
        JScrollPane tD = new JScrollPane(LR);
        panel3.add(tD);
        add(panel3);

        setVisible(true);
    }

    public String[][] DataA() throws IOException { // Table for the datas

        String data[][] = new String[class2.N()][2];

        FileInputStream fs = new FileInputStream(class1.FileSelector());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < class2.showValueCombobox() + 1; ++i) { // loop to run through a specific line in the data set text file. and add getindex for i
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");

            for (int i = 0; i < class2.N(); i++) {
                data[i][0] = tokens[i]; //X data

            }
            break;
        }
        FileInputStream fsY = new FileInputStream(class1.FileSelector());
        BufferedReader brY = new BufferedReader(new InputStreamReader(fsY));
        for (int i = 0; i < 0; ++i) { // loop to run through a specific line in the data set text file. and add getindex for i
            brY.readLine();
        }
        String lineY = null;
        while ((lineY = brY.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = lineY.split("\\s+");

            for (int i = 0; i < class2.N(); i++) {
                data[i][1] = tokens[i]; // Y data
            }
            break;
        }
        return data;
    }

    public String[][] DataB() throws IOException { 

        String data[][] = new String[4][3];

        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.CEILING);
        
        data[0][0] = "N";
        data[1][0] = "Mean";
        data[2][0] = "Variance";
        data[3][0] = "Standard Deviation";
        data[0][1] = Integer.toString(class2.N());
        data[0][2] = Integer.toString(class2.N());
        data[1][1] = df.format(class1.meanx(meanX));
        data[1][2] = df.format(class1.meany(meanY));
        data[2][1] = df.format(class2.VarianceX());
        data[2][2] = df.format(class2.VarianceY());
        data[3][1] = df.format(class2.StandardDeviationX());
        data[3][2] = df.format(class2.StandardDeviationY());

        return data;
    }

    public String[][] DataC() throws IOException {
        String data[][] = new String[5][2];

        DecimalFormat df = new DecimalFormat("#.#####");
        df.setRoundingMode(RoundingMode.CEILING);

        data[0][0] = "\u03A3 X";
        data[1][0] = "\u03A3 X^2";
        data[2][0] = "\u03A3 Y";
        data[3][0] = "\u03A3 Y^2";
        data[4][0] = "\u03A3 XY";
        data[0][1] = Double.toString(class2.SummationX());
        data[1][1] = Double.toString(class2.SummationX2());
        data[2][1] = Double.toString(class2.SummationY());
        data[3][1] = (df.format(class2.SummationY2()));
        data[4][1] = Double.toString(class2.SummationXY());

        return data;

    }

    public String[][] DataD() throws IOException {

        String data[][] = new String[1][4];
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        data[0][0] = Double.toString(class2.R());
        data[0][1] = Double.toString(class2.R2());
        data[0][2] = df.format(class1.Beta1());
        data[0][3] = df.format(class1.Beta0());

        return data;

    }

    private int Index() throws IOException { // Gets the index of the parameter selected.

        double d = class2.showValueCombobox();
        int i = (int) d;

        return i;
    }
}
