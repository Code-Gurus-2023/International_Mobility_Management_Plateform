import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Accomodation } from 'src/app/owner/models/Accomodation';

@Injectable({
  providedIn: 'root'
})
export class AccomodationService {

  constructor(private http:HttpClient) { }

  getAllAccomodation():Observable<any>
  {
         return this.http.get('http://localhost:8088/espritmobility/accomodation/getAllAccomodation');
  }
  /********************************************************************************************/
  addAccomodation(accomodation:Accomodation):Observable<any>{
    return  this.http.post('http://localhost:8088/espritmobility/accomodation/addAccomodation',accomodation);
   }
  /********************************************************************************************/

   updateAccomodation(idAcc:number,accomodation:Accomodation):Observable<Object>{
    return this.http.put(`http://localhost:8088/espritmobility/accomodation/updateAccomodation/${idAcc}`,accomodation);
  }
  getAccomodationById(idAcc:number):Observable<any>{
    return this.http.get<Accomodation>(`http://localhost:8088/espritmobility/accomodation/getAccomodationById/${idAcc}`);
   }
}
