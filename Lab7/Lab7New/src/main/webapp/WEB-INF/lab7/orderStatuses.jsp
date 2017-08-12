<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/lab7/adminHeader.tld" prefix="ex"%>
<%@ taglib uri="/WEB-INF/lab7/adminFooter.tld" prefix="exe"%>
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
		class="orderLable">Order Status</label>
	<table class="" id="">
		<thead class="">
			<tr>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(0)">Food Item</th>

				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(2)">Customer</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(1)">Date</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(1)">Status</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(1)">Action</th>

			</tr>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><img src="${item.getFood().getImage()}"> <br>
						${item.getFood().getName()}&nbsp ${item.getFood().getPrice()}</td>
					<td><p>${item.getCustomerName()}</p></td>
					<td>${item.getDate()}</td>
					<td>${item.getStatus()}</td>
					<td><form method="post">
							<select id="status" name="status">
								<option value="IN_QUEUE" selected>IN_QUEUE</option>
								<option value="IN_PROGRESS">IN_PROGRESS</option>
								<option value="COMPLETED">COMPLETED</option>
							</select>
							<button name="Edit" value="${item.getId()}">Edit</button>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	</section></main>
	<div style="padding-top:150px;"></div>
	<exe:footer />
</body>
</html>