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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */
public class CompareT extends JFrame implements ActionListener {

    String[] parameterList = {"TownA", "TownB", "TownC"};

    DrawGraph cw = new DrawGraph();

    JButton showGrph = new JButton("Show Graph");
    JButton showTbl = new JButton("Show Table");

    JComboBox parameters = new JComboBox(parameterList);

    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JLabel label = new JLabel("<html><u>Compare Other Towns </u></html>");
    JLabel label1 = new JLabel("Select Town: ");
    JButton back = new JButton(new ImageIcon("icons/Left.png"));
    JButton help = new JButton(new ImageIcon("icons/icons8_help_filled_5_pow8U.png"));
    JButton home = new JButton(new ImageIcon("icons/Home.png"));

    public CompareT() {
        setSize(800, 400);
        setTitle("Compare");
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

        panel3.setPreferredSize(new Dimension(1400, 90));// Panel for the back button
        //panel1.setBackground(Color.red);
        panel3.add(home);
        home.setActionCommand("Home");
        home.addActionListener(this);
        panel3.add(back);
        back.setActionCommand("Back");
        back.addActionListener(this);
        panel3.add(help);
        help.setActionCommand("Help");
        help.addActionListener(this);
        add(panel3);

        label1.setFont(fnt2);
        panel1.setPreferredSize(new Dimension(1400, 120));
        panel1.add(label1);
        parameters.setSelectedItem(null);
        panel1.add(parameters);
        add(panel1);

        panel2.add(showGrph, BorderLayout.EAST);
        showGrph.setActionCommand("ShowGraph");
        showGrph.addActionListener(this);
        panel2.add(showTbl);
        showTbl.setActionCommand("ShowTable");
        showTbl.addActionListener(this);
        add(panel2);

        setVisible(true);
    }

    public void file(String fn) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        PrintWriter writer = new PrintWriter("IndexC.txt", "UTF-8");

        writer.flush();
        writer.println(Integer.toString(parameters.getSelectedIndex()));
        writer.close();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("ShowGraph".equals(e.getActionCommand())) {
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

        if ("ShowTable".equals(e.getActionCommand())) {

            try {
                file(Integer.toString(parameters.getSelectedIndex()));
                ForecastTable cw = new ForecastTable();
            } catch (IOException ex) {
                Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("Home".equals(e.getActionCommand())) {
            this.dispose();
            Option cw = new Option();
        }

        if ("Help".equals(e.getActionCommand())) {

            try {
                Help cw = new Help();
            } catch (IOException ex) {
                Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }       
        
        if ("Back".equals(e.getActionCommand())) {

            try {
                this.dispose();
                
                PrintWriter writerI;
                writerI = new PrintWriter("Checker.txt", "UTF-8");
                writerI.flush();
                writerI.print("Main");
                writerI.close();

                COMP1555Coursework cw = new COMP1555Coursework();
                // cw.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
