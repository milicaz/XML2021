import { HttpClient, HttpEventType, HttpResponse } from "@angular/common/http";
import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { LoginComponent } from "../login/login.component";
import { LogoutComponent } from "../logout/logout.component";
import { LoginDataService } from "../service/login-data.service";

@Component({
  selector: "app-menu",
  templateUrl: "./menu.component.html",
  styleUrls: ["./menu.component.css"],
})
export class MenuComponent implements OnInit {
  // isUserLoggedIn : boolean = false
    
  constructor(
    private http: HttpClient, private loginService: LoginDataService,
    private router: Router
  ) {}

  ngOnInit() {
    // console.log("User je: " + this.isUserLoggedIn)
    // this.isUserLoggedIn = this.loginService.isUserLoggedIn()
    //console.log("User je: " + this.isUserLoggedIn)
  }

}
