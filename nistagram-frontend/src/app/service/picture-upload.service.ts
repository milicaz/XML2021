import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

export class Images {
  constructor(
    public name : string,
    public picByte : any
  ){}
}

@Injectable({
  providedIn: 'root'
})
export class PictureUploadService {

  images : Images

  constructor(
    private http : HttpClient,
    private router : Router
  ) { }

  executeMediaService(name){
    return this.http.get<Images>('http://localhost:8400/media/get/' + name)
  }

  executePictureUpload(username){
    return this.http.get<Images>('http://localhost:8400/media/get/image/' + username)
  }

}
