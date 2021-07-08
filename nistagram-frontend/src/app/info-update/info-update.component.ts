import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginDataService } from '../service/login-data.service';
import { Profile, ProfileService, User } from '../service/profile.service';

@Component({
  selector: 'app-info-update',
  templateUrl: './info-update.component.html',
  styleUrls: ['./info-update.component.css']
})
export class InfoUpdateComponent implements OnInit {

  profile: Profile
  id: any
  username: any
  firstName: any
  lastName: any
  email: any
  dateOfBirth: any
  phone: any
  privacy: any
  password: any
  role: any

  user: User

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private profileService: ProfileService,
    private loginService: LoginDataService
  ) { }

  ngOnInit() {
    this.username = sessionStorage.getItem('logUser')
    //console.log("Username je: " + this.username)
    // this.profile = new Profile()
    this.profileService.executeProfileService(this.username).subscribe(
      response => {
        this.id = response.id,
          this.firstName = response.firstName,
          this.lastName = response.lastName,
          this.email = response.email,
          this.dateOfBirth = response.dateOfBirth,
          this.phone = response.phone,
          this.privacy = response.privacy
      }
    )
  }

  update() {
    this.profile = new Profile(this.id, this.username, this.firstName, this.lastName, this.email,
      this.dateOfBirth, this.phone, this.privacy)
    // sessionStorage.setItem('logUser', this.username)
    console.log("Profile je: " + this.profile)
    // this.profile = new Profile(this.username, this.firstName, this.lastName, this.email,
    //   this.dateOfBirth, this.phone)
    this.profileService.executeUpdateProfile(this.username, this.profile).subscribe(
      response => {
        console.log("response je: " + response.firstName)
        this.router.navigate(['profile'])
      }
    )

    this.loginService.executeFindUsername(this.username).subscribe(
      d => {
        this.password = d.password
        this.role = d.role
        this.user = new User(this.id, this.username, this.password,
          this.firstName, this.lastName, this.email, this.role)

        console.log("Novi password je: " + this.password)
        console.log("Nova uloga je: " + this.role)

        this.profileService.executeUserUpdate(this.username, this.user).subscribe(
          data => {
            console.log("data password je :" + data.password)
          }
        )
      }
    )
  }

}
