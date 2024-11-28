import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../service/order.service";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {compare} from 'fast-json-patch';
import {OrderDetailsModel} from "../../model/order-details.model";
import {firstValueFrom} from "rxjs";

@Component({
  selector: 'app-order-form',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './order-form.component.html',
  styleUrl: './order-form.component.css'
})
export class OrderFormComponent implements OnInit {

  private initialOrder?: OrderDetailsModel;
  form: FormGroup;
  orderId?: number;

  constructor(private readonly _router: Router,
              private readonly _activatedRoute: ActivatedRoute,
              private readonly _orderService: OrderService,
              private readonly _fb: FormBuilder) {
    this.form = this._fb.group({
      'petId': [null, [Validators.required, Validators.max(Number.MAX_VALUE)]],
      'quantity': [1, [Validators.required, Validators.min(1), Validators.max(Number.MAX_VALUE)]],
      'shipDate': [null, [Validators.required]],
      'status': ['placed', [Validators.required]],
      'complete': [false, [Validators.required]],
    });
  }

  ngOnInit() {
    this._activatedRoute.paramMap.subscribe(
      p => {
        this.orderId = Number(p.get('id'))
      }
    ).unsubscribe();

    if (this.orderId) {
      this.getOrderDetailsToForm(this.orderId);
    }
  }

  private getOrderDetailsToForm(id: number) {
    this._orderService.getOrderDetails$(id).subscribe(
      resp => {
        delete resp['id']
        this.initialOrder = resp;
        this.form.patchValue({
          petId: resp.petId,
          quantity: resp.quantity,
          shipDate: resp.shipDate,
          status: resp.status,
          complete: resp.complete
        })
      }
    )
  }

  save() {
    const formData: OrderDetailsModel = this.form.value
    if (this.initialOrder && this.orderId) {
      const patch = compare(this.initialOrder, formData);
      firstValueFrom(this._orderService.patchOrder$(this.orderId, patch))
        .then(resp => this._router.navigate([`store/order/${resp.id}`]));
    } else {
      firstValueFrom(this._orderService.postOrder$(formData))
        .then(resp => this._router.navigate([`store/order/${resp.id}`]));
    }
  }
}
