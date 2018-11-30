/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp1555.coursework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */
public class COMP1555Coursework extends JFrame implements ActionListener, KeyListener {

    String[] parameterList = {"No. of Bathrooms", "Area of the Site(1000's sq. feet)", "Size of living space(1000's sq. feet)", "No. of Garages", "No. of Rooms", "No. of Bedrooms", "Age of Property(Years)"};

    JButton showGrph = new JButton("Show Graph");
    JButton showTbl = new JButton("Show Table");

    JComboBox parameters = new JComboBox(parameterList);

    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JLabel label = new JLabel("<html><u>Relationship Between The Selling Price And Different Parameters </u></html>");
    JLabel label1 = new JLabel("Select Parameter: ");

    DrawGraph cw = new DrawGraph();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        //COMP1555Coursework cw = new COMP1555Coursework();
        Option cw = new Option();

        // TODO code application logic here
    }

    public COMP1555Coursework() throws IOException {
        setSize(800, 400);
        setTitle("Housing Prices");
        Font fnt = new Font("Lucida Bright", Font.BOLD, 20);
        Font fnt1 = new Font("Lucida Bright", Font.PLAIN, 15);
        Font fnt2 = new Font("Lucida Bright", Font.PLAIN, 20);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //panel.setBackground(Color.blue);
        panel.setPreferredSize(new Dimension(1500, 50));
        label.setFont(fnt);
        panel.add(label);
        add(panel);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        // Setting up the ButtonBar
        JButton Home = new JButton("Home");
        Home.setActionCommand("HOME");
        Home.addActionListener(this);
        toolBar.add(Home);
        toolBar.addSeparator();
        JButton P = new JButton("Predictor");
        P.setActionCommand("Predict");
        P.addActionListener(this);
        toolBar.add(P);
        toolBar.addSeparator();
        JButton C = new JButton("Compare");
        C.setActionCommand("Compare");
        C.addActionListener(this);
        toolBar.add(C);
        toolBar.addSeparator();
        JButton H = new JButton("Help(?)");
        H.setActionCommand("Help");
        H.addActionListener(this);
        toolBar.add(H);
        toolBar.addSeparator();
        toolBar.add(Box.createHorizontalGlue());
        P.setFont(fnt1);
        H.setFont(fnt1);
        C.setFont(fnt1);
        Home.setFont(fnt1);
        // panel2.setBackground(Color.blue);
        panel2.setPreferredSize(new Dimension(1400, 110));
        panel2.add(toolBar, BorderLayout.WEST);
        add(panel2);

        label1.setFont(fnt2);
        panel1.setPreferredSize(new Dimension(1400, 90));
        panel1.add(label1);
        parameters.setSelectedItem(null);
        panel1.add(parameters);

        add(panel1);

        panel3.add(showGrph, BorderLayout.EAST);
        showGrph.setActionCommand("ShowGraph");
        showGrph.addActionListener(this);
        panel3.add(showTbl);
        showTbl.setActionCommand("ShowTable");
        showTbl.addActionListener(this);
        add(panel3);

        cw.R2s();

        setVisible(true);
    }

    public void file(String fn) throws FileNotFoundException, UnsupportedEncodingException, IOException { //renew all the files when the program starts

        PrintWriter writer = new PrintWriter("Index.txt", "UTF-8");

        writer.flush();
        writer.println(Integer.toString(parameters.getSelectedIndex()));
        writer.close();

        PrintWriter writerI = new PrintWriter("valueCapturer.txt", "UTF-8");
        writerI.flush();
        writerI.close();

        PrintWriter writerIC = new PrintWriter("IndexC.txt", "UTF-8");
        writerIC.flush();
        writerIC.print("-1");
        writerIC.close();

        // System.out.println(parameters.getSelectedIndex());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if ("Predict".equals(ae.getActionCommand())) {

            super.dispose();

            PrintWriter writerI;
            try {
                writerI = new PrintWriter("Checker.txt", "UTF-8");
                writerI.flush();
                writerI.print("Predict");
                writerI.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }

            Predictor cw = new Predictor();

        }

        if ("HOME".equals(ae.getActionCommand())) {
            this.dispose();
            Option cw = new Option();
        }

        if ("Compare".equals(ae.getActionCommand())) {

            super.dispose();

            PrintWriter writerI;
            PrintWriter writerC;
            try {
                writerI = new PrintWriter("Checker.txt", "UTF-8");
                writerC = new PrintWriter("IndexC.txt", "UTF-8");
                writerI.flush();
                writerC.flush();
                writerI.print("Compare");
                writerI.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }
            CompareT cw = new CompareT();

        }

        if ("Help".equals(ae.getActionCommand())) {

            try {
                Help cw = new Help();
            } catch (IOException ex) {
                Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("ShowTable".equals(ae.getActionCommand())) {
            if (parameters.getSelectedIndex() != -1) {

                try {
                    file(Integer.toString(parameters.getSelectedIndex()));
                    Tables cw = new Tables();
                } catch (IOException ex) {
                    Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Select Something!",
                        "INPUT NOT FOUND",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if ("ShowGraph".equals(ae.getActionCommand())) {
            if (parameters.getSelectedIndex() != -1) {

                try {

                    file(Integer.toString(parameters.getSelectedIndex()));
                    cw.DrawG();

                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Select Something!",
                        "INPUT NOT FOUND",
                        JOptionPane.WARNING_MESSAGE);
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed not coded yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased not coded yet.");
    }

}
