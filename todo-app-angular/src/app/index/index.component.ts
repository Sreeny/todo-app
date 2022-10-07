import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  errorMessage = "";
  login = {
    "userName": "",
    "password": ""

  }
  response = {
    "status": "",
    "message": ""
  }
  constructor(private apiService: ApiService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get formControls() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    this.apiService.login(this.login).subscribe((data: any) => {
      this.response = data;
      if (data.status == 'Success') {
        localStorage.setItem("loginMessage", "Welcome " + this.login.userName + "!!!");
        this.router.navigate(['/create']);
      } else {
        this.errorMessage = "Invalid Username or password";
      }

    });
  }

  onReset() {
    this.submitted = false;
    this.loginForm.reset();
  }

}
