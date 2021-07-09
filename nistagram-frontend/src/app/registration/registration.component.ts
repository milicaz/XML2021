import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDataService, Profile, User } from '../service/login-data.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  uname = ''
  username = ''
  password = ''
  firstName = ''
  lastName = ''
  email = ''
  role = 'user'

  user: User

  profile: Profile

  constructor(
    private userService: LoginDataService,
    private router: Router,
    private http: HttpClient
  ) { }

  ngOnInit() {

  }

  addUser() {
    this.user = new User(this.username, this.password, this.firstName, this.lastName, this.email, this.role)
    this.userService.executeRegisterService(this.user)
      .subscribe(
        data => {
          console.log(data)
          this.router.navigate(['login'])
        }
      )

    this.profile = new Profile(this.username, this.firstName, this.lastName, this.email, null, null)
    this.userService.executeAddProfile(this.profile).subscribe(
      response => {
        console.log(response)
      }
    )
  }

}
