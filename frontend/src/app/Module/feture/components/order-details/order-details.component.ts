import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {

  orders = [1, 1, 1] ;
  steps=[
    {id:0, title: 'PLACED', isCompleted: true },
    {id:1, title: 'CONFIRMED', isCompleted: false },
    {id:2, title: 'SHIPPED', isCompleted: false},
    {id:3, title: 'Delivered', isCompleted: false},
  ];
  constructor() { }

  ngOnInit(): void {
  }

}
