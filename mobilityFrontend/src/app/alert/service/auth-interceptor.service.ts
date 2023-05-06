import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthInterceptorService implements HttpInterceptor{

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler){
    const authToken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsYXl0aCIsImlhdCI6MTY4MzM4NTM4MSwiZXhwIjoxNjgzMzg4OTgxfQ.aDL8wqlITkL84CvQYVN0LbpSfxOMHai3cJ9WIrNq8wNBJtLaPfnRXLAazJ2azE6jEODl0KKMV1N_RIin1WEBOQ";
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
