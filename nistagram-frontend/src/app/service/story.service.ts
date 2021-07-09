import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export class Story {
  id: number;
  username: string;
  createdAt: any;
  picByte: string;
  retrievedImage:string;
  constructor(){}
}

@Injectable({
  providedIn: 'root'
})
export class StoryService {
  story: Story;

  constructor(private http:HttpClient) { }

  getAllStories(): Observable<Story[]> {
    return this.http.get<Story[]>('http://localhost:8500/story/all');
  }

  getAllByUsername(username) : Observable<Story[]> {
    return this.http.get<Story[]>(`http://localhost:8500/story/getUsername/${username}`)
  }

  getStory(id): Observable<Story>{
    return this.http.get<Story>(`http://localhost:8500/story/getStory/${id}`);
  }

  create(story: Story) {
    return this.http.post('http://localhost:8500/story/create', story);
  }
}
