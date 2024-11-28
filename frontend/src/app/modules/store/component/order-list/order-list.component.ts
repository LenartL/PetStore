import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../service/order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent implements OnInit {
  postIds!: number[]

  constructor(private readonly _orderService: OrderService,
              private readonly _router: Router) {
  }

  ngOnInit(): void {
    this.initialize();
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
}
