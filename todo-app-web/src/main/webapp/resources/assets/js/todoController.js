'use strict';
angular.module('myTodoApp').controller('TodoController', ['$scope', 'TodoService', function($scope, TodoService) {
    var self = this;
    self.todos = [];
    self.todo= {};
    getAllTods();
    self.remove = remove;
    function getAllTods(userId){
    	TodoService.allTodos(userId)
            .then(
            function(data) {
                self.todos = data;
                console.log(data);
            },
            function(errResponse){
                console.error('Error getting all todos for the user: '+userId);
            }
        );
    }
    
    function deleteTodo(id){
    	TodoService.deleteTodo(id)
            .then(
            getAllTods,    // TODO:send logged in userid
            function(errResponse){
				console.error(errResponse);
                console.error('Error while deleting todo');
            }
        );
    }
    
	 function remove(id){
	    console.log('Deleting tod for ', id);
	
	    deleteTodo(id);
	}
   
}]);