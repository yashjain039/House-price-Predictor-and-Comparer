/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp1555.coursework;

import java.awt.BasicStroke;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.green;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * 
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */
public class DrawGraph extends JPanel {

    String[] parameterList = {"No. of Bathrooms", "Area of the Site(1000's sq. feet)", "Size of living space(1000's sq. feet)", "No. of Garages", "No. of Rooms", "No. of Bedrooms", "Age of Property(Years)"};

    private int width = 800;
    private Double[] best = new Double[7];
    private static double meanX = 0;
    private static double meanY = 0;
    private static double Sxy = 0;
    private static double Sxx = 0;
    private static double Beta1 = 0;
    private static double Beta0 = 0;
    private static List<Double> Xdata;
    private static List<Double> Ydata;
    private int heigth = 400;
    private int padding = 25;
    private int labelPadding = 50;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(200, 0, 0);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 12;
    private int numberYDivisions = 10;
    private static double sum = 0;
    private static double mean = 0;
    public int checker;
    private int index;
    private static double Syy = 0;
    private double R2;
    private double Cvalue;
    private String file;

    public int checkFile() throws FileNotFoundException, IOException { // check the existence of value in the file of the inputed no. for prediction.
        BufferedReader br = new BufferedReader(new FileReader("Checker.txt"));
        String check = br.readLine();
        checker = 0;
        if ("Main".equals(check)) {
            checker = 1;
        }
        if ("Predict".equals(check)) {
            checker = 0;
        }
        if ("Compare".equals(check)) {
            checker = 2;
        }

        if ("UserData".equals(check)) {
            checker = 3;
        }
        //System.out.print(checker);
        return checker;
    }

    public String FileSelector() throws IOException { // Select the file depending on the user choice i.e. User data or the System's Data

        if (checkFile() == 1) {
            file = "Notes.txt";
        }
        if (checkFile() == 3) {
            file = "UserData.txt";
        }
        if (checkFile() == 0) {
            file = "Notes.txt";
        }
        if (checkFile() == 2) {
            file = "Notes.txt";
        }

        return file;
    }

    public double MAX_X() throws IOException { // gets the max value of X data
        List<Double> arrayList = new ArrayList<Double>();
        arrayList = getArrayX();
        double max = Math.ceil(Collections.max(arrayList));

        double a = max - (max % 10);
        double b = a + 10;

        //System.out.println(b);
        return b;
    }

    public double MAX_Y() throws IOException { // gets the max value of Y data
        List<Double> arrayList = new ArrayList<Double>();
        arrayList = getArrayY();
        double max = Math.ceil(Collections.max(arrayList));

        double a = max - (max % 10);
        double b = a + 10;

        //System.out.println(b);
        return b;
    }

    public double MaxValue() throws IOException {

        List<Double> arrayList = new ArrayList<Double>();

        //
        double max = 0;
        double Cmax = 0;
        double finalmax = 0;
        if (checkFile() == 1 || checkFile() == 0 || checkFile() == 3 || checkFile() == 2) {

            arrayList = getArrayX();
//        double max = (Collections.max(arrayList));
            max = (Collections.max(arrayList));
            finalmax = max;
        }

        if (checkFile() == 2) {
            arrayList = CgetArrayX();
            Cmax = (Collections.max(arrayList));
            if (max > Cmax) {
                finalmax = max;
            } else {
                finalmax = Cmax;
            }
        }
        return finalmax;

    }

    public List<Double> getArrayX() throws FileNotFoundException, IOException { // Gets the data of the selected X factor in an Array

        FileInputStream fs = new FileInputStream(FileSelector());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < File() + 1; ++i) { // loop to run through a specific line in the data set text file. and add getindex for i
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");

            Double[] intarray = new Double[tokens.length];
            int i = 0;
            for (String str : tokens) {
                intarray[i] = Double.parseDouble(str.trim());//Exception in this line
                i++;
            }

            Xdata = Arrays.asList(intarray);

            //System.out.println(Xdata);
            //System.out.print(Arrays.toString(intarray));
            break;
        }

