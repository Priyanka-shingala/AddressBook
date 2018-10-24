
<%@page import="com.axelor.pojo.Employee"%>
<%@page import="com.axelor.pojo.Address"%>
<%@page import="com.axelor.pojo.Contact"%>
<%@page import="com.axelor.pojo.GroupCircle"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Form</title>
<style>
td {
	background-color: Lavender;
	padding: 5px 5px;
}

.table {
	background: Silver;
	border: 6px groove lightblue;
	padding: 8px 23px;
	width: 30%;
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
	<%
		Employee employee = (Employee) request.getAttribute("employee");
		List<Address> address = (List) request.getAttribute("address");
		List<Contact> contact = (List) request.getAttribute("contact");
		List<GroupCircle> groupCircle = (List) request.getAttribute("groupCircle");
		/* GroupCircle groupCircle = (GroupCircle) request.getAttribute("groupCircle"); */
	%>
	<center>
		<h1 class="groove">Edit Person</h1>
		<br>
		<form
			action="<%=request.getContextPath()%>/update/<%=employee.getId()%>"
			method="POST">

			<table class="table">
				<tr>
					<td><b>FirstName :</b></td>
					<td colspan="4"><input type="text" name="first_name"
						value="<%=employee.getFirst_name()%>"></td>
				</tr>
				<tr>
					<td><b>LastName :</b></td>
					<td colspan="4"><input type="text" name="last_name"
						value="<%=employee.getLast_name()%>"></td>
				</tr>
				<tr>
					<td><b>Gender :</b></td>
					<c:if test="${employee.getGender() == 'male'}">
						<c:set var="genCheckMale" value="checked='true'" />
						<p>
					</c:if>
					<c:if test="${employee.getGender() == 'female'}">
						<c:set var="genCheckFemale" value="checked='true'" />
						<p>
					</c:if>

					<td colspan="2"><input type="radio" name="gender" value="male"
						${genCheckMale} />Male</td>
					<td colspan="2"><input type="radio" name="gender"
						value="female" ${genCheckFemale} />Female</td>

				</tr>
				<tr>
				<td>Group:</td>
				<c:forEach var="allGroupCircle1" items="${allGroupCircle}">	
					<c:if test="${fn:contains(groupCircle,allGroupCircle1.groupName)}">
						<c:set var="grpCheck" value="checked=true" />
					</c:if>
					<c:if test="${not fn:contains(groupCircle,allGroupCircle1.groupName)}">
						<c:set var="grpCheck" value="" />
					</c:if>
					<td><br /><input type="checkbox" name="group"
							value="${allGroupCircle1.id}" ${grpCheck} />${allGroupCircle1.groupName}</td>
				</c:forEach>
				
			</tr>
				<c:forEach var="address" items="${address}">
					<input type="hidden" value="${address.id}" name="addressId">
					<tr>
						<td colspan="5"><b>Address :</b></td>
					</tr>
					<tr>
						<td>Title:</td>
						<td colspan="4"><input type="text" name="title"
							value="${address.title}"></td>
					</tr>
					<tr>
						<td>Home-No:</td>
						<td colspan="4"><input type="text" name="home_no"
							value="${address.home_no}"></td>
					</tr>
					<tr>
						<td>Street:</td>
						<td colspan="4"><input type="text" name="street"
							value="${address.street}"></td>
					</tr>
					<tr>
						<td>City:</td>
						<td colspan="4"><input type="text" name="city"
							value="${address.city}"></td>
					</tr>
					<tr>
						<td>State:</td>
						<td colspan="4"><input type="text" name="state"
							value="${address.state}"></td>
					</tr>
					<tr>
						<td>PinCode:</td>
						<td colspan="4"><input type="text" name="pincode"
							value="${address.pincode}"></td>
					</tr>
				</c:forEach>

				<c:forEach var="contact" items="${contact}">
					<c:if test="${contact.getContAddress() == 'other'}">
						<c:set var="conCheckOther" value="selected='true'" />
						<p>
					</c:if>
					<c:if test="${contact.getContAddress() == 'home'}">
						<c:set var="conCheckHome" value="selected='true'" />
						<p>
					</c:if>
					<c:if test="${contact.getContAddress() == 'office'}">
						<c:set var="conCheckOffice" value="selected='true'" />
						<p>
					</c:if>
				    <c:if test="${contact.getContAddress() == 'personal'}">
						<c:set var="conCheckPersonal" value="selected='true'" />
						<p>
					</c:if>
					<input type="hidden" value="${contact.id}" name="contactId">
					<tr>
						<td><b>Contact :</b></td>
						<td colspan="4"><input type="text" name="phone"
							value="${contact.contact}"></td>
						<td><select name="contAddress">
								<option value="home" ${conCheckHome}>Home</option>
								<option value="office" ${conCheckOffice}>Office</option>
								<option value="personal" ${conCheckPersonal}>Personal</option>
								<option value="other" ${conCheckOther}>Other</option>
						</select></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="center"><input class="button"
						type="submit" name="submit" value="Update"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>

