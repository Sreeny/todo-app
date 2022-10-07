import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { nullSafeIsEquivalent } from '@angular/compiler/src/output/output_ast';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {

  todoForm: FormGroup;
  id: number;
  todo = {
    "id": "",
    "userId": "",
    "taskName": "",
    "description": "",
    "dueDate": "",
    "status": ""
  };
  submitted = false;
  message = "";

  statuses = ["PENDING", "COMPLETE", "DEFERRED", "OVERDUE"];
  toDos = [];
  constructor(private apiService: ApiService, private route: ActivatedRoute, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.todoForm = this.formBuilder.group({
      id:[],
      taskName: ['', Validators.required],
      description: [''],
      dueDate: ['', Validators.required],
      status: ['']
    });

    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = +params.get('id')
    });

    this.getAllTodos();
    if (this.id != null) {
      this.apiService.get(this.id)
        .subscribe(
          data => {
            console.log("success full fetched todo for !!!" + this.id);
            console.log("success full fetched todo for !!!" + JSON.stringify(data));
            this.todo = JSON.parse(JSON.stringify(data));
          },
          error => {
            console.log("failed!!!!!");
          });
    }
  }

  get formControls() { return this.todoForm.controls; }
  getAllTodos() {
    this.apiService.getAllTodos("Sreeny").subscribe((data: any[]) => {
      console.log(data);
      this.toDos = data;
    });

  }

  onSubmit() {

    this.submitted = true;
    if (this.todoForm.invalid) {
      return;
    }

    this.apiService.save(this.todo)
      .subscribe(
        data => {
          console.log(this.todo);
          if (this.todo.id != null && this.todo.id != '') {
            this.message = "Successfully Updated Todo";
          } else {
            this.message = "Successfully Created Todo";

          }
          this.getAllTodos();
        },
        error => {
          console.log("failed!!!!!");
        });
  }

  onRemove(id) {
    this.apiService.remove(id).subscribe((data: any[])=>{
      console.log(data);
      this.toDos = data;
      this.getAllTodos();
    })  ;
  }

  onReset() {
    this.submitted = false;
    this.todoForm.reset();
  }

}
