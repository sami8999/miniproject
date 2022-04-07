package testGUIS;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestChart {
    public static void main(String[] args) {

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(Graphs.createChart());
        jPanel4.setBounds(0,0, 250, 250);

        JFrame frame = new JFrame();
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(jPanel4);
    }
}