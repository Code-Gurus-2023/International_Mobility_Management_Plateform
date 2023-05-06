import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertsComponent } from './alerts/alerts.component';
import { AddAlertsComponent } from './add-alerts/add-alerts.component';
import { AlertListComponent } from './alert-list/alert-list.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from '../app-routing.module';
import { FormsModule } from '@angular/forms';





@NgModule({
  declarations: [
    AlertsComponent,
    AddAlertsComponent,
    AlertListComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  exports:[
    AlertsComponent
  ]
})
export class AlertModule { }
