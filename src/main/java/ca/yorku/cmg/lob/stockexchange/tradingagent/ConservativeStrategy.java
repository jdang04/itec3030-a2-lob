package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.tradestandards.IOrder;
import ca.yorku.cmg.lob.orderbook.Ask;

/**
 * A trading agent kind that reacts more carefully and conservatively to news.
 */
public class ConservativeStrategy implements ITradingStrategy {
	private Trader t;
	private StockExchange exc;
	private NewsBoard n;

	// Modify the constructor to accept the parameters
	public ConservativeStrategy(Trader t, StockExchange e, NewsBoard n) {
		this.t = t;
		this.exc = e;
		this.n = n;
	}

	public void actOnEvent(Event e, int pos, int price) {
		IOrder newOrder = null;

		if (e instanceof GoodNews) {
			newOrder = new Bid(t, e.getSecrity(), (int) Math.round(price * 1.05), (int) Math.round(pos * 0.2), e.getTime());
		} else if (e instanceof BadNews) {
			newOrder = new Ask(t, e.getSecrity(), (int) Math.round(price * 0.95), (int) Math.round(pos * 0.2), e.getTime());
		} else {
			System.out.println("Unknown event type");
		}

		if (newOrder != null) {
			exc.submitOrder(newOrder, e.getTime());
		}
	}
}
