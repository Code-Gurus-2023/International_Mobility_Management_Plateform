import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddClaimComponent } from '../add-claim/add-claim.component';
import { ListClaimComponent } from '../list-claim/list-claim.component';
import { ClaimsComponent } from '../claims/claims.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { FormsModule } from '@angular/forms';
import { UpdateClaimComponent } from '../update-claim/update-claim.component';



@NgModule({
  declarations: [
    AddClaimComponent,
    ListClaimComponent,
    ClaimsComponent,
    UpdateClaimComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  exports:[
    ClaimsComponent
  ]
})
export class ClaimModule { }
