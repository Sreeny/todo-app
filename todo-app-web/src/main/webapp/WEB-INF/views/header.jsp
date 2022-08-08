
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.js"></script>
<script src="<c:url value='/resources/assets/js/app.js' />"></script>
<script src="<c:url value='/resources/assets/js/todoController.js' />"></script>
<script src="<c:url value='/resources/assets/js/todoService.js' />"></script>


<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #18d2f8;">
	<h1>ToDO App</h1>
	<div class="collapse navbar-collapse " id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="<c:url value='/' />">Home <span class="sr-only"></span></a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value='/create' />">Create New ToDo </a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value='/allTodos' />">My ToDo's </a></li>
		</ul>
		
	</div>
	<span><c:if test = "${sessionScope.message != null || sessionScope.message != ''}">
         <p id="welcomeMessage">${sessionScope.message}<p>
      </c:if>
      </span>
</nav>
