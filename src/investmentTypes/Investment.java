package investmentTypes;

import investmentAttributes.InvestmentAttributes;

import java.io.Serializable;

public interface Investment extends Serializable {

    public void changeMarketPrice();
    public void buy(int number);
    public void sell(int number);
    public InvestmentAttributes getAttributes();

}
