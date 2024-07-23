import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/Models/AppState';

import { OrderService } from 'src/app/state/Order/order.service';

@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.scss']
})
export class AddressFormComponent implements OnInit {

  adresses = [1,1,1];
  myForm: FormGroup = this.formBuilder.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    streetAddress: ['', Validators.required],
    city: ['', Validators.required],
    state: ['', Validators.required],
    zipCode: ['', Validators.required],
    mobile: ['', Validators.required],
  });

  constructor(private formBuilder:FormBuilder,
    private router: Router,
    private store: Store<AppState>,
    private orderService:OrderService
    ) { }

  ngOnInit(): void {
  }
  handleCreateOrder(reqData:any){
    this.orderService.createOrder(reqData)
    // this.router.navigate(['/checkout/payment']);
    console.log('handle submit - : ', reqData);
  }

  handleSubmit = () => {
    const formValue = this.myForm.value;
    const reqData = formValue;
    this.handleCreateOrder(reqData)
    // this.store.dispatch(creqateOrderRequest({ reqData }));
    
  };

}
