import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

export class Friends {
  constructor(
    public id : number,
    public username : string,
    public firstName : string,
    public lastName : string,
    public email : string,
    public dateOfBirth : Date,
    public phone : string,
    public privacy : boolean,
    public picByte : string,
    public friendUname : string
  ) {}
}

@Injectable({
  providedIn: 'root'
})

export class FriendsService {

  friend : Friends

  constructor(
    private router : Router,
    private http : HttpClient
  ) { }


  executePostFriends(friend){
    return this.http.post<Friends>('http://localhost:8800/friends/add', friend)
  }

  getFriends(username){
    return this.http.get<Friends>(`http://localhost:8800/friends/getFUsername/${username}`);
  }
}
