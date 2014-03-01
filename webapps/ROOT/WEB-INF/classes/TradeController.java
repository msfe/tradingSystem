import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderBean;

public class TradeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8919025606473395622L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		PrintWriter out = response.getWriter();
		ServletContext sc = getServletContext();
		synchronized (this) {
			if (sc.getAttribute("tradingsystem") == null) {
				sc.setAttribute("tradingsystem", new bean.TradeSystemBean());
			}
		}

		HttpSession session = request.getSession();
		if (session.isNew()) {
			session.setAttribute("user", new bean.UserBean());
			RequestDispatcher rd = sc.getRequestDispatcher("/trade_index.html");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				out.println(e.getMessage());
			}
		}

		// TODO någon typ av hantering av inloggning

		if (request.getParameter("nickname") == null) {
			bean.UserBean u = (bean.UserBean) session.getAttribute("user");
			u.setNickname(request.getParameter("nickname"));
			RequestDispatcher rd = sc.getRequestDispatcher("/trade.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				out.println(e.getMessage());
			}
		}

		String message = "";

		if (request.getParameter("action") != null) {
			bean.UserBean user = (bean.UserBean) session.getAttribute("user");
			bean.TradeSystemBean tradeSys = (bean.TradeSystemBean) sc
					.getAttribute("tradingsystem");

			if (request.getParameter("action").equals("addSecurity")) {
				tradeSys.addSecurity(request.getParameter("")); // TODO add
																// right param
				message = "addSecurity";
			}

			if (request.getParameter("action").equals("addOrder")) {
				String type = request.getParameter(""); // TODO add right param
				OrderBean order = new OrderBean();
				order.setName(request.getParameter("")); // TODO add right param
				order.setPrice(new Float(request.getParameter(""))); // TODO add
																		// right
																		// param
				order.setAmount(new Integer(request.getParameter(""))); // TODO
																		// add
																		// right
																		// param
				order.setUserId(request.getParameter("")); // TODO add right
															// param
				if (type == "buy") {
					tradeSys.addBuyOrder(order);
				} else if (type == "sell") {
					tradeSys.addSaleOrder(order);
				}
				message = "addOrder";
			}

			if (request.getParameter("action").equals("viewTrades")) {
				// Kod för att lägga en köp eller säljorder
				message = "viewTrades";
			}
		}

		try {
			RequestDispatcher rd = request
					.getRequestDispatcher("trade.jsp?message=" + message);
			rd.forward(request, response);
		} catch (ServletException e) {
			System.out.print(e.getMessage());
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		out.close();
	}

}