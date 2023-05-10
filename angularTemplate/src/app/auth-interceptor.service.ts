import { HttpHandler, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor {

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYW1leiIsImlhdCI6MTY4MzY3MDU5MywiZXhwIjoxNjgzNjc0MTkzfQ.PMGd9IfNPyRkY2nptjLs790meG-Zo8IBt8CQPhBeCzFD7cdmGKd8A5MdJncmcUrzVFqe-oDtkpk992U4r2djOg";
    if (authToken) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${ authToken }`)
      });
      return next.handle(authReq);
    } else {
      return next.handle(req);
    }
  }
}
