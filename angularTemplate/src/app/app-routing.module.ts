import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseBackComponent } from './backoffice/base-back/base-back.component';
import { PersonalPageComponent } from './frontoffice/personal-page/personal-page.component';
import { BaseFrontComponent } from './frontoffice/base-front/base-front.component';
import { AlertComponent } from './frontoffice/alert/alert.component';

const routes: Routes = [
  {path: 'home',component:BaseFrontComponent},


  {
    path:'owner',
    loadChildren: ()=>
    import('./owner/owner.module').then((o) => o.OwnerModule),
  },
   {
    path:'dashboard',
    loadChildren: ()=>
    import('./backend/backend.module').then((b) => b.BackendModule),
  },
  {path:'', redirectTo:'home',pathMatch:'full'},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
