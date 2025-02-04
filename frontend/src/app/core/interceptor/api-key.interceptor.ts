import {HttpInterceptorFn} from '@angular/common/http';
import {inject} from "@angular/core";
import {AuthService} from "../../modules/auth/service/auth.service";

export const apiKeyInterceptor: HttpInterceptorFn = (req, next) => {
  const apiKey = inject(AuthService).apiKey$.getValue();
  const apiHeader = 'x-api-key'

  const newReq = req.clone({
    headers: req.headers.set(apiHeader, apiKey)
  });

  return next(newReq);
};
