import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  message = localStorage.getItem('message');
  toDos = [];
  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getAllTodos("Sreeny").subscribe((data: any[])=>{
      console.log(data);
      this.toDos = data;
    })  ;
  }
  onRemove(id) {
    this.apiService.remove(id).subscribe((data: any[])=>{
      console.log(data);
      this.toDos = data;
      this.ngOnInit();
    })  ;
  }
  }

