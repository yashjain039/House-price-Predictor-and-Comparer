/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp1555.coursework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */
public class Algorithm { // All the Calculations required.

    private static double sum = 0;
    private static double meanX = 0;
    private static double meanY = 0;
    private static double Sxy = 0;
    private static double Sxx = 0;
    private static double Syy = 0;
    private static double Beta1 = 0;
    private static double Beta0 = 0;
    private double squaresum;
    private double deviation;
    private double R2;
    private double value;
    private double Cvalue;
    private int checker;
    private int N;
    private Double[] best = new Double[7];
    private static List<Double> Xdata;
    private static List<Double> Ydata;
    private int index;

    DrawGraph class1 = new DrawGraph();

    public double showValue() throws FileNotFoundException, IOException { // gets the value of the inputed no. from file(for PREDICTOR)
        FileInputStream fs = new FileInputStream("valueCapturer.txt"); // reads the line that is the choosen parameter.
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
            value = intarrayX[0];
            break;
        }
        return value;
    }

    public double showValueCombobox() throws FileNotFoundException, IOException { // gets the value of the selected index from file
        FileInputStream fs = new FileInputStream("Index.txt"); // reads the line that is the choosen parameter.
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

    public int N() throws IOException {// No. of data in a particular line

        FileInputStream fs = new FileInputStream(class1.FileSelector());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < showValueCombobox() + 1; ++i) { // loop to run through a specific line in the data set text file.
            br.readLine();
        }
        String line = null;
        while ((line = br.readLine()) != null) {
            // \\s+ means any number of whitespaces between tokens
            String[] tokens = line.split("\\s+");
            N = 0;
            N = tokens.length;
            break;
        }
        return N;
    }

    public double StandardDeviationX() throws FileNotFoundException, IOException {//StandardDeviation of the selected X factor

        FileInputStream fs = new FileInputStream(class1.FileSelector()); // reads the line that is the choosen parameter.
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < showValueCombobox() + 1; ++i) { // loop to run through a specific line in the data set text file.
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

            int n = intarrayX.length;

            for (int j = 0; j < n; j++) {
                // do summation
                squaresum = squaresum + (intarrayX[j] * intarrayX[j]);
                sum = sum + intarrayX[j];
            }
            // calculate deviation
            double numerator = ((double) n * (squaresum)) - (sum * sum);

            double root = ((double) n * Math.sqrt(Math.abs(numerator)));

            double comp = (double) n * ((double) n - 1);

            deviation = root / comp;

            break;

        }
        // System.out.print(deviation);
        return deviation;
    }

    public double StandardDeviationY() throws FileNotFoundException, IOException {//StandardDeviation of the selected Y factor

        FileInputStream fs = new FileInputStream(class1.FileSelector()); // reads the line that is the choosen parameter.
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

            int n = intarrayX.length;

            for (int k = 0; k < n; k++) {

                for (int j = 0; j < n; j++) {
                    // do summation
                    squaresum = squaresum + (intarrayX[j] * intarrayX[j]);
                    sum = sum + intarrayX[j];
                }
                // calculate deviation
                double numerator = ((double) n * (squaresum)) - (sum * sum);

                double root = ((double) n * Math.sqrt(Math.abs(numerator)));

                double comp = (double) n * ((double) n - 1);

                deviation = root / comp;

                break;

            }
            break;
        }
        // System.out.print(deviation);
        return deviation;
    }

    public double VarianceX() throws IOException {// Variance of selected X factor
        double V = StandardDeviationX() * StandardDeviationX();

        return V;
    }

    public double VarianceY() throws IOException { // Variance of selected Yfactor
        double V = StandardDeviationY() * StandardDeviationY();

        return V;
    }

    public double R2() throws FileNotFoundException, IOException { //Calculating corelation cooeficient of the selcted factor 

        DrawGraph class1 = new DrawGraph();

        FileInputStream fs = new FileInputStream(class1.FileSelector()); // reads the line that is the choosen parameter.
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < showValueCombobox() + 1; ++i) { // loop to run through a specific line in the data set text file.
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

            FileInputStream y = new FileInputStream(class1.FileSelector()); //reads the first line that is 'Y'
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
                    Sxy = Sxy + ((intarrayX[k] - class1.meanx(meanX)) * (intarrayY[k] - class1.meany(meanY)));
                    Sxx = Sxx + ((intarrayX[k] - class1.meanx(meanX)) * (intarrayX[k] - class1.meanx(meanX)));
                    Syy = Syy + ((intarrayY[k] - class1.meany(meanY)) * (intarrayY[k] - class1.meany(meanY)));

                }
                R2 = 0;
                double numerator = Sxy * Sxy;
                double denominator = Sxx * Syy;
                R2 = numerator / denominator;
                break;
            }
            break;
        }
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(df.format(R2));
    }

    public double R() throws IOException {

        double R = Math.sqrt(R2());

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(df.format(R));

    }

    public double SummationX() throws FileNotFoundException, IOException { // Sum of all the values of the selected X factor
        FileInputStream fs = new FileInputStream(class1.FileSelector());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < class1.File() + 1; ++i) { // loop to run through a specific line in the data set text file.
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
            // System.out.println(meanX);

            break;

        }
        return sum;

    }

    public double SummationY() throws FileNotFoundException, IOException {
        FileInputStream fs = new FileInputStream(class1.FileSelector());
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

            break;

        }
        return sum;

    }

    public double SummationXY() throws FileNotFoundException, IOException {
        FileInputStream fs = new FileInputStream(class1.FileSelector());
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for (int i = 0; i < class1.File() + 1; ++i) { // loop to run through a specific line in the data set text file.
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

            FileInputStream y = new FileInputStream(class1.FileSelector()); //reads the first line that is 'Y'
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
                sum = 0;
                for (int a = 0; a < intarray.length; a++) {
                    sum = sum + (intarray[a] * intarrayY[a]);
                }
            }

            break;

        }
        return sum;

    }

    public double SummationX2() throws FileNotFoundException, IOException {

        double sum = SummationX() * SummationX();
        return sum;

    }

    public double SummationY2() throws FileNotFoundException, IOException {

        double sum = SummationY() * SummationY();
        return sum;

    }

}
