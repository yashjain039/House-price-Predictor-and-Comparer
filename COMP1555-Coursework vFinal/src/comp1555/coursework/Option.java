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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */
public class Option extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel label = new JLabel("<html><u>Choose Your Preferred Way </u></html>");
    JButton back = new JButton(new ImageIcon("icons/Left.png"));
    JButton help = new JButton(new ImageIcon("icons/icons8_help_filled_5_pow8U.png"));
    JRadioButton own = new JRadioButton("Use your own Data", false);
    JRadioButton system = new JRadioButton("Use System's Values", false);
    ButtonGroup group = new ButtonGroup();

    JButton ok = new JButton("OK");

    public Option() {

        setSize(600, 300);
       // setTitle("Choose One");
        Font fnt = new Font("Lucida Bright", Font.BOLD, 20);
        Font fnt1 = new Font("Lucida Bright", Font.PLAIN, 15);
        Font fnt2 = new Font("Lucida Bright", Font.PLAIN, 20);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        panel.setPreferredSize(new Dimension(1500, 100));
        label.setFont(fnt);
        panel.add(label);
        add(panel);

        panel1.setPreferredSize(new Dimension(1400, 100));
        panel1.add(own);
        panel1.add(system);
        group.add(own);
        group.add(system);
        add(panel1);

        panel2.add(ok);
        ok.setActionCommand("OK");
        ok.addActionListener(this);
        add(panel2);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("OK".equals(e.getActionCommand())) {

            if (system.isSelected()) {

                this.dispose();

                PrintWriter writerI;
                try {
                    writerI = new PrintWriter("Checker.txt", "UTF-8");
                    writerI.flush();
                    writerI.print("Main");
                    writerI.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    COMP1555Coursework cw = new COMP1555Coursework();
                } catch (IOException ex) {
                    Logger.getLogger(Option.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (own.isSelected()) {

                this.dispose();

                PrintWriter writerI;
                try {
                    writerI = new PrintWriter("Checker.txt", "UTF-8");
                    writerI.flush();
                    writerI.print("UserData");
                    writerI.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(COMP1555Coursework.class.getName()).log(Level.SEVERE, null, ex);
                }

                InputData cw = new InputData();

            }
        }

    }

}
