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
		class="orderLable">Order Status</label>
	<table class="" id="">
		<thead class="">
			<tr>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(0)">Created</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(2)">Item</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(2)">Customer</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(1)">Status</th>

			</tr>
		<tbody>
			<c:forEach items="${items}" var="item">
				<tr>
					<td><fmt:formatDate value="${date}" type="time" /></td>
					<td><img src="${item.getImage()}">
						<br> ${item.getName()}&nbsp ${item.getPrice()}</td>
					<td><p>Roshanak</p></td>
					<td><form name="status" method="get" action="#">
							<select>
								<option>In Queue</option>
								<option>Completed</option>
								<option>In progress</option>

							</select>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	</section></main>
	<exe:footer />
</body>
</html>