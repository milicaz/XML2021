import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

export class Post {
  constructor(
    public username: string,
    public urlMedia: string,
    public caption: string,
    public totalLikes: number = 0,
    public totalDislikes: number = 0,
    public favorite: false
  ) {}
}

@Injectable({
  providedIn: "root",
})
export class PostService {
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

  create(data) {
    return this.http.post(`http://localhost:9000/post/create`, data);
  }

  update(id, data) {
    return this.http.put(`http://localhost:9000/post/update/${id}`, data);
  }

  delete(id){
    return this.http.delete(`http://localhost:9000/post/delete/${id}`);
  }

  deleteAll(){
    return this.http.delete(`http://localhost:9000/post/all`);
  }

}