        return Xdata;
    }

    public List<Double> getArrayY() throws FileNotFoundException, IOException { // Gets the data of the selected Y factor in an Array

        FileInputStream fs = new FileInputStream(FileSelector());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < 0; ++i) { // loop to run through a specific line in the data set text file.
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");

            Double[] intarray = new Double[tokens.length];
            int i = 0;
            for (String str : tokens) {
                intarray[i] = Double.parseDouble(str.trim());//Exception in this line
                i++;
            }

            Ydata = Arrays.asList(intarray);

            //System.out.println(Ydata);
            // System.out.print(Arrays.toString(intarray));
            break;
        }

        return Ydata;
    }

    public List<Double> CgetArrayX() throws FileNotFoundException, IOException { // Gets the data of the selected Town's factor in an Array

        int index = (int) CshowValueCombobox();
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

        if (index == -1) {
            file = "Notes.txt";
        }

        FileInputStream fs = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < File() + 1; ++i) { // loop to run through a specific line in the data set text file. and add getindex for i
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");

            Double[] intarray = new Double[tokens.length];
            int i = 0;
            for (String str : tokens) {
                intarray[i] = Double.parseDouble(str.trim());//Exception in this line
                i++;
            }

            Xdata = Arrays.asList(intarray);

            //System.out.println(Xdata);
            // System.out.print(Arrays.toString(intarray));
            break;
        }

        return Xdata;
    }

    public List<Double> CgetArrayY() throws FileNotFoundException, IOException { // Gets the data of the selected Town's factor in an Array

        int index = (int) CshowValueCombobox();
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

        if (index == -1) {
            file = "Notes.txt";
        }

        FileInputStream fs = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < 0; ++i) { // loop to run through a specific line in the data set text file.
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");

            Double[] intarray = new Double[tokens.length];
            int i = 0;
            for (String str : tokens) {
                intarray[i] = Double.parseDouble(str.trim());//Exception in this line
                i++;
            }

            Ydata = Arrays.asList(intarray);

            //System.out.println(Ydata);
            // System.out.print(Arrays.toString(intarray));
            break;
        }

        return Ydata;
    }

    public double meany(double y) throws IOException { //mean of y

        FileInputStream fs = new FileInputStream(FileSelector());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < 0; ++i) { // loop to run through a specific line in the data set text file.
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");

            Double[] intarray = new Double[tokens.length];
            int i = 0;
            for (String str : tokens) {
                intarray[i] = Double.parseDouble(str.trim());//Exception in this line
                i++;
            }

            List<Double> list = Arrays.asList(intarray);
            sum = list.stream().mapToDouble(p -> p).sum();
            meanY = sum / intarray.length;
            // System.out.println(meanY);

            break;

        }
        return meanY;
    }

    public double CshowValueCombobox() throws FileNotFoundException, IOException { // gets the value of the selected index from file
        FileInputStream fs = new FileInputStream("IndexC.txt"); // reads the line that is the choosen parameter.
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < 0; ++i) { // loop to run through a specific line in the data set text file.
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
            Cvalue = intarrayX[0];

            break;
        }

        return Cvalue;
    }

    public double meanx(double x) throws IOException {  //mean of x(parameters)

        FileInputStream fs = new FileInputStream(FileSelector());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < File() + 1; ++i) { // loop to run through a specific line in the data set text file.
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");

            Double[] intarray = new Double[tokens.length];
            int i = 0;
            for (String str : tokens) {
                intarray[i] = Double.parseDouble(str.trim());//Exception in this line
                i++;
            }

            List<Double> list = Arrays.asList(intarray);
            sum = list.stream().mapToDouble(p -> p).sum();
            meanX = sum / intarray.length;
            // System.out.println(meanX);

            break;

        }
        return meanX;
    }

    public double Beta1() throws FileNotFoundException, IOException {  //Algorithm for the formula of beta1 for reg. formula

        FileInputStream fs = new FileInputStream(FileSelector()); // reads the line that is the choosen parameter.
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < File() + 1; ++i) { // loop to run through a specific line in the data set text file.
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

            FileInputStream y = new FileInputStream(FileSelector()); //reads the first line that is 'Y'
            BufferedReader yr = new BufferedReader(new InputStreamReader(y));
            for (int j = 0; j < 0; ++j) { // loop to run through the first line in the data set text file.
                yr.readLine();
            }
            String liney = null;
            while ((liney = yr.readLine()) != null) {
                // \\s+ means any number of whitespaces between tokens
                String[] tokensY = liney.split("\\s+");

                Double[] intarrayY = new Double[tokensY.length];
                int j = 0;
                for (String str : tokensY) {
                    intarrayY[j] = Double.parseDouble(str.trim());//Exception in this line
                    j++;
                }
                Sxy = Sxx = Beta1 = 0;
                //  loop for Beta1
                for (int k = 0; k < tokensX.length; k++) {
                    Sxy = Sxy + ((intarrayX[k] - meanx(meanX)) * (intarrayY[k] - meany(meanY)));
                    Sxx = Sxx + ((intarrayX[k] - meanx(meanX)) * (intarrayX[k] - meanx(meanX)));

                }
                Beta1 = Sxy / Sxx;
                //System.out.print(Beta1);
                break;
            }
            break;
        }
        return Beta1;
    }

    public double Beta0() throws IOException {  //Algorithm for the formula of beta0 for reg. formula

        Beta0 = meany(meanY) - (Beta1() * meanx(meanX));

        return Beta0;

    }

    public String R2s() throws IOException { // Gets all the coorelation cooeficients of all the factors in a file

        PrintWriter writerB = new PrintWriter("Best.txt", "UTF-8");
        writerB.flush();
        writerB.close();

        for (int b = 0; b < 7; b++) {

            FileInputStream fs = new FileInputStream("Notes.txt"); // reads the line that is the choosen parameter.
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int i = 0; i < b + 1; ++i) { // loop to run through a specific line in the data set text file.
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

                FileInputStream y = new FileInputStream("Notes.txt"); //reads the first line that is 'Y'
                BufferedReader yr = new BufferedReader(new InputStreamReader(y));
                for (int j = 0; j < 0; ++j) { // loop to run through the first line in the data set text file.
                    yr.readLine();
                }
                String liney = null;
                while ((liney = yr.readLine()) != null) {
                    // \\s+ means any number of whitespaces between tokens
                    String[] tokensY = liney.split("\\s+");

                    Double[] intarrayY = new Double[tokensY.length];
                    int j = 0;
                    for (String str : tokensY) {
                        intarrayY[j] = Double.parseDouble(str.trim());//Exception in this line
                        j++;
                    }
                    Sxy = Sxx = Syy = 0;
                    //  loop for Beta1
                    for (int k = 0; k < tokensX.length; k++) {
                        Sxy = Sxy + ((intarrayX[k] - meanx(meanX)) * (intarrayY[k] - meany(meanY)));
                        Sxx = Sxx + ((intarrayX[k] - meanx(meanX)) * (intarrayX[k] - meanx(meanX)));
                        Syy = Syy + ((intarrayY[k] - meany(meanY)) * (intarrayY[k] - meany(meanY)));

                    }
                    DecimalFormat df = new DecimalFormat("#.###");
                    df.setRoundingMode(RoundingMode.CEILING);

                    R2 = 0;
                    double numerator = Sxy * Sxy;
                    double denominator = Sxx * Syy;
                    R2 = numerator / denominator;
                    best[b] = R2;

                    String round = (df.format(R2));

                    try (BufferedWriter bw
                            = new BufferedWriter(new FileWriter("Best.txt", true))) {
                        String s;
                        s = round + " ";
                        bw.flush();
                        bw.write(s);

                    }
                    break;

                }
                break;
            }

        }

        return "";
    }

    public int BestR2() throws IOException { // gets the best coorelation cooeficient
        FileInputStream fs = new FileInputStream("Best.txt"); // reads the line that is the choosen parameter.
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < 0; ++i) { // loop to run through a specific line in the data set text file.
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
            double max = intarrayX[0];

            for (int j = 1; j < intarrayX.length; j++) {
                if (intarrayX[j] > max) {
                    max = intarrayX[j];
                }
                index = 0;
                index = Arrays.asList(intarrayX).indexOf(max);
            }
            // System.out.print(index);
            break;
        }
        return index;
    }

    public int File() throws IOException { //Determine which functionality of 
        Algorithm class2 = new Algorithm();
        index = 0;
        if (checkFile() == 2) {
            index = BestR2();
        }
        if (checkFile() == 1) {
            index = (int) class2.showValueCombobox();
        }

        if (checkFile() == 0) {
            index = (int) class2.showValueCombobox();
        }

        if (checkFile() == 3) {
            index = (int) class2.showValueCombobox();
        }

        return index;
    }

    public void DrawG() throws IOException { // main method for graphics initialization for other classes

        JFrame frame = new JFrame("Regression Graph");
        DrawGraph panel = new DrawGraph();
        frame.add(panel);
        frame.setSize(850, 850);
        frame.setVisible(true);
        frame.setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

    }

    @Override
    public void paintComponent(Graphics g) {

        try {

            super.paintComponent(g);
            Graphics2D g1 = (Graphics2D) g;
            g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (checkFile() == 1 || checkFile() == 0 || checkFile() == 2 || checkFile() == 3) {

                double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (MAX_X());
                double yScale = ((double) getHeight() - (2 * padding) - labelPadding) / (MAX_Y());

                List<Point> graphPoints = new ArrayList<>();// you were here
                try {
                    for (int i = 0; i < getArrayX().size(); i++) {
                        int x1 = (int) ((getArrayX().get(i)) * xScale + padding + labelPadding);
                        int y1 = (int) ((MAX_Y() - getArrayY().get(i)) * yScale + padding);
                        graphPoints.add(new Point(x1, y1));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DrawGraph.class.getName()).log(Level.SEVERE, null, ex);
                }

                // create x and y axes
                g1.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
                g1.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

                // draw white background
                g1.setColor(Color.WHITE);
                g1.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
                g1.setColor(Color.BLACK);

                // create hatch marks and grid lines for y axis.
                for (int i = 0; i < numberYDivisions + 1; i++) {
                    int x0 = padding + labelPadding;
                    int x1 = pointWidth + padding + labelPadding;
                    int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
                    int y1 = y0;

                    if (1 > 0) {
                        g1.setColor(gridColor);
                        g1.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                        g1.setColor(Color.BLACK);

                        String yLabel = (MAX_Y() / 10 * i) + ""; // for labels
                        FontMetrics metrics = g1.getFontMetrics();
                        int labelWidth = metrics.stringWidth(yLabel);
                        g1.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);

                    }

                    g1.drawLine(x0, y0, x1, y1);
                }

                Stroke oldStroke = g1.getStroke();
                g1.setStroke(oldStroke);
                g1.setColor(pointColor);
                for (int i = 0; i < graphPoints.size(); i++) {
                    int x = graphPoints.get(i).x - pointWidth / 2;
                    int y = graphPoints.get(i).y - pointWidth / 2;
                    int ovalW = pointWidth;
                    int ovalH = pointWidth;
                    g1.fillOval(x, y, ovalW, ovalH);
                }

                // and for x axis
                for (int i = 0; i < 11; i++) {
                    int x0 = i * (getWidth() - padding * 2 - labelPadding) / 10 + padding + labelPadding;
                    int x1 = x0;
                    int y0 = getHeight() - padding - labelPadding;
                    int y1 = y0 - pointWidth;

                    if (1 > 0) {

                        g1.setColor(gridColor);
                        g1.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                        g1.setColor(Color.BLACK);

                        String xLabel = (MAX_X() / 10 * i) + ""; // for labels
                        FontMetrics metrics = g1.getFontMetrics();
                        int labelWidth = metrics.stringWidth(xLabel);
                        g1.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    }
                    g1.drawLine(x0, y0, x1, y1);
                }

                for (int k = 0; k < 1; k++) {   //determine the lenght of the line

                    double y = (Beta1() * k) + Beta0();
                    double ya = (Beta1() * (k + Math.ceil(MaxValue()))) + Beta0();

                    double x1 = k * xScale + padding + labelPadding;
                    double y1 = (MAX_Y() - y) * yScale + padding;
                    double x2 = (k + Math.ceil(MaxValue())) * xScale + padding + labelPadding;;
                    double y2 = (MAX_Y() - ya) * yScale + padding;

                    g1.draw(new Line2D.Double(x1, y1, x2, y2));

                }

                if (checkFile() == 0) {
                    Algorithm class2 = new Algorithm();

                    g1.setColor(lineColor);

                    int x1 = (int) (class2.showValue() * xScale + padding + labelPadding) - pointWidth / 2;
                    int y1 = (int) ((MAX_Y() - ((Beta1() * class2.showValue()) + Beta0())) * yScale + padding) - pointWidth / 2;
                    int ovalW = pointWidth;
                    int ovalH = pointWidth;
                    g1.fillOval(x1, y1, ovalW, ovalH);

                    g1.fillOval(getWidth() - labelPadding - (padding * 3), getHeight() - labelPadding + (padding / 8), ovalW, ovalH);

                    g1.setFont(new Font("Garamond", Font.BOLD, 16));

                    g1.drawString("- Prediction", getWidth() - labelPadding - (padding * 2), getHeight() - labelPadding + (padding / 2));

                    //g1.fillOval(getWidth() - labelPadding - (padding * 3), getHeight() - labelPadding + (padding), ovalW, ovalH);
                    g1.setColor(black);
                    DecimalFormat df = new DecimalFormat("#.####");
                    df.setRoundingMode(RoundingMode.CEILING);
                    double value = ((Beta1() * class2.showValue()) + Beta0());
                    g1.drawString("Value =" + df.format(value), getWidth() - labelPadding - (padding * 3), getHeight() - labelPadding + (padding + padding / 2));
                }

                if (checkFile() == 2) {

                    List<Point> CgraphPoints = new ArrayList<>();
                    for (int i = 0; i < CgetArrayX().size(); i++) {
                        int x1 = (int) ((CgetArrayX().get(i)) * xScale + padding + labelPadding);
                        int y1 = (int) ((MAX_Y() - CgetArrayY().get(i)) * yScale + padding);
                        CgraphPoints.add(new Point(x1, y1));
                    }

                    g1.setColor(green);
                    for (int i = 0; i < CgraphPoints.size(); i++) {
                        int x = CgraphPoints.get(i).x - pointWidth / 2;
                        int y = CgraphPoints.get(i).y - pointWidth / 2;
                        int ovalW = pointWidth;
                        int ovalH = pointWidth;
                        g1.fillOval(x, y, ovalW, ovalH);

                    }

                    int index = (int) CshowValueCombobox();
                    String file = null;

                    if (index == 0) {
                        file = "TownA";
                    }
                    if (index == 1) {
                        file = "TownB";
                    }
                    if (index == 2) {
                        file = "TownC";
                    }

                    int ovalW = pointWidth;
                    int ovalH = pointWidth;

                    g1.setColor(green);
                    g1.fillOval(getWidth() - labelPadding - (padding * 3), getHeight() - labelPadding + (padding / 8), ovalW, ovalH);
                    g1.setColor(black);
                    g1.drawString(file, getWidth() - labelPadding - (padding * 2), getHeight() - labelPadding + (padding / 2));

                    g1.setColor(pointColor);
                    g1.fillOval(getWidth() - labelPadding - (padding * 3), getHeight() - labelPadding + (padding), ovalW, ovalH);
                    g1.setColor(black);
                    g1.drawString("Training DATA", getWidth() - labelPadding - (padding * 2), getHeight() - labelPadding + (padding + padding / 2));

                    g1.setFont(new Font("Garamond", Font.BOLD, 18));
                    g1.setColor(black);

                    g1.drawString(parameterList[BestR2()], 330, getHeight() - labelPadding + (padding / 2));// x-axis labels

                    AffineTransform at = new AffineTransform(); // y-axis labels
                    at.setToRotation(Math.toRadians(90), 80, 100);
                    g1.setTransform(at);
                    g1.drawString("Selling Prices(X 100,000 Pounds)", 190, 170);

                }

                if (checkFile() == 3) {
                    g1.setFont(new Font("Garamond", Font.BOLD, 18));
                    g1.setColor(black);

                    Algorithm class2 = new Algorithm();

                    double d = class2.showValueCombobox();
                    int i = (int) d;

                    g1.drawString(parameterList[i], 330, getHeight() - labelPadding + (padding / 2));// x-axis labels

                    AffineTransform at = new AffineTransform(); // y-axis labels
                    at.setToRotation(Math.toRadians(90), 80, 100);
                    g1.setTransform(at);

                    g1.drawString("Selling Prices(X 100,000 Pounds)", 190, 170);

                    return;
                }

                g1.setFont(new Font("Garamond", Font.BOLD, 18));
                g1.setColor(black);

                Algorithm class2 = new Algorithm();

                double d = class2.showValueCombobox();
                int i = (int) d;

                g1.drawString(parameterList[i], 330, getHeight() - labelPadding + (padding / 2));// x-axis labels

                AffineTransform at = new AffineTransform(); // y-axis labels
                at.setToRotation(Math.toRadians(90), 80, 100);
                g1.setTransform(at);

                g1.drawString("Selling Prices(X 100,000 Pounds)", 190, 170);
            }

        } catch (IOException ex) {
            Logger.getLogger(DrawGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
