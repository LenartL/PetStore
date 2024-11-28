import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {OrderDetailsModel} from "../model/order-details.model";
import {Operation} from "fast-json-patch/commonjs/core";
import {OrderFormModel} from "../model/order-form.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly BASE_URL = `${environment.url}/store`;
  getOrderIds$ = this._http.get<number[]>(`${this.BASE_URL}/order`);

  constructor(private readonly _http: HttpClient) {
  }

  getOrderDetails$(id: number): Observable<OrderDetailsModel> {
    return this._http.get<OrderDetailsModel>(`${this.BASE_URL}/order/${id}`);
  }

  deleteOrder$(id: number) {
    return this._http.delete<void>(`${this.BASE_URL}/order/${id}`);
  }

  postOrder$(order: OrderFormModel) {
    return this._http.post<OrderDetailsModel>(`${this.BASE_URL}/order`, order);
  }

  patchOrder$(id: number, patch: Operation[]) {
    const patchHeader = {'content-type': 'application/json-patch+json'}
    return this._http.patch<OrderDetailsModel>(`${this.BASE_URL}/order/${id}`, patch, {'headers': patchHeader});
  }
}
