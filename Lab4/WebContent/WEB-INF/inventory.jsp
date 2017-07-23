<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/adminHeader.tld" prefix="ex"%>
<%@ taglib uri="/WEB-INF/adminFooter.tld" prefix="exe"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="app.css">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Burrito Cafe</title>
</head>
<body>
	<ex:header />
	<main> <section class="order"> <label
		class="orderLable">Existing Food Items</label>
	<table id="order" class="cart-table center">
		<thead class="cart-table">
			<tr>
				<td class="TableHeader">Created</td>
				<td class="TableHeader">Item</td>
				<td class="TableHeader">Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${food_items}" var="food_items">
				<tr>
					<td><fmt:formatDate value="${date}" type="time" /></td>
					<td><img src="${food_items.getImage()}">
						<br> ${food_items.getName()} &nbsp ${food_items.getPrice()}</td>
					<td><form method="get">
							<button name="Submit" value="${food_items.getName()}">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="actions">
		<button class="confirm_order_button" style="margin-top: 10px;">
			Add to the Menu</button>
		<button class="delete_all_button"
			style="margin-top: 10px; margin-left: 5px;">Clear All</button>
	</div>
	</section> </main>
	<exe:footer />
</body>
</html>