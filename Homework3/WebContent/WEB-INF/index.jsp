<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/customerHeader.tld" prefix="cx"%>
<%@ taglib uri="/WEB-INF/customerFooter.tld" prefix="cxc"%>
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
	<cx:customer_header />
	<main> <section class="order"> <label
		class="orderLable">Food Menu</label>
	<table id="order" class="cart-table center">
		<thead class="cart-table">
			<tr>
				<td class="TableHeader">Name</td>
				<td class="TableHeader">Price</td>
				<td class="TableHeader">Description</td>
				<td class="TableHeader">Image</td>
				<td class="TableHeader">Action</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${entries_food}" var="item">
				<tr>
					<td>${item.getName()}</td>
					<td>${item.getPrice()}</td>
					<td>${item.getComment()}</td>
					<td><img src="${item.getImage()}"> <br></td>
					<td><form method="get">
							<button name="Submit" value="${item.getName()}">Add to
								cart</button>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</section> </main>
	<cxc:customer_footer />
</body>
</html>