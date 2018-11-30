package comp1555.coursework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */
public class Predictor extends JFrame implements ActionListener, KeyListener {

    String[] parameterLists = {"No. of Bathrooms", "Area of the Site(1000's sq. feet)", "Size of living space(1000's sq. feet)", "No. of Garages", "No. of Rooms", "No. of Bedrooms", "Age of Property(Years)"};

    private static double mean = 0;
    private double p;

    private static double sum = 0;

    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JComboBox parameterss = new JComboBox(parameterLists);
    JButton showGrph = new JButton("Show Graph");
    //JButton showTbl = new JButton("Show Tables");
    JLabel label = new JLabel("Price Predictor");
    JLabel label1 = new JLabel("INPUT VALUE:");
    JLabel label2 = new JLabel(": Choose A Parameter");
    JTextField input = new JTextField();
    JButton back = new JButton(new ImageIcon("icons/Left.png"));
    JButton home = new JButton(new ImageIcon("icons/Home.png"));
    JButton help = new JButton(new ImageIcon("icons/icons8_help_filled_5_pow8U.png"));
    //new ImageIcon("icons/Left.png")
    DrawGraph cw = new DrawGraph();

    public Predictor() {
        setSize(700, 400);
        setTitle("Predictor");
        Font fnt = new Font("Lucida Bright", Font.BOLD, 20);
        Font fnt1 = new Font("Lucida Bright", Font.PLAIN, 15);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        panel1.setPreferredSize(new Dimension(1400, 50));// Panel for the back button
        //panel1.setBackground(Color.red);
        panel1.add(home);
        home.setActionCommand("Home");
        home.addActionListener(this);
        panel1.add(back);
        back.setActionCommand("Back");
        back.addActionListener(this);
        panel1.add(help);
        help.setActionCommand("Help");
        help.addActionListener(this);
        add(panel1);

        // panel.setBackground(Color.blue); // Panel for the title
        panel.setPreferredSize(new Dimension(1400, 70));
        label.setFont(fnt);
        panel.add(label);
        add(panel);

        panel3.setPreferredSize(new Dimension(1400, 70)); // Panel for the combo box
        //panel2.setBackground(Color.blue);
        panel3.add(parameterss);
        parameterss.setSelectedItem(null);
        panel3.add(label2);
        label2.setFont(fnt1);

        add(panel3);

        // panel2.setBackground(Color.red);// Panel for the text area
        panel2.setPreferredSize(new Dimension(1400, 80));
        input.setPreferredSize(new Dimension(150, 33));
        input.setFont(fnt);
        
        input.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE || c == KeyEvent.VK_PERIOD ||  c == KeyEvent.VK_TAB))) {
                    e.consume();
                }
            }
        });
        panel2.add(label1);
        panel2.add(input);

        label1.setFont(fnt1);

        add(panel2);

        panel4.add(showGrph);
        //panel4.add(showTbl);
        showGrph.setActionCommand("ShowGraph");
        showGrph.addActionListener(this);
        add(panel4);

        setVisible(true);
    }

    /*    public void file(String fn) throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter("valueCapturer.txt", "UTF-8");
        //writer.flush();
        writer.println(input.getText());
        writer.close();

        System.out.println(input.getText());

    }*/
    public void file(String fn) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        PrintWriter writer = new PrintWriter("Index.txt", "UTF-8");

        writer.flush();
        writer.println(Integer.toString(parameterss.getSelectedIndex()));
        writer.close();

        PrintWriter writerI = new PrintWriter("valueCapturer.txt", "UTF-8");
        writerI.flush();
        writerI.println(input.getText());
        writerI.close();

        if (input.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Input Something!",
                    "INPUT NOT FOUND",
                    JOptionPane.WARNING_MESSAGE);
        } else {

            cw.DrawG();
        }
        // System.out.println(parameters.getSelectedIndex());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if ("Back".equals(ae.getActionCommand())) {

            try {
                this.dispose();
                
                PrintWriter writerI;
                writerI = new PrintWriter ("Checker.txt", "UTF-8");
                writerI.flush();
                writerI.print("Main");
                writerI.close();
                
                
                 PrintWriter writerP;
                writerP = new PrintWriter ("Best.txt", "UTF-8");
                writerP.flush();
                writerP.close();
                
                COMP1555Coursework cw = new COMP1555Coursework();
                // cw.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        if ("Help".equals(ae.getActionCommand())) {

            try {
                Help cw = new Help();
            } catch (IOException ex) {
                Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
         if ("Home".equals(ae.getActionCommand())){
            this.dispose();
            Option cw = new Option();
        }

        if ("ShowGraph".equals(ae.getActionCommand())) {
            try {
                file(input.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Predictor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped not coded yet.");
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
