import { Component, OnInit } from '@angular/core';
import { gounsPage1 } from 'src/Data/Gouns/gouns';
import { mens_kurta } from 'src/Data/Men/men_kurta';
import { lehngacholiPage2 } from 'src/Data/Saree/lenghaCholiPage2';
import { productdata } from 'src/Data/productsData';
import { mensShoesPage1 } from 'src/Data/shoes';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  products:any[]=[]
  gouns:any
  saree:any
  men_kurta:any
  shoes:any
  constructor() { 
    this.products = productdata.slice(0, 5);
    this.gouns= gounsPage1.slice(0,5)
    this.saree=lehngacholiPage2.slice(0,5)
    this.men_kurta=mens_kurta.slice(0,5)
    this.shoes=mensShoesPage1.slice(0,5)
  }

  ngOnInit(): void {
  }

}
