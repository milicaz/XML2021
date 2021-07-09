import { DatePipe } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { PictureUploadService } from "../service/picture-upload.service";
import { Post, PostService } from "../service/post.service";

@Component({
  selector: "app-post",
  templateUrl: "./post.component.html",
  styleUrls: ["./post.component.css"],
})
export class PostComponent implements OnInit {
  posts: Array<Post>;
  postsRecieved: Array<Post>;
  selectedPost: Post;
  action: string;

  username: any;

  base64Data: any;
  retrievedImage: any;

  constructor(
    private postService: PostService,
    private pictureUpload: PictureUploadService,
    private datepipe: DatePipe,
    private activedRoute: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit(): void {
    // this.username = sessionStorage.getItem("logUser");
    // this.retrievePosts();
    // this.pictureUpload.executePictureUpload(this.username).subscribe((data) => {
    //   console.log(data);
    //   this.base64Data = data.picByte;
    //   this.retrievedImage = "data:image/jpeg;base64," + this.base64Data;
    //   console.log("Image " + this.retrievedImage);
    // });
    this.refreshData();
  }

  // we will be taking the posts response returned from the database
  // and we will be adding the retrieved
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
    }
  }

  createPost() {
    this.selectedPost = new Post();
    this.selectedPost.username = sessionStorage.getItem("logUser");
    var datum = Date.now();
    this.selectedPost.createdAt = this.datepipe.transform(datum, "yyyy-MM-dd");
    this.router.navigate(["posts"], { queryParams: { action: "add" } });
  }

  viewPost(id: number) {
    this.router.navigate(["posts"], { queryParams: { id, action: "view" } });
  }

  deletePost(id: number) {
    this.postService.delete(id).subscribe((post) => {
      this.refreshData();
      this.router.navigate(["posts"]);
    });
  }

  likeUp($event, id) {
    console.log("Like button is clicked");
    console.log("ID je: " + id);
    this.postService.getPost(id).subscribe(data =>{
      console.log("Data je: " + data);
      data.totalLikes += 1;
    });
  }

  dislikeUp($event, id) {
    console.log("Dislike button is clicked");
    console.log("ID je: " + id);
    this.postService.getPost(id).subscribe(data =>{
      console.log("Data je: " + data);
      var p = new Post();
      p.username = data.username;
      console.log("p.username :" + p.username);
      p.caption = data.caption;
      p.createdAt = data.createdAt;
      p.totalLikes = data.totalLikes;
      p.totalDislikes = data.totalDislikes + 1;
      console.log("p.totalDislikes :" + p.totalDislikes);
      p.favorite = data.favorite;

      console.log("p:" + p);
      this.postService.update(id,p).subscribe(update =>{
        console.log("Update je: " + update);
      });
    });
  }

  refreshData() {
    this.postService
      .getAllPosts()
      .subscribe((response) => this.handleSuccessfulResponse(response));
    this.activedRoute.queryParams.subscribe((params) => {
      // get the url parameter named action. this can either be add or view.
      this.action = params["action"];
      // get the parameter id. this will be the id of the post whose details
      // are to be displayed when action is view.
      const id = params["id"];
      // if id exists, convert it to integer and then retrive the post from
      // the post array
      if (id) {
        this.selectedPost = this.posts.find((post) => {
          return post.id === +id;
        });
      }
    });
  }
}
