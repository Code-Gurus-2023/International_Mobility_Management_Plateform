import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccomodationOfferDetailsComponent } from './accomodation-offer-details/accomodation-offer-details.component';
import { AccomodationOffersComponent } from './accomodation-offers/accomodation-offers.component';
import { AccomodationPagesComponent } from './accomodation-pages/accomodation-pages.component';
import { AccomodationUpdateComponent } from './accomodation-update/accomodation-update.component';
import { ReservationComponent } from './reservation/reservation.component';

const routes: Routes = [
  {
    path:'',
    component:AccomodationPagesComponent,
    children:[
      {path:'accomodationUpdate', component:AccomodationUpdateComponent},
      {path:'accomodationOffers',component:AccomodationOffersComponent},
      {path:'offerDetails/:id', component:AccomodationOfferDetailsComponent},
      {path:'reserveAccomodation', component:ReservationComponent}

    ]
  }



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OwnerRoutingModule { }
