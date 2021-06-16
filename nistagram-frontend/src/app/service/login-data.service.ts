import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

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

@Injectable({
  providedIn: 'root'
})
export class LoginDataService {

  user : User

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  executeLoginService() {
    return this.http.get<User>('http://localhost:9100/auth/all/users')
    console.log("Execute Login!")
  }

  executeRegisterService(user) {
    return this.http.post<User>(`http://localhost:9100/auth/user/register`, user)
  }

  hadleSuccessfulResponse(response, username, password) {
    var r = response.length
    console.log("Varijabla r je: " + r)
    for (r in response) {
      if (username === response[r].username && password === response[r].password) {
        sessionStorage.setItem('logUser', username)
        this.router.navigate(['welcome'])
      } else {
        //console.log("Niste uneli dobar username ili password")
      }
    }
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('logUser')
    return !(user === null)
  }

  loggout() {
    sessionStorage.removeItem('logUser')
  }

}
