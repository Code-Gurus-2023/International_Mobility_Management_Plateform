import { HttpHandler, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor {

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYW1leiIsImlhdCI6MTY4MzczNDk5NiwiZXhwIjoxNjgzNzM4NTk2fQ.Bca3-B52OW4auwoGUYMJrb0n1qDoRhQ8163oVRN1ssuAX_X66IiaikK1mh1C87xLsY3CLP5sRIEX-3x2Ja7ElQ";
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
