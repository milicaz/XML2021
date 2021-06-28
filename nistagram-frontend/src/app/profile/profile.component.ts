import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { THROW_IF_NOT_FOUND } from '@angular/core/src/di/injector';
import { Router } from '@angular/router';
import { PictureUploadService } from '../service/picture-upload.service';
import { ProfileService } from '../service/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  imageName: any
  message: string
  retrieveResonse: any
  base64Data: any
  retrievedImage: any
  selectedFile: File

  username: any
  firstName: any
  lastName: any
  email: any
  dateOfBirth: any
  phone: any
  retResponse: any


  constructor(
    private httpClient: HttpClient,
    private pictureService : PictureUploadService,
    private router : Router,
    private profileService : ProfileService
    ) { }

  ngOnInit() {
    this.username = sessionStorage.getItem('logUser')
    // console.log("Username je: " + this.username)
    this.profileService.executeProfileService(this.username).subscribe(
      response => {
      this.firstName = response.firstName,
      this.lastName = response.lastName,
      this.email = response.email,
      this.dateOfBirth = response.dateOfBirth,
      this.phone = response.phone
    }
    )
  }

  //Gets called when the user selects an image

  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
  }

  //Gets called when the user clicks on submit to upload the image
  onUpload() {
    console.log(this.selectedFile);
    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    //Make a call to the Spring Boot Application to save the image
    this.httpClient.post('http://localhost:8400/media/upload', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );
  }
  //Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
    //Make a call to Sprinf Boot to get the Image Bytes.
    // this.httpClient.get(`http://localhost:8400/media/get/` + this.imageName)
    //   .subscribe(
    //     res => {
    //       this.retrieveResonse = res;
    //       this.base64Data = this.retrieveResonse.picByte;
    //       this.retrievedImage = 'this.imageName;base64,' + this.base64Data;
    //       // this.retrievedImage = this.base64Data;
    //     }
    //   );
    console.log("image name je " + this.imageName)
    this.pictureService.executeMediaService(this.imageName).subscribe(
      data => {
        // console.log("data je " + data.name)
        this.retrieveResonse = data
        this.base64Data = this.retrieveResonse.picByte
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data
        // this.retrievedImage = this.base64Data
        console.log("image " + this.retrievedImage)
      }
    )
  }

  share() {
    console.log("Selected file " + this.selectedFile);
    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    console.log("Upload image data " + this.selectedFile.name);
    //Make a call to the Spring Boot Application to save the image
    this.httpClient.post('http://localhost:8400/media/upload', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );

      var slika = this.selectedFile.name.split(".", 1);

      console.log("Slika je " + slika + "ddddd")

      console.log("image name je " + this.selectedFile.name.split(".", 0))
    this.pictureService.executeMediaService(this.selectedFile.name.split(".", 1)).subscribe(
      data => {
        // console.log("data je " + data.name)
        this.retrieveResonse = data
        this.base64Data = this.retrieveResonse.picByte
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data
        // this.retrievedImage = this.base64Data
        console.log("image " + this.retrievedImage)
      }
    )

  }

  change() {

    this.router.navigate(['update'])
  }

}
