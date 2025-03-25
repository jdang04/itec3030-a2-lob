package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

public class TradingAgentRetailFactory extends AbstractTradingAgentFactory {

    @Override
    public TradingAgent createAgent(String type, String style, Trader t, StockExchange e, NewsBoard n) {
        ITradingStrategy strategy;
        if ("Conservative".equalsIgnoreCase(style)) {
            strategy = new ConservativeStrategy(t, e, n);
        } else {
            strategy = new AggressiveStrategy(t, e, n);
        }
        return new TradingAgentRetail(t, e, n, strategy); // Make sure to pass the strategy here
    }
}
