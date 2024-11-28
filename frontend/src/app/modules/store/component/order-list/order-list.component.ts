import {Component, inject, OnInit} from '@angular/core';
import {OrderService} from "../../service/order.service";
import {Router} from "@angular/router";
import {AsyncPipe, DatePipe} from "@angular/common";
import {AuthService} from "../../../auth/service/auth.service";
import {NgbCalendar, NgbDate, NgbDatepicker} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [
    AsyncPipe,
    DatePipe,
    NgbDatepicker
  ],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent implements OnInit {
  postIds!: number[]
  shipDate?: Date
  calendar = inject(NgbCalendar);

  hoveredDate: NgbDate | null = null;
  fromDate?: NgbDate | null;
  toDate?: NgbDate | null;

  constructor(private readonly _orderService: OrderService,
              private readonly _router: Router,
              private readonly _authService: AuthService) {
  }

  ngOnInit(): void {
    this.getOrderIds();
  }

  getShipmentDate(id: number) {
    this._orderService.getOrderDetails$(id).subscribe(
      resp => this.shipDate = resp.shipDate
    )
  }

  getOrderIds(ngbDateFrom?: NgbDate | null, ngbDateTo?: NgbDate | null) {
    let from: Date | undefined | null;
    let to: Date | undefined | null;
    if (ngbDateFrom) {
      from = new Date(ngbDateFrom?.year, ngbDateFrom?.month - 1, ngbDateFrom?.day);
    }
    if (ngbDateTo) {
      to = new Date(ngbDateTo?.year, ngbDateTo?.month - 1, ngbDateTo?.day + 1);
    }

    this._orderService.getOrderIds$(from, to).subscribe({
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
        next: () => this.getOrderIds()
      }
    )
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isDatepickerHovered(date: NgbDate) {
    return (
      this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate)
    );
  }

  isDatepickerInside(date: NgbDate) {
    return this.toDate && date.after(this.fromDate) && date.before(this.toDate);
  }

  isDatepickerRange(date: NgbDate) {
    return (
      date.equals(this.fromDate) ||
      (this.toDate && date.equals(this.toDate)) ||
      this.isDatepickerInside(date) ||
      this.isDatepickerHovered(date)
    );
  }

  getApiKey() {
    return this._authService.apiKey$.getValue();
  }

  setApiKey(event: Event) {
    this._authService.apiKey$.next((event.target as HTMLInputElement).value)
  }

  clearCalendarAndGetOrderIds() {
    this.fromDate = null;
    this.toDate = null
    this.getOrderIds();
  }
}
