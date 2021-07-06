import { Component, OnInit } from '@angular/core';
import { PictureUploadService } from '../service/picture-upload.service';
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
  username: any;

  retrieveResponse: any;
  base64Data: any;
  retrievedImage: any;

  constructor(private postService: PostService, private pictureUpload: PictureUploadService) { }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('logUser');
    this.retrievePosts();
    this.pictureUpload.executePictureUpload(this.username).subscribe(data => {
      console.log(data);
      this.retrieveResponse = data
      this.base64Data = data.picByte
      this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data
      console.log("Image " + this.retrievedImage)
    });
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
