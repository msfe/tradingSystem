package bean;

import java.util.ArrayList;

public class TradeSystemBean {

	private ArrayList<OrderBean> saleOrders;
	private ArrayList<OrderBean> buyOrders;
	private ArrayList<String> allSecurities;
	private ArrayList<ClosedOrderBean> closedOrders;

	enum OrderStatus {
		buy, sell
	};

	public TradeSystemBean() {
		saleOrders = new ArrayList<OrderBean>();
		buyOrders = new ArrayList<OrderBean>();
		allSecurities = new ArrayList<String>();
		closedOrders = new ArrayList<ClosedOrderBean>();
	}

	public void addSecurity(String security) {
		allSecurities.add(security);
	}

	public boolean securityExists(String security) {
		return allSecurities.contains(security);
	}

	/**
	 * Adds a new sellOrder and if it is possible close a deal instantly
	 * @param sellOrder A new sell Order
	 */
	public void addSaleOrder(OrderBean sellOrder) {
		saleOrders.add(sellOrder);
		OrderBean matchingBuyOrder = matchingOrderExists(sellOrder, buyOrders,
				OrderStatus.sell);
		if (matchingBuyOrder != null) {
			closeDeal(matchingBuyOrder, sellOrder);
		}
	}

	/**
	 * Adds a new buyOrder and if it is possible close a deal instantly
	 * @param buyOrder a new buyOrder
	 */
	public void addBuyOrder(OrderBean buyOrder) {
		buyOrders.add(buyOrder);
		OrderBean matchingSellOrder = matchingOrderExists(buyOrder, saleOrders,
				OrderStatus.buy);
		if (matchingSellOrder != null) {
			closeDeal(buyOrder, matchingSellOrder);
		}
	}

	/**
	 * A method that closes a deal based on a buy order and a sell order
	 * The price will be set to whatever the buyer can accept to pay as long
	 * as it is a minimum of what the seller is expected to get in return.
	 * 
	 * @param buyOrder
	 * @param sellOrder
	 * @return
	 */
	private boolean closeDeal(OrderBean buyOrder, OrderBean sellOrder) {
		if (buyOrder.getPrice() > sellOrder.getPrice()) {
			ClosedOrderBean closedOrder = new ClosedOrderBean(buyOrder,
					sellOrder);

			closedOrders.add(closedOrder);
			
			// Adjust the buy/sell order size or remove if empty
			if (closedOrder.getAmount() >= buyOrder.getAmount()) {
				buyOrders.remove(buyOrder);
				// If they were the same
				if (closedOrder.getAmount() >= sellOrder.getAmount()) {
					saleOrders.remove(sellOrder);
				} else { // Their might be more matches for the saleOrder
					sellOrder.setAmount(sellOrder.getAmount()
							- closedOrder.getAmount());
					OrderBean newOrderMatch = matchingOrderExists(sellOrder,
							buyOrders, OrderStatus.sell);
					if (newOrderMatch != null) {
						closeDeal(newOrderMatch, sellOrder);
					}
				}
			} else { // saleOrder set the limit for the amount, hence removed.
				saleOrders.remove(sellOrder);
				buyOrder.setAmount(buyOrder.getAmount()
						- closedOrder.getAmount());
				OrderBean newOrderMatch = matchingOrderExists(buyOrder,
						saleOrders, OrderStatus.buy);
				if (newOrderMatch != null) {
					closeDeal(buyOrder, newOrderMatch);
				}
			}
			return true;
		}

		return false;
	}

	public ArrayList<ClosedOrderBean> getClosedOrders() {
		return closedOrders;
	}

	private OrderBean matchingOrderExists(OrderBean order,
			ArrayList<OrderBean> orderList, OrderStatus status) {
		String name = order.getName();
		float price = order.getPrice();
		for (OrderBean iterateOrder : orderList) {
			if (iterateOrder.getName() == name) {
				if (status == OrderStatus.sell
						&& iterateOrder.getPrice() > price) {
					return iterateOrder;
				} else if (status == OrderStatus.buy && iterateOrder.getPrice() < price) {
					return iterateOrder;
				}
			}
		}
		return null;
	}

}