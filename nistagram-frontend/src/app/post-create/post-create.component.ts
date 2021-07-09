import { HttpClient } from "@angular/common/http";
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Router } from "@angular/router";
import { Post, PostService } from "../service/post.service";

@Component({
  selector: "app-post-create",
  templateUrl: "./post-create.component.html",
  styleUrls: ["./post-create.component.css"],
})
export class PostCreateComponent implements OnInit {
  @Input()
  post: Post;

  @Output()
  postAddedEvent = new EventEmitter();

  private selectedFile;
  imgURL: any;

  constructor(
    private postService: PostService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit() {}

  public onFileChanged(event) {
    this.selectedFile = event.target.files[0];

    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
  }

  sharePost() {
    const uploadData = new FormData();
    uploadData.append("image", this.selectedFile, this.selectedFile.name);
    this.selectedFile.imageName = this.selectedFile.name;

    this.http
      .post("http://localhost:9000/post/upload", uploadData, {
        observe: "response",
      })
      .subscribe((response) => {
        if (response.status === 200) {
          this.postService.create(this.post).subscribe((post) => {
            this.postAddedEvent.emit();
            this.router.navigate(["posts"]);
          });
          console.log("Image uploaded successfully");
        } else {
          console.log("Image not uploaded successfully");
        }
      });
  }
}
