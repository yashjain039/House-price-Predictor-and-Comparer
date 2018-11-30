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
public class ForecastTable extends JFrame {

    String[] parameterList = {"No. of Bathrooms", "Area of the Site(1000's sq. feet)", "Size of living space(1000's sq. feet)", "No. of Garages", "No. of Rooms", "No. of Bedrooms", "Age of Property(Years)"};

    JTable ForecastData;
    JPanel panel = new JPanel();
    
    

    DrawGraph class1 = new DrawGraph();
    Algorithm class2 = new Algorithm();

    public ForecastTable() throws IOException {
        setLayout(new FlowLayout());
        setSize(700, 250);
        setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        String dataColA[] = {parameterList[class1.BestR2()], "Selling Prices(X 100,000 Pounds)", "Forecasted Y", "Standard Error of Estimate"};
        ForecastData = new JTable(ForecastY(), dataColA);
        ForecastData.setEnabled (false);
        ForecastData.setFillsViewportHeight(true);
        ForecastData.getTableHeader().setReorderingAllowed(false);
        ForecastData.setPreferredScrollableViewportSize(new Dimension(600, 160));
        JScrollPane tA = new JScrollPane(ForecastData);
        panel.setSize(1000, 400);
        panel.add(tA);
        add(panel);

        setResizable(false);
        setVisible(true);

    }

    public String[][] ForecastY() throws IOException {// table for Forecasted Y, Y, Standard error of Estimate

        String data[][] = new String[10][4];

        int index = (int) class1.CshowValueCombobox();
        String file = null;

        if (index == 0) {
            file = "TownA.txt";
        }
        if (index == 1) {
            file = "TownB.txt";
        }
        if (index == 2) {
            file = "TownC.txt";
        }

        FileInputStream fs = new FileInputStream(file); // reads the line that is the choosen parameter.
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < class1.BestR2() + 1; ++i) { // loop to run through a specific line in the data set text file.
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokensX = line.split("\\s+");

            Double[] intarrayX = new Double[tokensX.length]; // data of x for choosen parameter
            int i = 0;
            for (String str : tokensX) {
                intarrayX[i] = Double.parseDouble(str.trim());//Exception in this line
                i++;
            }

            for (int a = 0; a < 10; a++) {
                data[a][0] = tokensX[a];

            }

            for (int j = 0; j < 10; j++) { // Forcasted Y
                double y = (class1.Beta1() * intarrayX[j]) + class1.Beta0();

                DecimalFormat df = new DecimalFormat("#.#");
                df.setRoundingMode(RoundingMode.CEILING);

                data[j][2] = df.format(y);

            }
            break;
        }

        FileInputStream fsY = new FileInputStream(file);
        BufferedReader brY = new BufferedReader(new InputStreamReader(fsY));
        for (int i = 0; i < 0; ++i) { // loop to run through a specific line in the data set text file. and add getindex for i
            brY.readLine();
        }
        String lineY = null;
        while ((lineY = brY.readLine()) != null) { // Selling Price
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = lineY.split("\\s+");

            for (int i = 0; i < 10; i++) {
                data[i][1] = tokens[i];

                FileInputStream fs1 = new FileInputStream(file); // reads the line that is the choosen parameter.
                BufferedReader br1 = new BufferedReader(new InputStreamReader(fs1));
                for (int c = 0; c < class1.BestR2() + 1; ++c) { // loop to run through a specific line in the data set text file.
                    br1.readLine();
                }
                String line1 = null;
                while ((line1 = br1.readLine()) != null) {
                    // \\s+ means any number of whitespaces between tokens
                    String[] tokensX = line1.split("\\s+");

                    Double[] intarrayX = new Double[tokensX.length]; // data of x for choosen parameter
                    int c = 0;
                    for (String str : tokensX) {
                        intarrayX[c] = Double.parseDouble(str.trim());//Exception in this line
                        c++;
                    }

                    double y = (class1.Beta1() * intarrayX[i]) + class1.Beta0();// forecasted Y

                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);

                    double num = Math.abs(Double.parseDouble(tokens[i]) - y);
                    double den = Double.parseDouble(tokens[i]);
                    double est = (Double.parseDouble(df.format(num)) / den);
                    double finest = est * 100; //Standard Error of Estimate

                    // double est = (Math.abs((y - Double.parseDouble(tokens[i])) / y)) * 100;
                    data[i][3] = df.format(finest) + "%";
                    break;
                }

            }
            break;
        }
        return data;
    }
}
