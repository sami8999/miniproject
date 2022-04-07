package testGUIS;

import types.Bond;
import types.Currency;
import types.Stock;
import types.YahooFinanceAPI;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

import java.io.IOException;
import java.util.Map;


public class terminalUI {

    public static void main(String[] args) throws IOException {

        // creating stocks
        String[] tickers = new String[] {"AAPL", "AMZN", "MSFT"};
        Map<String, yahoofinance.Stock> stocks = YahooFinanceAPI.getStocks(tickers);
        Stock appleStock = new Stock(tickers[0], stocks);
        Stock amazonStock = new Stock(tickers[1], stocks);
        Stock microsoftStock = new Stock(tickers[2], stocks);



        // creating currency pairs
        String[] currencyPairs = new String []{"EURUSD=X", "GBPUSD=X", "AUDUSD=X" };
        double [] margin = new double[]{0.05, 0.12, 0.08};
        Map<String, FxQuote> currencies = YahooFinanceAPI.getCurrencies(currencyPairs);
        Currency eurUsd = new Currency(currencyPairs[0], currencies, margin[0]);
        Currency gbpUsd = new Currency(currencyPairs[1],currencies, margin[1]);
        Currency audUsd = new Currency(currencyPairs[2],currencies, margin[2]);

        // creating bonds
        Bond treasuryBond = new Bond("US Treasury bond", 10000, 2.5,
                "semi-annual", "AAA-Investment grade", 30, 10000);

        Bond corporateBond = new Bond("Vanguard Corporate bond", 150000, 6.5,
                "annual", "A-Investment grade",5, 15984.61);

        Bond mortgageBond = new Bond("Subprime mortgage bond", 7000, 7,
                "quarterly", "CCC-Speculative", 2, 10046.17);


        // types.User newUser = new types.User("sami");

    }
}

//
// https://financequotes-api.com/
// https://www.youtube.com/watch?v=ZIjBSQc6MNA
// https://zetcode.com/java/jfreechart/

// https://futures.io/platforms-indicators/31633-java-candlestick-chart-jfreechart.html
// https://www.roseindia.net/chartgraphs/candle-stick-chart.shtml
// https://www.boraji.com/jfreechart-ohlc-chart-example
// https://stackoverflow.com/questions/12318488/adding-a-chartpanel-to-jpanel
// https://stackhowto.com/how-to-create-multiple-tabs-in-java-swing/
// https://www.javatpoint.com/jfreechart-line-chart
// https://www.roseindia.net/chartgraphs/candle-stick-chart.shtml


// use simple factory https://www.codeproject.com/Articles/1131770/Factory-Patterns-Simple-Factory-Pattern
//
//System.out.println(appleStock.getMarketPrice());
//        newUser.buyInvestment(appleStock, 10);
//
//        System.out.println(appleStock.getMarketPrice());
//        newUser.sellInvestment(appleStock, 9);
//
//        System.out.println(appleStock.getMarketPrice());
//
//        System.out.println(newUser.getCashBalance());
//        System.out.println(newUser.calculateTotalPortfolioValue());
//        System.out.println(newUser.calculateTotalReturn());
//String[] bonds = new String [] {"US Treasury bond", "Vanguard Corporate bond", "Subprime mortgage bond" };
//    double [] faceValues = new double []{10000, 150000, 7000};
//    double [] couponRates = new double[]{2.5, 6.5, 7};
//    String [] couponDates = new String[]{"semi-annual", "annual", "quarterly"};
//    String [] bondRatings = new String[]{"AAA-Investment grade", "A-Investment grade", "CCC-Speculative"};
//    int [] yearsToMaturities = new int[]{30, 5, 2};
//    double [] marketPrices = new double[]{10000, 15984.61, 10046.17};