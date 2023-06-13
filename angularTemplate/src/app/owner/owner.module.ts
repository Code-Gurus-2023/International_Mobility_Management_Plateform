import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OwnerRoutingModule } from './owner-routing.module';
import { AccomodationUpdateComponent } from './accomodation-update/accomodation-update.component';
import { AccomodationPagesComponent } from './accomodation-pages/accomodation-pages.component';
import { AccomodationOffersComponent } from './accomodation-offers/accomodation-offers.component';
import { NotesComponent } from './notes/notes.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { PaginationComponent } from './pagination/pagination.component';
import { AccomodationOfferDetailsComponent } from './accomodation-offer-details/accomodation-offer-details.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { OwnerNavbarComponent } from './owner-navbar/owner-navbar.component';
import { UpdateAccomodationComponent } from './update-accomodation/update-accomodation.component';
import { ReservationComponent } from './reservation/reservation.component';
import { PayementComponent } from './payement/payement.component';



@NgModule({
    declarations: [

        AccomodationUpdateComponent,
        AccomodationPagesComponent,
        AccomodationOffersComponent,
        NotesComponent,
        PaginationComponent,
        AccomodationOfferDetailsComponent,
        OwnerNavbarComponent,
        UpdateAccomodationComponent,
        ReservationComponent,
        PayementComponent,

    ],
    imports: [
        CommonModule,
        OwnerRoutingModule,
        FormsModule,
        HttpClientModule,
        NgxPaginationModule,
        ReactiveFormsModule,
    ],
    exports:[
    ],

})
export class OwnerModule { }
