package testGUIS;

import javax.swing.*;
import java.awt.*;

public class testTabs {

    public static void main(String[] args){

        // within J1
        JPanel p1 = new JPanel();
        JPanel p1Sub1 = new JPanel();

        // adding a panel within a panel for the graph
        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(Graphs.createChart());
        jPanel4.setBounds(0,0, 250, 250);
        p1Sub1.setLayout(null);
        p1Sub1.add(jPanel4);

        JPanel p1Sub2 = new JPanel();
        JPanel p1Sub3 = new JPanel();

        JTabbedPane subTabs = new JTabbedPane();
        subTabs.add("MSFT", p1Sub1);
        subTabs.add("AMZN", p1Sub2);
        subTabs.add("AAPL", p1Sub3);
        p1.setLayout(new BorderLayout());
        p1.add(subTabs);



        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        JTabbedPane majorTabs = new JTabbedPane();
        majorTabs.add("General", p1);
        majorTabs.add("Display", p2);
        majorTabs.add("About", p3);


        JFrame frame = new JFrame();
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(majorTabs);
        frame.setVisible(true);



    }
}
