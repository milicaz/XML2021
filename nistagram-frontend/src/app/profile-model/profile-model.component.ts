import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileModel, ProfileService } from '../service/profile.service';


@Component({
  selector: 'app-profile-model',
  templateUrl: './profile-model.component.html',
  styleUrls: ['./profile-model.component.css']
})

export class ProfileModelComponent implements OnInit {

  constructor( 
    private profileService: ProfileService,
    private router : Router,
    private activatedRoute : ActivatedRoute
    ) { }

  id : any
  username: any
  firstName: any
  lastName : any
  email: any
  dateOfBirth: any
  phone: any
  privacy: any
  picByte: any
  retrievedImage: any

  action: string

  profiles : Array<ProfileModel>

  profile : Array<ProfileModel>

  selectedProfile : ProfileModel

  ngOnInit() {

    // this.profiles = new Array<ProfileModel>()


    // this.profileService.executeGetProfiles().subscribe(
    //   data => {
    //     this.profiles = data
    //   }
    // )

    this.username = sessionStorage.getItem('logUser')
    this.profileService.executeGetProfile(this.username).subscribe(
      data => {
        this.id = data.id
        this.firstName = data.firstName
        this.lastName = data.lastName
        this.email = data.email
        this.dateOfBirth = data.dateOfBirth
        this.phone = data.phone
        this.picByte = data.picByte

        this.retrievedImage = 'data:image/jpeg;base64,' + this.picByte
      }
    )

    this.activatedRoute.queryParams.subscribe(
      (params) => {
        this.action = params['action'];
      }
    );
  }

  updateProfile() {
    this.selectedProfile = new ProfileModel()
    this.router.navigate(['profiles'], { queryParams: { action: 'update' } });
  }

  refreshData() {
    this.profileService.executeGetProfile(this.username).subscribe(
      data => {
        this.id = data.id
        this.firstName = data.firstName
        this.lastName = data.lastName
        this.email = data.email
        this.dateOfBirth = data.dateOfBirth
        this.phone = data.phone
      })
  }

}
