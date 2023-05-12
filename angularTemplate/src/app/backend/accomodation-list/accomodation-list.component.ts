import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Accomodation } from 'src/app/owner/models/Accomodation';
import { AccomodationService } from '../services/accomodation.service';

@Component({
  selector: 'app-accomodation-list',
  templateUrl: './accomodation-list.component.html',
  styleUrls: ['./accomodation-list.component.css']
})
export class AccomodationListComponent implements OnInit {


    accomodation:Accomodation[]
    acc:Accomodation
    key:any=''
    country:any
    reverse:boolean=false
    idAcc:number
    id:number

  constructor(private accomodationService:AccomodationService,private router:Router) { }

  ngOnInit(): void {
    this.getAccomodation()
  }
  getAccomodation(){
    this.accomodationService.getAllAccomodation().subscribe(data=>{
      this.accomodation=data;
      console.log(data)
    })
}
toAccomodation(){
  this.router.navigate(['dashboard/addAccomodation']);
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

sort(key:any){
  this.key =key;
  this.reverse=!this.reverse;
}
    getById(id:number){
      this.idAcc=id;
      console.log(this.idAcc);
      this.accomodationService.getAccomodationById(this.idAcc).subscribe(data=>{
        this.acc=data;
        console.log("GetById")
        console.log("this.acc"+this.acc)
      }
    )
    }


}

