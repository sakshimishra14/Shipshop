import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedComponent } from './components/shared.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavContentComponent } from './components/navbar/nav-content/nav-content.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { FetureModule } from '../feture/feture.module';
import { ProductCardComponent } from './components/product-card/product-card.component';
import { StarRatingComponent } from './components/star-rating/star-rating.component';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import {MatDividerModule} from '@angular/material/divider';
import { AddressCardComponent } from './components/address-card/address-card.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrderTrackerComponent } from './components/order-tracker/order-tracker.component';
import { MatDialogModule } from '@angular/material/dialog';
import { CapitalizePipe } from 'src/app/Pips/capitalize-pipe';


@NgModule({
  declarations: [
    SharedComponent,
    NavbarComponent,
    FooterComponent,
    NavContentComponent,
    ProductCardComponent,
    StarRatingComponent,
    CartItemComponent,
    AddressCardComponent,
    OrderTrackerComponent,
    CapitalizePipe
  ],
  imports: [
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatDividerModule,
    MatFormFieldModule, 
    MatDialogModule,
   
  ],
  exports:[
    NavbarComponent,
    FooterComponent,
    NavContentComponent,
    ProductCardComponent,
    StarRatingComponent,
    CartItemComponent,
    MatDividerModule,
    MatIconModule,
    AddressCardComponent,
    OrderTrackerComponent
  ]
})
export class SharedModule { }
