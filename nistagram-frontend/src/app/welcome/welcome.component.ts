import { Component, OnInit } from '@angular/core';
import { Post } from '../service/post.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  posts: Array<Post>;
  postsRecieved: Array<Post>;

  constructor() { }

  ngOnInit() {
  }

}
