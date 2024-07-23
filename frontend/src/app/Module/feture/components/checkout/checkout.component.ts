import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/Models/AppState';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  constructor(private store:Store<AppState>) { }

  ngOnInit(): void {
  }
  handleCreateOrder=()=>{

    // this.store.dispatch(createOrderRequest({}))
  }

}
