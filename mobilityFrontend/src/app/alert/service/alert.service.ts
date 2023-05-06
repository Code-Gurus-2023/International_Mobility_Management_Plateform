import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";
import { Alert } from '../models/alert';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  public baseUrl="http://localhost:8088/espritmobility/api/alert/alerts";
  constructor(public httpClient: HttpClient) { }

  getAlerts():Observable<Alert[]>{
    return this.httpClient.get<Alert[]>(`http://localhost:8088/espritmobility/api/alert/alerts`);
  }

  creataAlert(alert:Alert):Observable<Object>{
    return this.httpClient.post(`http://localhost:8088/espritmobility/api/alert`, alert);
  }

  deleteAlert(id: number): Observable<Object> {
    return this.httpClient.delete(`http://localhost:8088/espritmobility/api/alert/delete/${id}`);
  }

}
