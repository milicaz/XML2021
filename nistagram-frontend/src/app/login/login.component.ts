import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { forEach } from '@angular/router/src/utils/collection';
import { AuthService } from '../service/auth.service';
import { LoginDataService } from '../service/login-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''

  constructor(
    private router : Router,
    private service: LoginDataService,
    ) { }

  ngOnInit() {
  }

  handleLogin() {
    console.log("Username je: " + this.username)
    console.log("Password je: " + this.password)
    // this.router.navigate(['welcome'])
    console.log(this.service.executeLoginService())
    this.service.executeLoginService().subscribe(
      response => this.service.hadleSuccessfulResponse(response, this.username, this.password)
    )
  }

  // hadleSuccessfulResponse(response) {
  //   var r = response.length
  //   console.log("Varijabla r je: " + r)
  //   for(r in response){
  //     if(this.username === response[r].username && this.password === response[r].password){
  //       sessionStorage.setItem('logUser', this.username)
  //       this.router.navigate(['welcome'])
  //     } else {
  //       console.log("Niste uneli dobar username ili password") 
  //     }
  //   }
  // }

  // isUserLoggedIn() {
  //   let user = sessionStorage.getItem('logUser')
  //   return !(user === null)
  // }

}
