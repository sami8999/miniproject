import guis.Frames.LoginFrame;
import investmentTypes.Bond;
import investmentTypes.CurrencyPair;
import types.YahooFinanceAPI;
import yahoofinance.Stock;
import yahoofinance.quotes.fx.FxQuote;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // creating stocks
        String[] tickers = new String[] {"AAPL", "AMZN", "MSFT"};
        Map<String, Stock> stocks = YahooFinanceAPI.getStocks(tickers);
        investmentTypes.Stock appleStock = new investmentTypes.Stock(tickers[0], stocks);
        investmentTypes.Stock amazonStock = new investmentTypes.Stock(tickers[1], stocks);
        investmentTypes.Stock microsoftStock = new investmentTypes.Stock(tickers[2], stocks);

        // creating currencies
        String[] currencyPairs = new String []{"EURUSD=X", "GBPUSD=X", "AUDUSD=X" };
        double [] margin = new double[]{0.05, 0.12, 0.08};
        Map<String, FxQuote> currencies = YahooFinanceAPI.getCurrencies(currencyPairs);
        CurrencyPair eurUsd = new CurrencyPair(currencyPairs[0], currencies, margin[0]);
        CurrencyPair gbpUsd = new CurrencyPair(currencyPairs[1],currencies, margin[1]);
        CurrencyPair audUsd = new CurrencyPair(currencyPairs[2],currencies, margin[2]);

        // creating bonds
        Bond treasuryBond = new Bond("US Treasury bond", 10000, 2.5,
                "semi-annual", "AAA-Investment grade", 30, 10000);

        Bond corporateBond = new Bond("Vanguard Corporate bond", 150000, 6.5,
                "annual", "A-Investment grade",5, 15984.61);

        Bond mortgageBond = new Bond("Subprime mortgage bond", 7000, 7,
                "quarterly", "CCC-Speculative", 2, 10046.17);

        new LoginFrame(appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                audUsd, treasuryBond, corporateBond, mortgageBond);

    }
}
