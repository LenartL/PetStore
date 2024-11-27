import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiKey$ = new BehaviorSubject(environment.apiKey);

  constructor() {
  }
}
