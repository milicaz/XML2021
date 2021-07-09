import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

export class Profile {
  constructor(
    public id: number,
    public username: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public dateOfBirth: Date,
    public phone: string,
    public privacy: boolean
  ){}
}

export class ProfileModel {
  public id: number;
  public username: string;
  public firstName: string;
  public lastName: string;
  public email: string;
  public dateOfBirth: Date;
  public phone: string;
  public privacy: boolean;
  public picByte: string;
  public retrievedImage: string;

  constructor(){}
}

export class User {
  constructor(
    public id: number,
    public username: string,
    public password: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public role: string
  ) { }
}

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(
    private http: HttpClient,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  executeProfileService(username) {
    return this.http.get<Profile>('http://localhost:8900/profile/get/profile/' + username)
  }

  executeUpdateProfile(username, profile) {
    return this.http.put<Profile>('http://localhost:8900/profile/update/profile/' + username, profile)
  }

  executeUserUpdate(username, user) {
    return this.http.put<User>('http://localhost:9100/auth/update/user/' + username, user)
  }

  executeGetProfiles() {
    return this.http.get<ProfileModel[]>('http://localhost:8900/profile/model/get/profiles')
  }

  executeGetProfile(username) {
    return this.http.get<ProfileModel>('http://localhost:8900/profile/model/get/profile/' + username)
  }

  executeUpdateProfileModel(username, profile) {
    return this.http.put<ProfileModel>('http://localhost:8900/profile/model/update/' + username, profile)
  }

  executeFindUsername(username) {
    return this.http.get<User>('http://localhost:9100/auth/user/' + username)
  }

  executeGetOneProfile(id) {
    return this.http.get<ProfileModel>('http://localhost:8900/profile/model/get/by/' + id)
  }

}
