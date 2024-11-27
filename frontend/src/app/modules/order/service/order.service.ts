import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private readonly BASE_URL = `${environment.url}/store`;
  getOrderIds$ = this._http.get<number[]>(`${this.BASE_URL}/order`)

  constructor(private readonly _http: HttpClient) {}
}
