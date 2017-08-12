<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/homework4/adminHeader.tld" prefix="ex"%>
<%@ taglib uri="/WEB-INF/homework4/adminFooter.tld" prefix="exe"%>
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
		class="orderLable">Add new Food</label>
	<table class="" id="">
		<thead class="">
			<tr>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(0)">Name</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(2)">Price</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(2)">Description</th>
				<th
					style="line-height: 2em; color: black; border: 3px solid #3a4a34; text-align: center; border-radius: 5px; font-weight: bold;"
					class="TableHeader" onclick="sortTable(1)">Image Link</th>

			</tr>
		</thead>
		<tbody>
			<tr>
				<Form method="post">
				<td><input type="text" name="name" id="name"> </input></td>
				<td><input type="text" name="price" id="price"> </input></td>
				<td><input type="text" name="description" id="description">
					</input></td>
				<td><input type="text" name="image" id="image"> </input>
				&nbsp <button name="Submit" value="Submit" style="margin-top: 10px; padding:0.5em;">
					Add</button>
				</td>
				
				</Form>
			</tr>
		</tbody>
	</table>

	</section></main>
	<div style="padding-top:65px;"></div>
	<exe:footer />
</body>
</html>