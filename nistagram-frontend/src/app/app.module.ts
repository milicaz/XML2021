import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { DatePipe } from "@angular/common";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { LoginComponent } from "./login/login.component";
import { WelcomeComponent } from "./welcome/welcome.component";
import { MenuComponent } from "./menu/menu.component";
import { LogoutComponent } from "./logout/logout.component";
import { HttpClientModule } from "@angular/common/http";
import { RegistrationComponent } from "./registration/registration.component";
import { ProfileComponent } from "./profile/profile.component";
import { FriendsComponent } from "./friends/friends.component";
import { PostComponent } from "./post/post.component";
import { StoryComponent } from "./story/story.component";
import { InfoUpdateComponent } from "./info-update/info-update.component";
import { Ng2TelInputModule } from "ng2-tel-input";
import { PostCreateComponent } from "./post-create/post-create.component";

// import { IConfig, NgxMaskModule } from 'ngx-mask';

// const maskConfig: Partial<IConfig> = {
//   validation: false,
// };

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomeComponent,
    MenuComponent,
    LogoutComponent,
    RegistrationComponent,
    ProfileComponent,
    FriendsComponent,
    PostComponent,
    StoryComponent,
    InfoUpdateComponent,
    PostCreateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    Ng2TelInputModule,
    // NgxMaskModule.forRoot(maskConfig)
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
