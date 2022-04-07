package testGUIS;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.DefaultHighLowDataset;

import java.util.Calendar;
import java.util.Date;

public class lineChart extends ApplicationFrame {

    private DefaultHighLowDataset createDataset() {

        int service = 40;

        Date[] date = new Date[service];
        double[] high = new double[service];
        double[] low = new double[service];
        double[] open = new double[service];
        double[] close = new double[service];
        double[] volume = new double[service];



        Calendar calendar = Calendar.getInstance();
        calendar.set(2008, 5, 1);

        for (int i = 0; i < service; i++) {
            date[i] = createData(2008, 8, i + 1);
            high[i] = 30 + Math.round(10) + Math.random() * 20.0;
            low[i] = 30 + Math.round(10) + Math.random() * 20.0;
            open[i] = 10 + Math.round(10) + Math.random() * 20.0;
            close[i] = 10 + Math.round(10) + Math.random() * 20.0;
            volume[i] = 10.0 + Math.random() * 20.0;
        }

        DefaultHighLowDataset data = new DefaultHighLowDataset(
                "", date, high, low, open, close, volume);
        return data;
    }

    private Date createData(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }

    private JFreeChart createChart(final DefaultHighLowDataset dataset) {
        final JFreeChart chart = ChartFactory.createCandlestickChart(
                "Candlestick Demo", "Time", "Price", dataset, false);
        return chart;
    }

    public lineChart(String title) {
        super(title);
        final DefaultHighLowDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
        setContentPane(chartPanel);
    }

    public static void main(String args[]) {
        lineChart chart = new lineChart("Candle Stick Chart");
        chart.pack();
        chart.setVisible(true);
    }
}

