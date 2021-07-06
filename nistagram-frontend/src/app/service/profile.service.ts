import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

export class Profile {
  constructor(
    public id: number,
    public username: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public dateOfBirth: Date,
    public phone: string
  ){}
}

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  executeProfileService(username) {
    return this.http.get<Profile>('http://localhost:8900/profile/get/profile/' + username)
  }

  executeUpdateProfile(username, profile) {
    return this.http.put<Profile>('http://localhost:8900/profile/update/profile/' + username, profile)
  }

}
