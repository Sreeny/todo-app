
<body ng-app="myTodoApp">
	<div class="container" ng-controller="TodoController as ctrl">
		<div class="row">

			<div class="col">
				<div class=" class='card p-4 bg-light'">

					<h5 class="card-header" id="myTodoListHeader" style="background-color: #18d2f8;">My Todo List</h5>
					<div class="tablecontainer">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Task</th>
									<th>Description</th>
									<th>Due Date</th>
									<th>Status</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="p in ctrl.todos">
									<td><span ng-bind="p.id" name="todoId"></span></td>
									<td><span ng-bind="p.taskName"></span></td>
									<td><span ng-bind="p.description"></span></td>
									<td><span ng-bind="p.dueDate"></span></td>
									<td><span ng-bind="p.status"></span></td>
									<td>
			                            <button type="button"  class="btn btn-success custom-width"><a href = "<c:url value='/edit/{{p.id}}' />">Edit</a></button>  <button name="removeButton" type="button" ng-click="ctrl.remove(p.id)" class="btn btn-danger custom-width">Remove</button>
			                        </td> 
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
