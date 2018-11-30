/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp1555.coursework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @authors as6501h, gs9043r, lp7619j, yj3908i
 */

public class Help extends JFrame implements ActionListener {

    JPanel pnlMain = new JPanel();
    JPanel pnlSth = new JPanel();

    public Help() throws FileNotFoundException, IOException {

        setTitle("Help & Support");
        setSize(410, 550);
        setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width - this.getSize().width, dim.height / 3 - this.getSize().height / 2);

//      Main Panel START

        JTextArea txtArea = new JTextArea();
        JScrollPane scrPane = new JScrollPane(txtArea);
        FileReader helpFile = new FileReader("Help.txt");
        txtArea.read(helpFile, null);   // Modified but still working.
        txtArea.setLineWrap(true);      // Limits the line horizontally.
//      txtArea.setWrapStyleWord(true); // Limits the line vertically.
        txtArea.setEditable(false);     // Unables user to modify text.

        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.add(scrPane);

        add(pnlMain, BorderLayout.CENTER);

//      Main Panel END 

//      SOUTH Panel START

        JLabel label = new JLabel("Was this helpful?");
        JButton btnYes = new JButton("Yes");
        btnYes.setActionCommand("Yes");
        btnYes.addActionListener(this);
        JButton btnNo = new JButton("No");
        btnNo.setActionCommand("No");
        btnNo.addActionListener(this);

        pnlSth.setBackground(Color.lightGray);
        pnlSth.add(label);
        pnlSth.add(btnYes);
        pnlSth.add(btnNo);

        add(pnlSth, BorderLayout.SOUTH);

//      SOUTH Panel END

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if ("Yes".equals(ae.getActionCommand())) {
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Please give us feedback at help@example.co.uk",
                    "Was not helpful?",
                    JOptionPane.WARNING_MESSAGE);
            this.dispose();
        }
    }
}
