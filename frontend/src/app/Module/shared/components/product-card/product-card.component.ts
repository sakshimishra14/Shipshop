import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent implements OnInit {

  @Input() product:any ;

  products = [1, 1, 1, 1, 1];
  constructor(
    private router:Router
  ) { }

  ngOnInit(): void {
  }
  
  navigate(path:string){
    this.router.navigate([path]);
  }

}
