import {Component, OnInit} from '@angular/core';
import {HeaderComponent, HeaderNavigation} from "../../../../shared/header/header.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    HeaderComponent,
    RouterOutlet
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit{

  navigation!: HeaderNavigation[];

  ngOnInit(): void {
    this._initNavigation()
  }

  private _initNavigation() {
    this.navigation = [
      {
        linkName: 'Orders',
        url: ['order-list'],
        title: 'List of orders'
      },
    ]
  }
}
