import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {OrderDetailsModel} from "../component/order-list/model/order-details.model";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly BASE_URL = `${environment.url}/store`;
  getOrderIds$ = this._http.get<number[]>(`${this.BASE_URL}/order`);

  constructor(private readonly _http: HttpClient) {}

  getOrderDetails$(id: number) {
    return this._http.get<OrderDetailsModel>(`${this.BASE_URL}/order/${id}`);
  }

  deleteOrder$(id: number) {
    return this._http.delete<void>(`${this.BASE_URL}/order/${id}`);
  }
}
