import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BaseBackComponent } from './backoffice/base-back/base-back.component';
import { BaseFrontComponent } from './frontoffice/base-front/base-front.component';
import { PersonalPageComponent } from './frontoffice/personal-page/personal-page.component';
import { AlertComponent } from './frontoffice/alert/alert.component';
import { InterceptorService } from './frontoffice/services/interceptor.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './frontoffice/navbar/navbar.component';
import { FooterComponent } from './frontoffice/footer/footer.component';
import { OwnerModule } from './owner/owner.module';
import { PortailComponent } from './frontoffice/portail/portail.component';
import { BackendModule } from './backend/backend.module';

@NgModule({
  declarations: [
    AppComponent,
    BaseBackComponent,
    BaseFrontComponent,
    PersonalPageComponent,
    AlertComponent,
    NavbarComponent,
    FooterComponent,
    PortailComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    OwnerModule,
    BackendModule,

  ],
  exports:[
      NavbarComponent,
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
