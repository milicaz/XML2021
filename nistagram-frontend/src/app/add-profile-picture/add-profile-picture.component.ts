import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Friends, FriendsService } from '../service/friends.service';
import { LoginDataService } from '../service/login-data.service';
import { ProfileModel, ProfileService, User } from '../service/profile.service';

@Component({
  selector: 'app-add-profile-picture',
  templateUrl: './add-profile-picture.component.html',
  styleUrls: ['./add-profile-picture.component.css']
})
export class AddProfilePictureComponent implements OnInit {

  @Input()
  profile : ProfileModel

  @Output()
  postAddedEvent = new EventEmitter();

  username : any
  password: any
  role: any
  user : User
  id: any

  friendId: any
  friendUname: any

  private selectedFile;
  imgURL: any;

  friend : Friends

  constructor(
    private profileService : ProfileService,
    private router : Router,
    private http : HttpClient,
    private loginService : LoginDataService,
    private friendService : FriendsService
  ) { }

  ngOnInit() {
    this.username = sessionStorage.getItem('logUser')
    console.log("Username je : " + this.username)
    this.profileService.executeGetProfile(this.username).subscribe(
      data => {
        this.profile = data
        this.username = this.profile.username
        console.log("This.username je u metodi: " + this.username)
        console.log("Privacy je : " + this.profile.privacy)
        console.log("Username je : " + this.profile.username)

      }
    )
  }

  public onFileChanged(event) {
    console.log(event);
    this.selectedFile = event.target.files[0];

    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };

  }

  saveProfile() {

    const uploadData = new FormData();
    uploadData.append('imageFile', this.selectedFile, this.selectedFile.name);
    this.selectedFile.imageName = this.selectedFile.name;

    // console.log("This.username je u metodi save: " + this.username)

    this.http.post('http://localhost:8900/profile/model/upload', uploadData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          // console.log("this.profile.username je " + this.profile.username)
          // sessionStorage.setItem('logUser', this.profile.username)
          this.profileService.executeUpdateProfileModel(this.username, this.profile).subscribe(
            (profile) => {
              this.postAddedEvent.emit();
              this.router.navigate(['profiles']);
            }
          );
          console.log('Image uploaded successfully');
        } else {
          console.log('Image not uploaded successfully');
        }
      }
      )

      this.profileService.executeFindUsername(this.username).subscribe(
        data => {
          this.id = data.id
          this.password = data.password
          this.role = data.role
          this.user = new User(this.id, this.username, this.password, this.profile.firstName, this.profile.lastName, this.profile.email, this.role)
          this.profileService.executeUserUpdate(this.username, this.user).subscribe(
            d => {
              console.log("D je: " + d)
            }
          )

          this.friendService.executeFindByUsername(this.username).subscribe(
            d => {
              this.friendId = d.id
              this.friendUname = d.friendUname
              this.friend = new Friends(this.friendId, this.username, this.profile.firstName, this.profile.lastName, 
                this.profile.email, this.profile.dateOfBirth, this.profile.phone, this.profile.privacy, this.profile.picByte, this.friendUname)
                console.log("PicByte profila je: " + this.profile.picByte)
              console.log("Id je :" + this.friend.id)
              console.log("Username je : " + this.friend.username)
              this.friendService.executeUpdateFriends(this.friend.username, this.friend).subscribe(
                r => {
                  console.log("updated friend je :" + r)
                  this.friendService.executeFindByUsername(this.friend.username).subscribe(
                    res => {
                      console.log("Ime je: " + res.firstName)
                      console.log("PicByte prijatelja je :" + res.picByte)
                    }
                  )
                }
             )
            }
          )



        }
      )

      // sessionStorage.setItem('logUser', this.profile.username)
      // console.log("this.username je: " + this.username)
      // this.ngOnInit()

    // console.log("Profil je : " + this.profile.email)
    // this.profileService.executeUpdateProfileModel(this.username, this.profile).subscribe()
    // this.router.navigate['profiles']
  }

}
