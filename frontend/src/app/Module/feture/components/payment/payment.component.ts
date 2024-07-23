import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState } from 'src/app/Models/AppState';
import { getOrderByIdRequest } from 'src/app/state/Order/Actions';
import { OrderService } from 'src/app/state/Order/order.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {
  order$!: Observable<any>;
  cart = [1,1,1];
  products = [1, 1, 1];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private store: Store<AppState>,
    private orderService:OrderService,
  ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.orderService.getOrderById(id)
        this.store.dispatch(getOrderByIdRequest({ orderId: id }));
      }
      this.order$ = this.store.select((store) => store.order.order);
      this.order$.subscribe((order) => console.log('order from store - ', order));

  }

  redirectToPaymentPage(){

  }

}
