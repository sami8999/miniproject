package testGUIS;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import types.YahooFinanceAPI;
import yahoofinance.Stock;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class Graphs {

    public static ChartPanel createChart(){

        XYSeries series = new XYSeries("asdf");
        for (int i = 0; i < 100; i++)
            series.add(i, Math.random());
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(null, null, null, dataset, PlotOrientation.HORIZONTAL, true, true, true);
        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setDomainZoomable(true);
        return chartpanel;
    }
}

//    public static void main(String[] args) throws IOException {
//
//        String[] tickers = new String[] {"AAPL", "AMZN", "MSFT"};
//        Map<String, Stock> stocks = YahooFinanceAPI.getStocks(tickers);
//        types.Stock appleStock = new types.Stock("AAPL", stocks);
//
//        final JFreeChart chart = ChartFactory.createCandlestickChart(
//                "Candlestick Demo", "Time", "Price", appleStock.getData(), false);
//
//        ChartPanel chartpanel = new ChartPanel(chart);
//
//
//        JPanel jPanel4 = new JPanel();
//        jPanel4.setLayout(new BorderLayout());
//        jPanel4.add(chartpanel);
//        jPanel4.setBounds(0,0, 250, 250);
//
//        JFrame frame = new JFrame();
//        frame.setSize(900,900);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//        frame.setVisible(true);
//        frame.add(jPanel4);
//    }