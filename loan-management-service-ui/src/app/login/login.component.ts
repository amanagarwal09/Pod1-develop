import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../classes/user';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username:string;
  password:string;
  msg:string;

  constructor(private authService:AuthService,private user:User) {
    
   }

  ngOnInit(): void {
  }

  validate(){
    this.user.username=this.username;
    this.user.password=this.password;
    this.msg = "";


    if((this.password === undefined || this.password == "") && (this.username === undefined || this.username == "")){
      this.msg = 'Username & Password is required';
      return false;
    }else if(this.username == undefined || this.username == ""){
      this.msg= 'Username is required';
      return false;
    }else if(this.password == undefined || this.password == ""){
      this.msg = 'Password is required';
      return false;
    }else if(this.authService.handleLogin(this.user)==false){
      this.msg="Incorrect Username/Password";
    }

  }
  
  

}
