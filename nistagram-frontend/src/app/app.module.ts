import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { MenuComponent } from './menu/menu.component';
import { LogoutComponent } from './logout/logout.component';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationComponent } from './registration/registration.component';
import { ProfileComponent } from './profile/profile.component';
import { FriendsComponent } from './friends/friends.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomeComponent,
    MenuComponent,
    LogoutComponent,
    RegistrationComponent,
    ProfileComponent,
    FriendsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
