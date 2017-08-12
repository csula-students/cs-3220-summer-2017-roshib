<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/lab7/customerHeader.tld" prefix="cx"%>
<%@ taglib uri="/WEB-INF/lab7/customerFooter.tld" prefix="cxc"%>
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
		class="orderLable">Order Status</label>
	<table id="order" class="cart-table center">
		<thead class="cart-table">
			<tr>
				<td class="TableHeader">Food Item</td>
				<td class="TableHeader">Customer</td>
				<td class="TableHeader">Status</td>
				<td class="TableHeader">Date</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><img src="${item.getFood().getImage()}"> <br>
						${item.getFood().getName()}&nbsp ${item.getFood().getPrice()}</td>
					<td>${item.getCustomerName()}</td>
					<td>${item.getStatus()}</td>
					<td>${item.getDate()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</section> </main>
	<div style="padding-top: 150px;"></div>
	<cxc:customer_footer />
</body>
</html>