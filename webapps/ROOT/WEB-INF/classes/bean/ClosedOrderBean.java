package bean;

public class ClosedOrderBean extends OrderBean {

	private String userIDSeller;

	/**
	 * Class assumes that the price of the buyOrder is greater than the one in
	 * the sellOrder.
	 */
	public ClosedOrderBean(OrderBean buyOrder, OrderBean sellOrder) {
		super(buyOrder.getName(), buyOrder.getPrice(), buyOrder.getAmount(),
				buyOrder.getuserID());
		userIDSeller = sellOrder.getuserID();
		
		//correct amount of closedOrder if sellOrder was limiting
		if (buyOrder.getAmount() > sellOrder.getAmount()) {
			setAmount(sellOrder.getAmount());
		}
	}

	public String getuserIDSeller() {
		return userIDSeller;
	}

}