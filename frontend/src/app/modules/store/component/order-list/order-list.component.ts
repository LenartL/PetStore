import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../service/order.service";
import {Router} from "@angular/router";
import {AsyncPipe, DatePipe} from "@angular/common";
import {AuthService} from "../../../auth/service/auth.service";

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [
    AsyncPipe,
    DatePipe
  ],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent implements OnInit {
  postIds!: number[]
  shipDate?: Date

  constructor(private readonly _orderService: OrderService,
              private readonly _router: Router,
              private readonly _authService: AuthService) {
  }

  ngOnInit(): void {
    this.initialize();
  }

  getShipmentDate(id: number) {
    setTimeout(()=>this._orderService.getOrderDetails$(id).subscribe(
      resp => this.shipDate = resp.shipDate
    ), 500)
  }

  initialize() {
    this._orderService.getOrderIds$.subscribe({
        next: value => this.postIds = value,
        error: err => console.error(err)
      }
    );
  }

  updateOrder(id: number) {
    this._router.navigate(['store/order/', id]).then();
  }

  deleteOrder(id: number) {
    this._orderService.deleteOrder$(id).subscribe(
      {
        next: () => this.initialize()
      }
    )
  }

  getApiKey() {
    return this._authService.apiKey$.getValue();
  }

  setApiKey(event: Event) {
    this._authService.apiKey$.next((event.target as HTMLInputElement).value)
  }
}
