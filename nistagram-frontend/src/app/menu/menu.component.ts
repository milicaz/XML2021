import { HttpClient, HttpEventType, HttpResponse } from "@angular/common/http";
import { Component, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { LoginComponent } from "../login/login.component";
import { LogoutComponent } from "../logout/logout.component";
import { LoginDataService } from "../service/login-data.service";

@Component({
  selector: "app-menu",
  templateUrl: "./menu.component.html",
  styleUrls: ["./menu.component.css"],
})
export class MenuComponent implements OnInit {
  // isUserLoggedIn : boolean = false
  username: any;
  selectedFile: File;
  progress = 0;
  message = "";
  imageName: any;
  retrievedImage: any;
  retrieveResponse: any;
  base64Data: any;
  
  constructor(
    private http: HttpClient,private loginService: LoginDataService,
    private router: Router
  ) {}

  ngOnInit() {
    // console.log("User je: " + this.isUserLoggedIn)
    // this.isUserLoggedIn = this.loginService.isUserLoggedIn()
    //console.log("User je: " + this.isUserLoggedIn)
  }

  selectFile(event) {
    this.selectedFile = event.target.files[0];
  }

  upload() {
    this.username = sessionStorage.getItem('logUser');
    this.progress = 0;

    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);

    this.http.post('http://localhost:8400/media/upload/' + this.username, uploadImageData, {observe: 'response'}).subscribe((response)=>{
      if(response.status === 200){
        this.message = 'Image uploaded successfully.';
  
        this.router.navigate(['posts'])
      } else {
        this.message = 'Image could not be uploaded.';
      }
    });

    

  }
}
