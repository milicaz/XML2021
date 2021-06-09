import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FriendsComponent } from './friends/friends.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ProfileComponent } from './profile/profile.component';
import { RegistrationComponent } from './registration/registration.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  {path : '', component : LoginComponent},
  {path : 'login', component : LoginComponent },
  {path : 'welcome', component : WelcomeComponent},
  {path : 'logout', component : LogoutComponent},
  {path : 'registration', component : RegistrationComponent},
  {path : 'profile', component : ProfileComponent},
  {path : 'friends', component : FriendsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
