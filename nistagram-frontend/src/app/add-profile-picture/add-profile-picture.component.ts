import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileModel, ProfileService } from '../service/profile.service';

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

  private selectedFile;
  imgURL: any;

  constructor(
    private profileService : ProfileService,
    private router : Router,
    private http : HttpClient
  ) { }

  ngOnInit() {
    this.username = sessionStorage.getItem('logUser')
    console.log("Username je : " + this.username)
    this.profileService.executeGetProfile(this.username).subscribe(
      data => {
        this.profile = data
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

    this.http.post('http://localhost:8900/profile/model/upload', uploadData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
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
      );

    // console.log("Profil je : " + this.profile.email)
    // this.profileService.executeUpdateProfileModel(this.username, this.profile).subscribe()
    // this.router.navigate['profiles']
  }

}
