/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp1555.coursework;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
import javax.swing.JTextField;

/**
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */
public class InputData extends JFrame implements ActionListener {

    String[] parameterLists = {"No. of Bathrooms", "Area of the Site(1000's sq. feet)", "Size of living space(1000's sq. feet)", "No. of Garages", "No. of Rooms", "No. of Bedrooms", "Age of Property(Years)"};

    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JComboBox parameterss = new JComboBox(parameterLists);
    JButton showGrph = new JButton("Show Graphs");
    JButton showTbl = new JButton("Show Tables");
    JLabel label = new JLabel("Create Data");
    JLabel label1 = new JLabel("INPUT Values:");
    JLabel label2 = new JLabel(": Choose A Parameter");
    JLabel label3 = new JLabel("INPUT Price: ");
    JTextField input = new JTextField();
    JTextField input2 = new JTextField();
    JButton back = new JButton(new ImageIcon("icons/Left.png"));
    JButton help = new JButton(new ImageIcon("icons/icons8_help_filled_5_pow8U.png"));

    DrawGraph cw = new DrawGraph();

    public InputData() {

        setSize(700, 400);
        setTitle("Input Data");
        Font fnt = new Font("Lucida Bright", Font.BOLD, 20);
        Font fnt1 = new Font("Lucida Bright", Font.PLAIN, 15);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        panel1.setPreferredSize(new Dimension(1400, 50));// Panel for the back button
        //panel1.setBackground(Color.red);
        panel1.add(back);
        back.setActionCommand("Back");
        back.addActionListener(this);
        panel1.add(help);
        help.setActionCommand("Help");
        help.addActionListener(this);
        add(panel1);

        // panel.setBackground(Color.blue); // Panel for the title
        panel.setPreferredSize(new Dimension(1400, 60));
        label.setFont(fnt);
        panel.add(label);
        add(panel);

        panel3.setPreferredSize(new Dimension(1400, 70)); // Panel for the combo box
        //panel2.setBackground(Color.blue);
        panel3.add(parameterss);
        panel3.add(label2);
        label2.setFont(fnt1);

        add(panel3);

        // panel2.setBackground(Color.red);// Panel for the text area
        panel2.setPreferredSize(new Dimension(1400, 40));
        input.setPreferredSize(new Dimension(350, 33));
        input.setFont(fnt1);

        input.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE || c == KeyEvent.VK_PERIOD || c == KeyEvent.VK_COMMA || c == KeyEvent.VK_TAB))) {
                    e.consume();
                }
            }
        });

        panel2.add(label1);
        panel2.add(input);

        label1.setFont(fnt1);
        add(panel2);

        // panel2.setBackground(Color.red);// Panel for the text area
        panel5.setPreferredSize(new Dimension(1400, 60));
        input2.setPreferredSize(new Dimension(350, 33));
        input2.setFont(fnt1);
        input2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE || c == KeyEvent.VK_PERIOD || c == KeyEvent.VK_COMMA || c == KeyEvent.VK_TAB))) {
                    e.consume();
                }
            }
        });
        panel5.add(label3);
        panel5.add(input2);

        label3.setFont(fnt1);

        add(panel5);

        panel4.add(showGrph);
        panel4.add(showTbl);
        showGrph.setActionCommand("ShowGraph");
        showGrph.addActionListener(this);
        showTbl.setActionCommand("ShowTable");
        showTbl.addActionListener(this);
        add(panel4);

        setVisible(true);

    }

    public void file(String fn) throws IOException {
        PrintWriter writerU = new PrintWriter("UserData.txt", "UTF-8");
        writerU.flush();
        writerU.close();

        PrintWriter writer = new PrintWriter("Index.txt", "UTF-8");

        writer.flush();
        writer.println(Integer.toString(parameterss.getSelectedIndex()));
        writer.close();

        BufferedWriter br = new BufferedWriter(new FileWriter("UserData.txt"));

        for (int i = 0; i < 0; i++) {
            br.newLine();
        }

        String line = input2.getText().replace(",", " ");

        br.write(line);

        for (int i = 0; i < parameterss.getSelectedIndex() + 1; i++) {
            br.newLine();
        }

        String line1 = input.getText().replace(",", " ");
        br.write(line1);

        br.close();

    }

    @Override
    public void actionPerformed(ActionEvent e
    ) {

        if ("Back".equals(e.getActionCommand())) {
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

        if ("ShowTable".equals(e.getActionCommand())) {
            if (input.getText().equals("") || input2.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Input missing!",
                        "INPUT NOT FOUND",
                        JOptionPane.WARNING_MESSAGE);

            } else {

                try {
                    file(Integer.toString(parameterss.getSelectedIndex()));
                    Tables cw = new Tables();

                    DrawGraph c = new DrawGraph();
                    
                    /*if (c.Beta1() == 0 & c.Beta0() == 0) {
                        JOptionPane.showMessageDialog(null,
                                "Input Something!",
                                "INPUT NOT FOUND",
                                JOptionPane.WARNING_MESSAGE);
                    }*/
                } catch (IOException ex) {
                    Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "All Input Values cannot be the same.",
                            "ERROR!",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if ("ShowGraph".equals(e.getActionCommand())) {
            if (input.getText().equals("") || input2.getText().equals("")) {

                JOptionPane.showMessageDialog(null,
                       "All Input Values cannot be the same.",
                            "ERROR!",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                try {

                    file(Integer.toString(parameterss.getSelectedIndex()));
                    cw.DrawG();

                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(InputData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    public void inputkeyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE || c == KeyEvent.VK_PERIOD || c == KeyEvent.VK_COMMA || c == KeyEvent.VK_TAB))) {
            e.consume();
        }
    }

}
