import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Friends, FriendsService } from '../service/friends.service';
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
  log = true
  username : any


  firend : Friends

  constructor(
    private http : HttpClient,
    private profileService : ProfileService,
    private router : Router,
    private friendService : FriendsService
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

  addFriend(id : number){
    console.log("Zapracivanje prijatelja")
    // console.log("First name je: " + this.p.firstName)

    // this.firend = new Friends(id)
    // this.friendService.executePostFriends(this.firend).subscribe(
    //   data => {
    //     console.log("data je :" + data)
    //   }
    // )

    this.profileService.executeGetOneProfile(id).subscribe(
      data => {
        this.log = false
        this.firend = new Friends(id, data.username, data.firstName, data.lastName, data.email, data.dateOfBirth, data.phone, data.privacy, data.picByte, this.username)
        this.friendService.executePostFriends(this.firend).subscribe(
          response => {
            console.log("response je : " + response)
          }
        )
      }
    )
    
  }


}
