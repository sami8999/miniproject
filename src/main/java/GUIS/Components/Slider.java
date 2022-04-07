package GUIS.Components;


import InvestmentTypes.Investment;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class Slider extends JSlider {

    public Slider(Investment investment){

        super(10, investment.getAttributes().getHistory().size(), 30);
        this.setOrientation(SwingConstants.VERTICAL);
        this.setPaintTrack(true);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setMajorTickSpacing(30);

        // creating labels for slider
        JLabel tenDays = new JLabel("10d");
        tenDays.setForeground(new Color(255,255,255));
        JLabel twoMonths = new JLabel("2m");
        twoMonths.setForeground(new Color(255,255,255));
        JLabel threeMonths = new JLabel("3m");
        threeMonths.setForeground(new Color(255,255,255));
        JLabel sixMonths = new JLabel("6m");
        sixMonths.setForeground(new Color(255,255,255));
        JLabel oneYear = new JLabel("1y");
        oneYear.setForeground(new Color(255,255,255));
        JLabel max = new JLabel("Max");
        max.setForeground(new Color(255,255,255));
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(10, tenDays);
        labels.put(60, twoMonths);
        labels.put(90, threeMonths);
        labels.put(180, sixMonths);
        labels.put(364, oneYear);
        labels.put(investment.getAttributes().getHistory().size(), max);
        this.setLabelTable(labels);
        this.setPaintLabels(true);
        this.setBackground(new Color(0x123456));

    }
}
