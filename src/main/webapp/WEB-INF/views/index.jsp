<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>


<%@ include file="header.jsp"%>
<br />
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<form:form class='card p-3 bg-light' action="/todo/login" method="post" modelAttribute="login">

				<h5 class="card-header" style="background-color: #18d2f8;">Login</h5>
				<div class="mb-3 form-group required">
					<label for="username" class="control-label">User ID:</label>
					<input type="text" class="form-control " id="userName" name="userName"">
					<form:errors path="userName" cssStyle="color: #ff0000" cssClass="error" />
				</div>
				<div class="mb-3 form-group required">
					<label for="password" class="control-label">Password:</label>
					<input type="password" class="form-control" id="password" name="password">
					<form:errors path="password" cssStyle="color: #ff0000" cssClass="error" />
				</div>
				<div class="mb-3">
					<p>
						<input type="submit" value="Submit" />
						<input type="reset" value="Reset" />
				</div>
			</form:form>
		</div>
		<div class="col"></div>
	</div>
</div>

 <%@ include file="footer.jsp"%>
</html>

