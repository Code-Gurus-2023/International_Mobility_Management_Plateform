import { Component, OnInit } from '@angular/core';
import { AlertService } from '../service/alert.service';
import { Alert } from '../models/alert';
import { Router } from '@angular/router';

@Component({
  selector: 'app-alert-list',
  templateUrl: './alert-list.component.html',
  styleUrls: ['./alert-list.component.scss']
})
export class AlertListComponent implements OnInit {
  
  constructor(public alertService:AlertService, private router: Router) { }

  ngOnInit(): void {
    this.getAlertList();
  }
  public alerts: Alert[];
  private getAlertList(){
    this.alertService.getAlerts().subscribe(data => {this.alerts = data;
    console.log(this.alerts)});
    console.log(this.alerts);
  }

  deleteAlert(id: number){
    this.alertService.deleteAlert(id).subscribe(data =>{
      this.getAlertList;
    });
    this.getAlertList;
    window.location.reload()
  }
}
