import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  logout():void{
    sessionStorage.removeItem('isLoggedIn');
    sessionStorage.removeItem('username');
    this.router.navigate(["/login"]);
    
  }
  username:string=sessionStorage.getItem('username');
 
}
