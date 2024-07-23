import { Component, Input, OnInit } from '@angular/core';
import { navigation } from './nav-content';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-content',
  templateUrl: './nav-content.component.html',
  styleUrls: ['./nav-content.component.scss']
})
export class NavContentComponent implements OnInit {

  constructor(
    private router:Router,
  ) { }
  category:any;
  @Input() selectedSection:any;
  ngOnInit(): void {
    this.category = navigation
  }

  
  navigateToProducts=(path:String)=>{
    this.router.navigate([path])
    
  }

}
