import { Component, OnInit } from '@angular/core';
import { Alert } from '../models/alert';
import { AlertService } from '../service/alert.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-alerts',
  templateUrl: './add-alerts.component.html',
  styleUrls: ['./add-alerts.component.scss']
})
export class AddAlertsComponent implements OnInit {
  alert: Alert=new Alert();
  constructor(private alertservice:AlertService, private router:Router ) { }

  ngOnInit(): void {
  }

  saveAlert(){
    this.alertservice.creataAlert(this.alert).subscribe(alerts => {
      console.log(alerts);
    },
    
    error => {
      console.log(error);
    });
  }

  onSubmit() {
    console.log(this.alert);
    this.saveAlert();
    this.router.navigate(['/alert']);
  }


}
