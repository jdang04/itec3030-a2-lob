package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;

/**
 * A trading agent that works for institutional traders.
 */
public class TradingAgentInstitutional extends TradingAgent {

    /**
     * Constructor to initialize the TradingAgentInstitutional with required parameters.
     * @param t The {@linkplain Trader} object associated with the agent.
     * @param e The {@linkplain StockExchange} object at which the agent has an account and trades in.
     * @param n The {@linkplain NewsBoard} object that generates news events.
     * @param strategy The strategy that the agent will follow (e.g., aggressive or conservative).
     */
    public TradingAgentInstitutional(Trader t, StockExchange e, NewsBoard n, ITradingStrategy strategy) {
        super(t, e, n, strategy);  // Pass the strategy to the parent constructor
    }

    /**
     * Handle events by delegating to the current strategy.
     * @param e The event to handle.
     * @param pos The position (number of units) of the trader in the security.
     * @param price The current price of the relevant ticker.
     */
    @Override
    protected void actOnEvent(Event e, int pos, int price) {
        // Delegate the action to the current strategy
        strategy.actOnEvent(e, pos, price);
    }
}
