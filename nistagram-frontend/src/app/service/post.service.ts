import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export class Post {
  constructor(
    public username: string,
    public urlMedia: string,
    public caption: string,
  ) { }
}

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`http://localhost:9000/post/all`);
  }

  get(id: any): Observable<Post> {
    return this.http.get<Post>(`http://localhost:9000/post/${id}`);
  }
}
