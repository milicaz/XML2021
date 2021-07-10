import { Component, OnInit } from "@angular/core";
import { Friends, FriendsService } from "../service/friends.service";
import { Post, PostService } from "../service/post.service";

@Component({
  selector: "app-friends",
  templateUrl: "./friends.component.html",
  styleUrls: ["./friends.component.css"],
})
export class FriendsComponent implements OnInit {
  friends: Array<Friends>;
  friendsRecieved: Array<Friends>;

  posts: Array<Post>;
  postsRecieved: Array<Post>;

  id: number;
  username: string;
  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: Date;
  phone: string;
  privacy: boolean;
  picByte: string;
  friendUname: string;
  image = "data:image/jpeg;base64,";

  clicked = false;

  constructor(
    private friendService: FriendsService,
    private postService: PostService
  ) {}

  ngOnInit() {
    this.refreshData();
  }

  refreshData() {
    this.friendService
      .getFriends(sessionStorage.getItem("logUser"))
      .subscribe((response) => {
        this.handleSuccessfulResponse(response);
      });
  }

  // we will be taking the friends response returned from the database
  // and we will be adding the retrieved
  handleSuccessfulResponse(response) {
    this.friends = new Array<Friends>();
    //get friends returned by the api call
    this.friendsRecieved = response;
    for (const friend of this.friendsRecieved) {
      const friendWithImage = new Friends(
        friend.id,
        friend.username,
        friend.firstName,
        friend.lastName,
        friend.email,
        friend.dateOfBirth,
        friend.phone,
        friend.privacy,
        friend.picByte,
        friend.friendUname
      );
      this.friends.push(friendWithImage);
    }
  }

  viewPosts(username) {
    this.postService.getAllByUsername(username).subscribe((data) => {
      console.log("Prikazuje postove korisnika: " + data);
      this.posts = new Array<Post>();
      //get posts returned by the api call
      this.postsRecieved = data;
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
    });
  }
}
