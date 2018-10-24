<%@page import="com.axelor.pojo.Employee"%>
<%@page import="com.axelor.pojo.Address"%>
<%@page import="com.axelor.pojo.Contact"%>
<%@page import="com.axelor.pojo.GroupCircle"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#search_field').on('keyup', function() {
			var value = $(this).val();
			var patt = new RegExp(value, "i");

			$('#myTable').find('tr').each(function() {
				if (!($(this).find('td').text().search(patt) >= 0)) {
					$(this).not('.myHead').hide();
				}
				if (($(this).find('td').text().search(patt) >= 0)) {
					$(this).show();
				}
			});

		});

		$("td").css("background-color", "Lavender");

	});
</script>
<script language="javascript" type="text/javascript">
	function add() {
		var newRow = document.getElementById("place").insertRow();
		newRow.innerHTML = document.getElementById("conId").innerHTML;
	}
	function addAddress() {
		var newRow = document.getElementById("placeAddress").insertRow();
		newRow.innerHTML = document.getElementById("addId").innerHTML;
	}
</script>
<style>
td {
	padding: 5px 5px;
}

.table {
	background: Silver;
	border: 6px groove lightblue;
	padding: 5px 15px;
	width: 30%;
}

.table2 {
	background: Silver;
	border: 6px groove lightblue;
	padding: 5px 10px;
}

input:focus {
	background-color: lightblue;
}

.groove {
	margin: auto;
	width: 30%;
	padding: 10px;
	border: 6px groove lightblue;
	border-radius: 12px;
	background-color: Silver;
}

th {
	background-color: Silver;
}

.button {
	padding: 8px 23px;
	text-align: center;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
</head>

<body>
	<center>
		<h1 class="groove">Insert your detail</h1>
		<br />
		<form action="<%=request.getContextPath()%>/add" method="post">

			<table class="table" style="with: 50%">
				<tr>
					<td><b>First Name :</b></td>
					<td colspan="4"><input type="text" name="first_name" required /></td>
				</tr>
				<tr>
					<td><b>Last Name :</b></td>
					<td colspan="4"><input type="text" name="last_name" required /></td>
				</tr>
				<tr>
					<td><b>Gender :</b></td>
					<td colspan="2"><input type="radio" name="gender" value="male" />Male</td>
					<td colspan="2"><input type="radio" name="gender"
						value="female" />Female</td>
				</tr>
				<tr>
					<td><b>Select Your Group:</b></td>
					
				<c:forEach var="groupCircle" items="${groupCircle}">	
					<td><input type="checkbox" name="group" value="${groupCircle.id}" />${groupCircle.groupName}</td>
				</c:forEach>
				
			<!-- 		<td><input type="checkbox" name="group" value="friends" />Friends</td>
					<td><input type="checkbox" name="group" value="family" />Family</td>
					<td><input type="checkbox" name="group" value="Office" />Office</td>
					<td><input type="checkbox" name="group" value="college" />College</td> -->
				</tr>
				<tr id=addId>
					<td colspan="5">
						<table style="with: 100%">
							<tr>
								<td colspan="3"><b>Address :</b></td>
							</tr>
							<tr>
								<td>Title :</td>
								<td><input type="text" name="title" required/></td>
							</tr>
							<tr>
								<td>Home_No :</td>
								<td><input type="text" name="home_no" required/></td>
							</tr>
							<tr>
								<td>Street :</td>
								<td><input type="text" name="street" required/></td>
							</tr>
							<tr>
								<td>City :</td>
								<td><input type="text" name="city" required/></td>
							</tr>
							<tr>
								<td>State :</td>
								<td><input type="text" name="state" required/></td>
							</tr>
							<tr>
								<td>PinCode :</td>
								<td><input type="text" name="pincode" required/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5"><table id="placeAddress"></table></td>
				</tr>
				<tr>
					<td colspan="5"><input type="button" value="Add Address"
						onClick="addAddress();"></td>
				</tr>
				<tr>
					<td colspan="5">
						<table id=conId>
							<tr>
								<td><b>Contact No :</b></td>
								<td><input type="text" name="contact" pattern="[7-9]{1}[0-9]{9}" required/></td>
								<td><select name="contAddress">
										<option value="home">Home</option>
										<option value="office">Office</option>
										<option value="personal">Personal</option>
										<option value="other">Other</option>
								</select></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5"><table id="place"></table></td>
				</tr>
				<tr>
					<td colspan="5"><input type="button" value="Add Contact"
						onClick="add();"></td>
				<tr>
					<th colspan="5"><input class="button" type="submit"
						value="Submit" name="submit" /></th>
				</tr>
			</table>

		</form>
	</center>
	<center>
		<h1>
			<font color="MidnightBlue">All Employee</font>
		</h1>
		<div>
			<h2 class="groove">
				<b>Search :</b><input type="text" placeholder="Search..."
					id="search_field">
			</h2>
		</div>
		<br />
		<table class="table2" border=2 id="myTable">

			<tr class="myHead">
				<th>ID</th>
				<th>First_Name</th>
				<th>Last_Name</th>
				<th>Gender</th>
				<th>Address</th>
				<th>Contact</th>
				<th>Group</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>

			<c:forEach var="employee" items="${employee}" varStatus="status">
				<tr>
					<td><c:out value="${employee.id}" /></td>
					<td><c:out value="${employee.first_name}" /></td>
					<td><c:out value="${employee.last_name}" /></td>
					<td><c:out value="${employee.gender} " /></td>
					<td><c:forEach var="address" items="${employee.addressList}">
							<c:out value="${address.title}, " />
							<c:out value="${address.home_no}, " />
							<c:out value="${address.street}, " />
							<c:out value="${address.city}, " />
							<c:out value="${address.state} - " />
							<c:out value="${address.pincode}" />
							<br />
						</c:forEach></td>
					<td><c:forEach var="contact" items="${employee.contactList}">
							<c:out value="${contact.contact}" />
							<c:out value="${contact.contAddress}" />
							<br />
						</c:forEach></td>

					<td><c:forEach var="groupCircle"
							items="${employee.groupcircle}">

							<c:out value="${groupCircle.groupName} " />
							<br />
						</c:forEach></td>
					<td><a
						href="<%=request.getContextPath()%>/edit/<c:out value="${employee.id}" />">Edit</a></td>
					<td><a
						href="<%=request.getContextPath()%>/delete/<c:out value="${employee.id}"/>"
						onclick="return window.confirm('Are You Sure?')">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>




