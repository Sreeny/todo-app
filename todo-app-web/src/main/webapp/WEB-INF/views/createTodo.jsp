<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp"%>


<html>

<br />
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<c:if test = "${not empty message}">
         		<p id="message" style ="color: #ff0000"><c:out value="${message}" ></c:out><p>
     		</c:if>
			<form:form class='card p-3 bg-light' action="/todo/save" method="post" modelAttribute="todo">
				<input type="hidden" id= "id" name="id" value="${todo.id}"/>
				<h5 class="card-header" style="background-color: #18d2f8;">New ToDo</h5>
				<div class="mb-3 form-group required">
					<label for="taskName" class="control-label">Name</label>
					<input type="text" class="form-control " id="taskName" name="taskName" value="${todo.taskName}">
					<form:errors path="taskName" cssStyle="color: #ff0000" cssClass="error" />
				</div>
				<div class="mb-3">
					<label for="taskDesc" class="form-label">Description</label>
					<input type="text" class="form-control" id="description" name="description"  value="${todo.description}">
				</div>
				<div class="mb-3 form-group required">
					<label for="taskDueDate" class="control-label">Due Date</label>
					<div class="form-group">
						<div class="input-group" id="datepicker">
							<input type="text" class="form-control" id="dueDate" name="dueDate" value="${todo.dueDate}">
							<span class="input-group-append"> <span class="input-group-text bg-white d-block"> <i class="fa fa-calendar"></i>
							</span>
							</span>
						</div>
						<form:errors path="dueDate" cssStyle="color: #ff0000" cssClass="error" />
					</div>
				</div>
				<div class="mb-3">
				<label for="Status" class="form-label">Status</label>
				<select class="form-control" path="status" id="status" name="status" value=value="${todo.status}>
						<option value="">Please select</option>
                    	<c:forEach var="value" items="${statusEnum}">
                        	<option value="${value}"  ${value == todo.status ? 'selected' : ''}>${value}</option>
                    </c:forEach>
     				<select> 
				</div>
				<div class="mb-3">
					<p>
						 
						<input type="submit" id="submit" value="Submit" />
						<input type="reset" value="Reset" />
				</div>
			</form:form>
		</div>
		<div class="col"></div>
	</div>
</div>
<!-- jQuery CDN -->
<%@ include file="myTodoListBody.jsp"%>
<script>
            $(document).ready(function () {
                $('#datepicker').datetimepicker({
                	format: 'hh:mm:ss a'
                });
             
            });
</script>
</html>
<%@ include file="footer.jsp"%>
