import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private REST_SERVICE_URI = 'http://localhost:8090/todo-app-services/api/todo/';
  private REST_LOGIN_URI = 'http://localhost:8090/todo-app-services/api/';

  constructor(private httpClient: HttpClient) { }

  public getAllTodos(userId){  
		return this.httpClient.get(this.REST_SERVICE_URI+'allTodosByUserId/'+userId);  
	}  

  public save(todo) {
    return this.httpClient.post(this.REST_SERVICE_URI, todo);
  }

  public get(id){  
		return this.httpClient.get(this.REST_SERVICE_URI+id);  
	}  

  public remove(id){  
   	return this.httpClient.delete(this.REST_SERVICE_URI+'delete/'+id);  
	} 
  
  public login(loginPayload){  
    console.log("In service --"+loginPayload);
    return this.httpClient.post(this.REST_LOGIN_URI+'login/', loginPayload);  
 }  

}
