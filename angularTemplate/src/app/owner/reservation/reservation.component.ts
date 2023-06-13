import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {


    title ='MultiStepForm'
    steps:any=1
  constructor() { }

  ngOnInit(): void {
  }



    step2() {
        this.steps+=1
    }
    backStep1() {
      this.steps-=1
      }
      step3() {
        this.steps+=1
        }
        backStep2() {
          this.steps-=1
          }

}
