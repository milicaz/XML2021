import { HttpClient } from "@angular/common/http";
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Router } from "@angular/router";
import { Story, StoryService } from "../service/story.service";

@Component({
  selector: "app-story-create",
  templateUrl: "./story-create.component.html",
  styleUrls: ["./story-create.component.css"],
})
export class StoryCreateComponent implements OnInit {
  @Input()
  story: Story;

  @Output()
  storyAddedEvent = new EventEmitter();

  private selectedFile;
  imgURL: any;

  constructor(
    private storyService: StoryService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit() {}

  public onFileChanged(event){
    this.selectedFile = event.target.files[0];

    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
  }

  shareStory(){
    const uploadData = new FormData();
    uploadData.append("image", this.selectedFile, this.selectedFile.name);
    this.selectedFile.imageName = this.selectedFile.name;

    this.http.post("http://localhost:8500/story/upload", uploadData, {
      observe: "response",
    })
    .subscribe((response) => {
      if (response.status === 200) {
        this.storyService.create(this.story).subscribe((story) => {
          this.storyAddedEvent.emit();
          this.router.navigate(["stories"]);
        });
        console.log("Image uploaded successfully");
      } else {
        console.log("Image not uploaded successfully");
      }
    });
  }


}
