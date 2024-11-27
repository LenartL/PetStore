import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../service/order.service";

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent implements OnInit {
  postIds!: number[]

  constructor(private readonly _orderService: OrderService) {
  }

  ngOnInit(): void {
    this._orderService.getOrderIds$.subscribe({
        next: value => this.postIds = value,
        error: err => console.error(err)
      }
    );
  }

}
