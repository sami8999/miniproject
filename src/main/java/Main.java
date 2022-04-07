import GUIS.*;
import types.Bond;
import types.Currency;
import types.User;
import types.YahooFinanceAPI;
import yahoofinance.Stock;
import yahoofinance.quotes.fx.FxQuote;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        // creating stocks
        String[] tickers = new String[] {"AAPL", "AMZN", "MSFT"};
        Map<String, Stock> stocks = YahooFinanceAPI.getStocks(tickers);
        types.Stock appleStock = new types.Stock(tickers[0], stocks);
        types.Stock amazonStock = new types.Stock(tickers[1], stocks);
        types.Stock microsoftStock = new types.Stock(tickers[2], stocks);

        // creating currencies
        String[] currencyPairs = new String []{"EURUSD=X", "GBPUSD=X", "AUDUSD=X" };
        double [] margin = new double[]{0.05, 0.12, 0.08};
        Map<String, FxQuote> currencies = YahooFinanceAPI.getCurrencies(currencyPairs);
        types.Currency eurUsd = new Currency(currencyPairs[0], currencies, margin[0]);
        types.Currency gbpUsd = new Currency(currencyPairs[1],currencies, margin[1]);
        types.Currency audUsd = new Currency(currencyPairs[2],currencies, margin[2]);

        // creating bonds
        types.Bond treasuryBond = new Bond("US Treasury bond", 10000, 2.5,
                "semi-annual", "AAA-Investment grade", 30, 10000);

        types.Bond corporateBond = new Bond("Vanguard Corporate bond", 150000, 6.5,
                "annual", "A-Investment grade",5, 15984.61);

        types.Bond mortgageBond = new Bond("Subprime mortgage bond", 7000, 7,
                "quarterly", "CCC-Speculative", 2, 10046.17);

        User newUser = new User("sami");

        new MainFrame(appleStock, amazonStock, microsoftStock, eurUsd, gbpUsd,
                audUsd, treasuryBond, corporateBond, mortgageBond, newUser);
    }


}
