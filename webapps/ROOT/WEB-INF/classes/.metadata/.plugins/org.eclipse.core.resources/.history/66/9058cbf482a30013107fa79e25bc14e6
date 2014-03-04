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
import bean.UserBean;

public class TradeController extends HttpServlet{

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
			RequestDispatcher rd = sc.getRequestDispatcher("/index.html");
			try {
				rd.forward(request, response);
				return;
			} catch (ServletException e) {
				out.println(e.getMessage());
			}
		}
		

		if (request.getParameter("nickname") != null) {
			UserBean u = (bean.UserBean) session.getAttribute("user");
			if(u == null){
				throw new IOException("User is null");
			}
			u.setNickname(request.getParameter("nickname"));
			RequestDispatcher rd = sc.getRequestDispatcher("/trade.jsp");
			try {
				rd.forward(request, response);
				return;
			} catch (ServletException e) {
				out.println(e.getMessage());
			}
		}

		String message = "";

		if (request.getParameter("action") != null) {
			UserBean user = (bean.UserBean) session.getAttribute("user");
			bean.TradeSystemBean tradeSys = (bean.TradeSystemBean) sc
					.getAttribute("tradingsystem");
			if (request.getParameter("action").equals("addSecurity")) {
				tradeSys.addSecurity(request.getParameter("security"));
				message = "addSecurity";
			}

			if (request.getParameter("action").equals("addOrder")) {
				String type = request.getParameter("buyOrSell");
				OrderBean order = new OrderBean();
				order.setName(request.getParameter("security"));
				order.setPrice(new Double(request.getParameter("price")));
				order.setAmount(new Integer(request.getParameter("amount")));
				order.setUserId(user.getNickname());
				if (type.equals("B")) {
					tradeSys.addBuyOrder(order);
				} else if (type.equals("S")) {
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
			out.print(e.getMessage());
		} catch (IOException e) {
			out.print(e.getMessage());
		}
		out.close();
	}

}