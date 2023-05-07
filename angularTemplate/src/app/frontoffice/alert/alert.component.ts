import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AlertService } from '../services/alert.service';
import { Alert } from 'src/app/models/alert';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {

  constructor(private alertServie: AlertService, private router: Router) { }

  ngOnInit(): void {
    this.getAlertList();
  }

  public alerts:Alert[];
  ancre:string;
  private getAlertList(){
    this.alertServie.getAlerts().subscribe(data => {this.alerts = data;
      console.log(this.alerts);
      this.ancre="dkgfj";
    })
  }

}
