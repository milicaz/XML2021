import { Component, OnInit } from '@angular/core';
import { LoginDataService } from '../service/login-data.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private loggout : LoginDataService
  ) { }

  ngOnInit() {
    this.loggout.loggout()
  }

}
