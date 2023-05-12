import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackendRoutingModule } from './backend-routing.module';
import { TemplateComponent } from './template/template.component';
import { AddAccomodationComponent } from './add-accomodation/add-accomodation.component';
import { AccomodationListComponent } from './accomodation-list/accomodation-list.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UpdateAccomodationComponent } from './update-accomodation/update-accomodation.component';


@NgModule({
  declarations: [
    TemplateComponent,
    AddAccomodationComponent,
    AccomodationListComponent,
    UpdateAccomodationComponent
  ],
  imports: [
    CommonModule,
    BackendRoutingModule,
    HttpClientModule,
    FormsModule,
  ]
})
export class BackendModule { }
