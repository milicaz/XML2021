import { DatePipe } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Story, StoryService } from "../service/story.service";

@Component({
  selector: "app-story",
  templateUrl: "./story.component.html",
  styleUrls: ["./story.component.css"],
})
export class StoryComponent implements OnInit {
  stories: Array<Story>;
  storiesRecieved: Array<Story>;
  selectedStory: Story;
  action: string;

  constructor(
    private storyService: StoryService,
    private datepipe: DatePipe,
    private activedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.refreshData();
  }

  handleSuccessfulResponse(response) {
    this.stories = new Array<Story>();
    //get stories returned by the api call
    this.storiesRecieved = response;
    for (const story of this.storiesRecieved) {
      const storyWithRetrievedImageField = new Story();
      storyWithRetrievedImageField.id = story.id;
      storyWithRetrievedImageField.username = story.username;
      storyWithRetrievedImageField.createdAt = story.createdAt;
      //populate retrieved image field so that story image can be displayed
      storyWithRetrievedImageField.retrievedImage =
        "data:image/jpeg;base64," + story.picByte;
      storyWithRetrievedImageField.picByte = story.picByte;
      this.stories.push(storyWithRetrievedImageField);
    }
  }

  createStory() {
    this.selectedStory = new Story();
    this.selectedStory.username = sessionStorage.getItem("logUser");
    var datum = Date.now();
    this.selectedStory.createdAt = this.datepipe.transform(datum, "yyyy-MM-dd");
    this.router.navigate(["stories"], { queryParams: { action: "add" } });
  }

  viewStory(id: number) {
    this.router.navigate(["stories"], { queryParams: { id, action: "view" } });
  }

  refreshData() {
    this.storyService
      .getAllByUsername(sessionStorage.getItem('logUser'))
      .subscribe((response) => {
        console.log("Refresh:" + response);
        this.handleSuccessfulResponse(response);
      });
    this.activedRoute.queryParams.subscribe((params) => {
      // get the url parameter named action. this can either be add or view.
      this.action = params["action"];
      // get the parameter id. this will be the id of the post whose details
      // are to be displayed when action is view.
      const id = params["id"];
      // if id exists, convert it to integer and then retrive the post from
      // the post array
      if (id) {
        this.selectedStory = this.stories.find((story) => {
          return story.id === +id;
        });
      }
    });
  }  
}
