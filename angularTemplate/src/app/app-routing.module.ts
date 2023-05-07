import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseBackComponent } from './backoffice/base-back/base-back.component';
import { PersonalPageComponent } from './frontoffice/personal-page/personal-page.component';
import { BaseFrontComponent } from './frontoffice/base-front/base-front.component';
import { AlertComponent } from './frontoffice/alert/alert.component';

const routes: Routes = [
  {path: '',component: BaseFrontComponent},
  {path: 'dash',component: BaseBackComponent},
  {path: 'myaccount',component: PersonalPageComponent},
  {path: 'alerts',component: AlertComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
