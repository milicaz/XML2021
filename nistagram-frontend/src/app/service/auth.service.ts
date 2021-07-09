import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

export class User {
  constructor(
    public username: string,
    public password: string,
    public firstName: string,
    public lastName: string,
    public email: string,
    public role: string
  ) { }
}

export class AuthService {

  constructor(
    private router : Router
  ) { }

}
