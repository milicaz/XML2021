import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { FollowComponent } from "./follow/follow.component";
import { FriendsComponent } from "./friends/friends.component";
import { InfoUpdateComponent } from "./info-update/info-update.component";
import { LoginComponent } from "./login/login.component";
import { LogoutComponent } from "./logout/logout.component";
import { PostComponent } from "./post/post.component";
import { ProfileModelComponent } from "./profile-model/profile-model.component";
import { ProfileComponent } from "./profile/profile.component";
import { RegistrationComponent } from "./registration/registration.component";
import { WelcomeComponent } from "./welcome/welcome.component";

const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "login", component: LoginComponent },
  { path: "welcome", component: WelcomeComponent },
  { path: "logout", component: LogoutComponent },
  { path: "registration", component: RegistrationComponent },
  { path: "profile", component: ProfileComponent },
  { path: "friends", component: FriendsComponent },
  { path: "posts", component: PostComponent },
  { path: "retrievedImage", component: ProfileComponent},
  { path: "update", component: InfoUpdateComponent},
  { path: "follow", component: FollowComponent},
  { path: "profiles", component: ProfileModelComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
