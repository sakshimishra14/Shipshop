import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FetureComponent } from './components/feture.component';
import { HomeComponent } from './components/home/home.component';
import { MainCarouselComponent } from './components/home/main-carousel/main-carousel.component';
import { ProductSliderComponent } from './components/home/product-slider/product-slider.component';
import { HomeProductCardComponent } from './components/home/home-product-card/home-product-card.component';
import { ProductsComponent } from './components/products/products.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatRadioModule} from '@angular/material/radio';
import { SharedModule } from '../shared/shared.module';
import { CartComponent } from './components/cart/cart.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { PaymentComponent } from './components/payment/payment.component';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';
import { OrderComponent } from './components/order/order.component';
import { OrderDetailsComponent } from './components/order-details/order-details.component';
import { ProductReviewCardComponent } from './components/product-details/product-review-card/product-review-card.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { AddressFormComponent } from './components/checkout/address-form/address-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import { OrderCardComponent } from './components/order/order-card/order-card.component';
import {MatPaginatorModule} from '@angular/material/paginator';




@NgModule({
  declarations: [
    FetureComponent,
    HomeComponent,
    MainCarouselComponent,
    ProductSliderComponent,
    HomeProductCardComponent,
    ProductsComponent,
    CartComponent,
    ProductDetailsComponent,
    CheckoutComponent,
    PaymentComponent,
    PaymentSuccessComponent,
    OrderComponent,
    OrderDetailsComponent,
    ProductReviewCardComponent,
    AddressFormComponent,
    OrderCardComponent,
    
    
  ],
  imports: [
    CommonModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatRadioModule,
    SharedModule,
    FormsModule,
    MatProgressBarModule,
    ReactiveFormsModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatPaginatorModule

  ],
  exports:[
    FetureComponent,
    HomeComponent,
    ProductsComponent
  ]
})
export class FetureModule { }
