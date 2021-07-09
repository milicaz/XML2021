import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

export class Post {
  id: number;
  username: string;
  createdAt: any;
  caption: string;
  totalLikes: number = 0;
  totalDislikes: number = 0;
  favorite: false;
  picByte: string;
  retrievedImage: string;
  constructor() {}
}

@Injectable({
  providedIn: "root",
})
export class PostService {
  post: Post;

  constructor(private http: HttpClient) {}

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`http://localhost:9000/post/all`);
  }

  getPost(id: any): Observable<Post> {
    return this.http.get<Post>(`http://localhost:9000/post/get/${id}`);
  }

  getAllByUsername(username): Observable<Post[]> {
    return this.http.get<Post[]>(
      `http://localhost:9000/post/getUsername/${username}`
    );
  }

  create(post: Post) {
    return this.http.post('http://localhost:9000/post/create', post);
  }

  update(id, post) {
    return this.http.put(`http://localhost:9000/post/update/${id}`, post);
  }

  delete(id) {
    return this.http.delete(`http://localhost:9000/post/delete/${id}`);
  }

  deleteAll() {
    return this.http.delete(`http://localhost:9000/post/all`);
  }
}
