package investmentTypes;

import investmentAttributes.InvestmentAttributes;

public interface Investment  {

    public void changeMarketPrice();
    public void buy(int number);
    public void sell(int number);
    public InvestmentAttributes getAttributes();

}
