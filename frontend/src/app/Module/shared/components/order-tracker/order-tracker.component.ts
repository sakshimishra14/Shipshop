import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-tracker',
  templateUrl: './order-tracker.component.html',
  styleUrls: ['./order-tracker.component.scss']
})
export class OrderTrackerComponent implements OnInit {

  @Input() activeStep: any
  @Input() steps:any
  
  constructor() { }

  ngOnInit(): void {
  }

}
