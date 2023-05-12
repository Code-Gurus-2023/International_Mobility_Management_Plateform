import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Alert } from 'src/app/models/alert';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  constructor(public httpClient: HttpClient) { }

  public baseUrl="http://localhost:8088/espritmobility/api/alert/alerts";
  
  getAlerts():Observable<Alert[]>{
    return this.httpClient.get<Alert[]>(`http://localhost:8088/espritmobility/api/alert/alerts`);
  }
}
