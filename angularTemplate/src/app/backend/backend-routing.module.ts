import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccomodationListComponent } from './accomodation-list/accomodation-list.component';
import { AddAccomodationComponent } from './add-accomodation/add-accomodation.component';
import { TemplateComponent } from './template/template.component';

const routes: Routes = [
  {
    path:'',
    component:TemplateComponent,
    children:[
     {path:'addAccomodation',component:AddAccomodationComponent},
     {path:'accomodationList',component:AccomodationListComponent}

    ]

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackendRoutingModule { }
