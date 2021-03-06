<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.OrderBean" %>
<%@ page import="bean.ClosedOrderBean" %>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
<meta charset="UTF-8">
<title>Trade 0.1 Beta</title>
</head>

<body>

<%
bean.TradeSystemBean tradeSys = (bean.TradeSystemBean)pageContext.getServletContext().getAttribute("tradingsystem");
bean.UserBean user = (bean.UserBean)pageContext.getSession().getAttribute("user");

bean.OrderBean orderBean;
%>


<%=request.getParameter("message")%>

<h3>Addera ett värdepapper</h3>
<form action="/TradeController">
<input type="hidden" name="action" value="addSecurity">
<input type="text" name="security" value=""><br>
<input type="submit" value="Utför">
</form>

<h3>Lägg en köp/säljorder på ett värdepapper</h3>
<form action="/TradeController">
<input type="hidden" name="action" value="addOrder">
Värdepapper: <select name="security">
<%
for(String security : tradeSys.getAllSecurities()){
%>
<option value="<%=security %>"><%=security %></option>
<%}%>
</select><br>
Köp: <input type="radio" name="buyOrSell" value="B" checked>
Sälj: <input type="radio" name="buyOrSell" value="S"><br>
Pris: <input type="text" name="price" value=""><br>
Antal: <input type="text" name="amount" value=""><br>
<input type="submit" value="Utför">
</form>

<h3>Visa avslutade affärer i ett värdepapper</h3>
<form action="/TradeController">
<input type="hidden" name="action" value="viewTrades">
Värdepapper: <select name="security">
<%for(String security : tradeSys.getAllSecurities()){
%>
<option value="<%=security %>"><%=security %></option>
<%}%>
</select><br>
<input type="submit" value="Utför">
</form>

<h2> Trades </h2>
<table>
<tr>
  <th>Name</th>
  <th>Price</th> 
  <th>Amount</th>
  <th>Buyer</th>
  <th>Seller</th>
</tr>
<%
for(ClosedOrderBean order : tradeSys.getTrades()){
%>
<tr>
<td><%=order.getName() %></td>
<td><%=order.getPrice() %></td>
<td><%=order.getAmount() %></td>
<td><%=order.getUserId() %></td>
<td><%=order.getUserIdSeller() %></td>
<%}%>

</table>

<!--
<h2> Buy Orders </h2>
<table>
<tr>
  <th>Name</th>
  <th>Price</th> 
  <th>Amount</th>
  <th>Buyer</th>
</tr>
<%
for(OrderBean order : tradeSys.getBuyOrders()){
%>
<tr>
<td><%=order.getName() %></td>
<td><%=order.getPrice() %></td>
<td><%=order.getAmount() %></td>
<td><%=order.getUserId() %></td>
<%}%>
</table>

<h2> Sale Orders </h2>
<table>
<tr>
  <th>Name</th>
  <th>Price</th> 
  <th>Amount</th>
  <th>Seller</th>
</tr>
<%
for(OrderBean order : tradeSys.getSaleOrders()){
%>
<tr>
<td><%=order.getName() %></td>
<td><%=order.getPrice() %></td>
<td><%=order.getAmount() %></td>
<td><%=order.getUserId() %></td>
<%}%>
</table>

<h2> Closed Orders </h2>
<table>
<tr>
  <th>Name</th>
  <th>Price</th> 
  <th>Amount</th>
  <th>Buyer</th>
  <th>Seller</th>
</tr>
<%
for(ClosedOrderBean order : tradeSys.getClosedOrders()){
%>
<tr>
<td><%=order.getName() %></td>
<td><%=order.getPrice() %></td>
<td><%=order.getAmount() %></td>
<td><%=order.getUserId() %></td>
<td><%=order.getUserIdSeller() %></td>
<%}%>

</table>
-->
</body>

</html>
