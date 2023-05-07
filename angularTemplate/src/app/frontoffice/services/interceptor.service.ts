import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class InterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler){
    const authToken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsYXl0aCIsImlhdCI6MTY4MzQwOTAwNiwiZXhwIjoxNjgzNDEyNjA2fQ.UfrLMxUyqqrE4N_tqjC0TLxv2t7iRuYqSXOH9_T8GpJuQNEy90j5PtcDnjPoM6TdXIf3Nk7YP6KXRk2pCl102g";
  if (authToken) {
    const authReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${authToken}`)
    });
    return next.handle(authReq);
    } else {
      return next.handle(req);
    }
  }
}
