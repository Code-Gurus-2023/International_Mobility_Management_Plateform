import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Accomodation } from 'src/app/owner/models/Accomodation';
import { AccomodationService } from '../services/accomodation.service';

@Component({
  selector: 'app-update-accomodation',
  templateUrl: './update-accomodation.component.html',
  styleUrls: ['./update-accomodation.component.css']
})
export class UpdateAccomodationComponent implements OnInit {

addAccomodation() {
throw new Error('Method not implemented.');
}

    @Input() id:number
    @Input() accomodation:Accomodation
    @ViewChild('closebutton') closebutton:any;
  constructor(private accomodationService:AccomodationService) { }

  ngOnInit(): void {
    this.getAccomodation();
  }
  updateAccomodation(){
    this.accomodationService.updateAccomodation(this.id,this.accomodation).subscribe(data=>{
      console.log("Updated");
      this.closebutton.nativeElement.click();
      window.location.reload();
    },
    erreur=>console.log("erreur")
    )

}

getAccomodation(){
  this.accomodationService.getAllAccomodation().subscribe(data=>{
    this.accomodation=data;
    console.log(data)
  })
}



}
