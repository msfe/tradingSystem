import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TradeController extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response){
	
//    	ServletContext sc = getServletContext();
//    	if(sc.getAttribute("forum")==null){
//    	 sc.setAttribute("forum", new bean.Forum());
//    	}
//    	HttpSession session = request.getSession();
//    	if(session.isNew()){
//    	 out.println("<h1>Ny session!</h1>");
//    	 session.setAttribute("user", new bean.User());
//    	 out.println("<form>");
//    	 out.println("Nickname<input type=\"text\" name=\"nickname\"><br>");
//    	 out.println("Email<input type=\"text\" name=\"email\">");
//    	 out.println("<input type=\"submit\"></form>");
//    	}
//    	if(request.getParameter("email")!=null){
//    	 out.println("<h1>Ny användare!</h1>");
//    	 bean.User u = (bean.User)session.getAttribute("user");
//    	 u.setNickname(request.getParameter("nickname"));
//    	 u.setEmail(request.getParameter("email"));
//    	 out.println("<form>");
//    	 out.println("Text<input type=\"text\" name=\"text\"><br>");
//    	 out.println("<input type=\"submit\"></form>");
//    	}
//    	if(request.getParameter("text")!=null){
//    		 out.println("<h1>Nytt inlägg!</h1>");
//    		 bean.User u = (bean.User)session.getAttribute("user");
//    		 bean.Forum f = (bean.Forum)sc.getAttribute("forum");
//    		 bean.Post p = new bean.Post();
//    		 p.setText(request.getParameter("text"));
//    		 p.setNickname(u.getNickname());
//    		 f.addPost(p);
//    		 ArrayList posts = f.getPosts();
//    		 for(int i = 0; i < posts.size(); i++){
//    		p = (bean.Post)posts.get(i);
//    		out.println("<b>" + p.getText() + "</b><br>");
//    		out.println("<i>" + p.getNickname() + "</i><br>");
//    		 }
//    		 out.println("<form>");
//    		 out.println("Text<input type=\"text\" name=\"text\"><br>");
//    		 out.println("<input type=\"submit\"></form>");
//    		}
//    		out.close();
//    		 }
//    		}

    	
	String message = "";

	if(request.getParameter("action").equals("addSecurity")){
	    // Kod för att addera ett slags värdepapper;
	    message = "addSecurity";
	}
	
	if(request.getParameter("action").equals("addOrder")){
	    // Kod för att lägga en köp eller säljorder
	    // samt eventuellt skapa en trade
	    message = "addOrder";
	}
	    
	if(request.getParameter("action").equals("viewTrades")){
	    // Kod för att lägga en köp eller säljorder
	    message = "viewTrades";
	}
	
	try{
	    RequestDispatcher rd =
		request.getRequestDispatcher("trade.jsp?message=" + message);
	    rd.forward(request, response);
	}
	catch(ServletException e){
	    System.out.print(e.getMessage());
	}
	catch(IOException e){
	    System.out.print(e.getMessage());
	}
    }
} 