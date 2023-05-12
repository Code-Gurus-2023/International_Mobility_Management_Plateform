import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Accomodation } from '../models/Accomodation';

@Injectable({
  providedIn: 'root'
})
export class AccomodationServiceService {

  constructor(private http:HttpClient) { }

  getAllAccomodation():Observable<any>
  {
         return this.http.get('http://localhost:8088/espritmobility/accomodation/getAllAccomodation');
  }
  /********************************************************************************************/
  addAccomodation(accomodation:Accomodation):Observable<Object>{
    return  this.http.post('http://localhost:8088/espritmobility/accomodation/addAccomodation',accomodation);
   }
  /********************************************************************************************/

   getAccomodationById(idAcc:number):Observable<any>{
    return this.http.get<Accomodation>(`http://localhost:8088/espritmobility/accomodation/getAccomodationById/${idAcc}`);
   }
  /********************************************************************************************/

   pageAccomodation(offset:number,pageSize:number):Observable<any>
   {
     return this.http.get<any>(`http://localhost:8088/espritmobility/accomodation/pagination/${offset}/${pageSize}`);
   }
}
