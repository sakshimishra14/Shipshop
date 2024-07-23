import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {
  isLoggedIn=true;

  constructor() { }

  ngOnInit(): void {
  }

  changeTemplate=()=>{
    this.isLoggedIn=!this.isLoggedIn;
  }

}
