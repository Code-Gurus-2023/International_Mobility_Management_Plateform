import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Accomodation } from '../models/Accomodation';
import { AccomodationServiceService } from '../services/accomodation-service.service';

@Component({
  selector: 'app-accomodation-offer-details',
  templateUrl: './accomodation-offer-details.component.html',
  styleUrls: ['./accomodation-offer-details.component.css']
})
export class AccomodationOfferDetailsComponent implements OnInit {

  idAcc:number;
  accomodation:Accomodation
  constructor(private activatedRoute:ActivatedRoute,private accomodationService:AccomodationServiceService) { }

  ngOnInit(): void {
    this.idAcc=this.activatedRoute.snapshot.params['id'];
    this.getAccomodationById();
  }
  getAccomodationById(){
    this.accomodationService.getAccomodationById(this.idAcc).subscribe(data=>{
      this.accomodation=data;
      console.log(this.accomodation);
    },
    erreur=>console.log("Erreur")
    )
  }

}
