import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Claim } from '../models/claim';

@Injectable({
  providedIn: 'root'
})
export class ClaimService {

  constructor(public httpClient: HttpClient) { }

  getClaims():Observable<Claim[]>{
    return this.httpClient.get<Claim[]>(`http://localhost:8088/espritmobility/api/Claim/myClaims`);
  }

  creataClaim(claim:Claim):Observable<Object>{
    return this.httpClient.post(`http://localhost:8088/espritmobility/api/Claim/creat`, claim);
  }

   updateClaim(id: number,claim: Claim): Observable<Object> {
     return this.httpClient.put(`http://localhost:8088/espritmobility/api/Claim/update/${id}`, claim);
   }

   getClaimById(id: number): Observable<Claim> { 
    return this.httpClient.get<Claim>(`http://localhost:8088/espritmobility/api/Claim/clm/${id}`);
  }
}
