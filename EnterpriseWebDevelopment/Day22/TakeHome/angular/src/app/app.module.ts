import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CyclesComponent } from './cycles/cycles.component';
import { BorrowedCyclesComponent } from './borrowed-cycles/borrowed-cycles.component';
import { AddCyclesComponent } from './add-cycles/add-cycles.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { CartComponent } from './cart/cart.component';
import { RentalRecordComponent } from './rental-record/rental-record.component';
import { NavbarComponent } from './navbar/navbar.component'; 
import { HttpInterceptorService } from './HttpInterceptorService';
import { AuthModule } from '@auth0/auth0-angular';
import { AuthButtonComponent } from './auth-button/auth-button.component';
import { UserProfileComponent } from './user-profile/user-profile.component';


@NgModule({
  declarations: [
    AppComponent,
    CyclesComponent,
    BorrowedCyclesComponent,
    AddCyclesComponent,
    LoginFormComponent,
    CartComponent,
    RentalRecordComponent,
    NavbarComponent,
    AuthButtonComponent,
    UserProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AuthModule.forRoot({
      domain: 'dev-lueuqmsmhbb3f8nq.us.auth0.com',
      clientId: 'T4xPWZxRjGpx7T0EXkAUr82FZLhIWGsg',
      authorizationParams: {
        redirect_uri: window.location.origin + "/cycles"
      }
    }),  
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
