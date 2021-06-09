import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { LogoutComponent } from '../logout/logout.component';
import { LoginDataService } from '../service/login-data.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  // isUserLoggedIn : boolean = false

  constructor(private loginService : LoginDataService) {
   }

  ngOnInit() {
    // console.log("User je: " + this.isUserLoggedIn)
    // this.isUserLoggedIn = this.loginService.isUserLoggedIn()
    //console.log("User je: " + this.isUserLoggedIn)
  }

}
