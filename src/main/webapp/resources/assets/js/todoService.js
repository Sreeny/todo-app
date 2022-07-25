'use strict';
angular.module('myTodoApp').factory('TodoService', ['$http', '$q', function($http, $q){
    var REST_SERVICE_URI = 'http://localhost:8080/todo/';
    var factory = {
    	allTodos: allTodos,
    	deleteTodo:deleteTodo
       
    };
    return factory;
    function allTodos(userId) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+'allTodosByUserId/'+userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error in gettting all todos for the user: '+userId);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteTodo(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+'delete/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.info(response.data);
            },
            function(errResponse){
                console.error('Error in gettting all todos for the user: '+id);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
   
}]);