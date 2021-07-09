import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileModel, ProfileService } from '../service/profile.service';

@Component({
  selector: 'app-follow',
  templateUrl: './follow.component.html',
  styleUrls: ['./follow.component.css']
})
export class FollowComponent implements OnInit {

  profiles : Array<ProfileModel>

  profile : any

  image : any
  u : any
  log : any
  username : any

  constructor(
    private profileService : ProfileService
  ) { }

  ngOnInit() {
    this.profiles = new Array<ProfileModel>()
    this.username = sessionStorage.getItem('logUser')
    this.profileService.executeGetProfiles().subscribe(
      data => {
        this.image = 'data:image/jpeg;base64,'
          for(this.u in data) {
            if(this.username !== data[this.u].username){
              this.profile = data[this.u]
              this.profiles.push(this.profile)
            }
            else{
              console.log("Usernmae je isti")
            }
          }
      }
    )
  }


}
