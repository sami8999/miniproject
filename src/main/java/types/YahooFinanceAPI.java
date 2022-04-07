package types;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.fx.FxQuote;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class YahooFinanceAPI {

    public static final int yearsBack = 2;
    public static final int daysBack = 0;

    public static Map<String, Stock> getStocks(String[] tickers) throws IOException {
        return YahooFinance.get(tickers);

    }

    public static Map<String, FxQuote> getCurrencies(String [] currencyPairs) throws IOException{

        return YahooFinance.getFx(currencyPairs);
    }

    public static List<HistoricalQuote> getHistory(Map<String, Stock> stocks, String ticker) throws IOException {

        Calendar from = Calendar.getInstance();
        from.add(Calendar.YEAR, -yearsBack);

        Calendar to = Calendar.getInstance();
        to.add(Calendar.DAY_OF_MONTH, -daysBack);

        Stock stock = stocks.get(ticker);
        List<HistoricalQuote> history = stock.getHistory(from, to, Interval.DAILY);

        return history;
    }

}



