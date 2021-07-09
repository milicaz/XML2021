import { Component, OnInit } from "@angular/core";
import { Post, PostService } from "../service/post.service";
import { ProfileService } from "../service/profile.service";

@Component({
  selector: "app-welcome",
  templateUrl: "./welcome.component.html",
  styleUrls: ["./welcome.component.css"],
})
export class WelcomeComponent implements OnInit {
  posts: Array<Post>;
  postsRecieved: Array<Post>;

  username: any;
  base64Data: any;
  retrievedImage: any;

  constructor(
    private postService: PostService,
    private profileService: ProfileService
  ) {}

  ngOnInit() {
    this.refreshData();
  }

  handleSuccessfulResponse(response) {
    this.posts = new Array<Post>();
    //get posts returned by the api call
    this.postsRecieved = response;
    for (const post of this.postsRecieved) {
      const postWithRetrievedImageField = new Post();
      postWithRetrievedImageField.id = post.id;
      postWithRetrievedImageField.username = post.username;
      postWithRetrievedImageField.createdAt = post.createdAt;
      postWithRetrievedImageField.caption = post.caption;
      postWithRetrievedImageField.totalLikes = post.totalLikes;
      postWithRetrievedImageField.totalDislikes = post.totalDislikes;
      postWithRetrievedImageField.favorite = post.favorite;
      //populate retrieved image field so that post image can be displayed
      postWithRetrievedImageField.retrievedImage =
        "data:image/jpeg;base64," + post.picByte;
      postWithRetrievedImageField.picByte = post.picByte;
      this.posts.push(postWithRetrievedImageField);
      this.profileService.executeGetProfile(post.username).subscribe((data) => {
        this.base64Data = data.picByte;
        this.retrievedImage = "data:image/jpeg;base64," + this.base64Data;
      });
    }
  }

  refreshData() {
    this.postService
      .getAllPosts()
      .subscribe((response) => {
        this.handleSuccessfulResponse(response);
      });
  }
}
