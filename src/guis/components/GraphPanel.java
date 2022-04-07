package guis.components;

import investmentAttributes.HistoricalData;
import investmentTypes.Investment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultHighLowDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class GraphPanel extends JPanel {

    JFreeChart chart;
    public GraphPanel(Investment investment) {

        this.setLayout(new BorderLayout());

        chart = ChartFactory.createCandlestickChart(investment.getAttributes().getTicker(), "Time", "Price",
                getGraphData(investment, 30), false);
        // sets colors
        chart.getPlot().setBackgroundPaint(new Color(0x123456));
        chart.setBackgroundPaint(new Color(0x123456));
        chart.getTitle().setPaint(new Color(255,255,255));
        XYPlot plot = chart.getXYPlot();
        plot.getDomainAxis().setTickLabelPaint(new Color(255,255,255));
        plot.getDomainAxis().setLabelPaint(new Color(255,255,255));
        plot.getRangeAxis().setTickLabelPaint(new Color(255,255,255));
        plot.getRangeAxis().setLabelPaint(new Color(255,255,255));

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
        yAxis.setAutoRange(true);

        ChartPanel chartPanel = new ChartPanel(chart);
        this.add(chartPanel);
    }
    public XYPlot getPlot(){
        return chart.getXYPlot();

    }
    public DefaultHighLowDataset getGraphData(Investment investment, int daysBack){

        List<HistoricalData> history= investment.getAttributes().getHistory();

        int size = investment.getAttributes().getHistory().size();
        int sizeFromDayBack = size-daysBack;

        Date[] date = new Date[daysBack];
        double[] high = new double[daysBack];
        double[] low = new double[daysBack];
        double[] open = new double[daysBack];
        double[] close = new double[daysBack];
        double[] volume = new double[daysBack];

        for (int i=0; i<daysBack; i++) {
            date[i] = history.get(sizeFromDayBack + i).getDate().getTime();
            high[i] = history.get(sizeFromDayBack + i).getHigh().doubleValue();
            low[i] = history.get(sizeFromDayBack + i).getLow().doubleValue();
            open[i] = history.get(sizeFromDayBack + i).getOpen().doubleValue();
            close[i] = history.get(sizeFromDayBack + i).getClose().doubleValue();
            volume[i] = history.get(sizeFromDayBack + i).getVolume().doubleValue();
        }
        return new DefaultHighLowDataset("", date, high, low, open, close, volume);

    }

}

