import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BaseBackComponent } from './backoffice/base-back/base-back.component';
import { BaseFrontComponent } from './frontoffice/base-front/base-front.component';
import { PersonalPageComponent } from './frontoffice/personal-page/personal-page.component';
import { AlertComponent } from './frontoffice/alert/alert.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './frontoffice/navbar/navbar.component';
import { FooterComponent } from './frontoffice/footer/footer.component';
import { ForumModule } from './Forum/forum.module';
import { AuthInterceptor } from './auth-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    BaseBackComponent,
    BaseFrontComponent,
    PersonalPageComponent,
    AlertComponent,
    NavbarComponent,
    FooterComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    ForumModule
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
