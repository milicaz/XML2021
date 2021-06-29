import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile, ProfileService } from '../service/profile.service';

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

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private profileService: ProfileService
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
          this.phone = response.phone
      }
    )
  }

  update() {
    this.profile = new Profile(this.id, this.username, this.firstName, this.lastName, this.email,
      this.dateOfBirth, this.phone)
    // sessionStorage.setItem('logUser', this.username)
    console.log("Profile je: " + this.profile)
    // this.profile = new Profile(this.username, this.firstName, this.lastName, this.email,
    //   this.dateOfBirth, this.phone)
    this.profileService.executeUpdateProfile(this.username, this.profile).subscribe(
      response => {
        console.log("response je: " + response.firstName)
        this.router.navigate(['profile'])
        this
      }
    )
  }

}
