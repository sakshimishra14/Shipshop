import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './components/admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProductsComponent } from './components/products/products.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { CustomersComponent } from './components/customers/customers.component';
import { OrderTableComponent } from './components/order-table/order-table.component';


@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    ProductsComponent,
    AddProductComponent,
    CustomersComponent,
    OrderTableComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
