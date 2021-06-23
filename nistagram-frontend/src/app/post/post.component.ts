import { Component, OnInit } from '@angular/core';
import { Post, PostService } from '../service/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  posts?: Post[];
  currentPost?: Post;
  currentIndex = -1;

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.retrievePosts();
  }

  retrievePosts(): void {
    this.postService.getAllPosts().subscribe(data => {
      this.posts = data;
      console.log(data);
    }, error=>{
      console.log(error);
    });
  }

  refreshList(): void {
    this.retrievePosts();
    this.currentPost = undefined;
    this.currentIndex = -1;
  }

}
