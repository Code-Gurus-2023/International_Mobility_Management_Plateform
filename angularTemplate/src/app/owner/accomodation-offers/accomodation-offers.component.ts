import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Accomodation } from '../models/Accomodation';
import { AccomodationServiceService } from '../services/accomodation-service.service';

@Component({
  selector: 'app-accomodation-offers',
  templateUrl: './accomodation-offers.component.html',
  styleUrls: ['./accomodation-offers.component.css']
})
export class AccomodationOffersComponent implements OnInit {

  accomodation:Accomodation[]
  offset=0;
    pageSize=3
    page=1
    pageSizes = [3,6,9];
    totalLength:any
    @Input() country:string
    reverse:boolean=false

  constructor(private accomodationService:AccomodationServiceService,private router:Router) { }

  ngOnInit(): void {
    this.getAccomodations()
  }



getAccomodations(): void {
  this.getRequestParams(this.offset,this.pageSize)
  this.accomodationService.pageAccomodation(this.offset,this.pageSize)
  .subscribe(
    data => {
      data=data.response
      console.log(data)
      this.accomodation = data.content;
      this.totalLength=data.totalElements
    },
    error => {
      console.log(error);
    });}
  detailOffer(idAcc:number){
    this.router.navigate(['/offerDetails',idAcc])
  }
  /************************************Fonction de passage entre les page**********************************************/
  handlePageChange(event: number): void {
    this.offset = event;
    this.getAccomodations();
  }
  /********************************************* Pour la pagination d'une page vers une autre*/
  getRequestParams(offset: number, pageSize: number) {

    if (offset) {
      this.offset = offset - 1;
    }
    if (pageSize) {
      this.pageSize = pageSize;
    }
  }


  /************************************************Affichage de nombre de produit par page:La liste déroulante********************************************/
  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getAccomodations();
  }
  Search(){
    if(this.country == "")
    {
      this.ngOnInit();
    }
    else
    {
      this.accomodation=this.accomodation.filter(res=>{
        return res.country.toLocaleLowerCase().match(this.country.toLocaleLowerCase());
      })}

    }


}
