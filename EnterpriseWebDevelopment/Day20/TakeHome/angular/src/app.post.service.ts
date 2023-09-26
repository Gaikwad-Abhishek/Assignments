import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from './app/post.detail';


@Injectable({
  providedIn: 'root'
})

export class PostService {

  private apiUrl = 'http://localhost:8080/api/forum'; 
  postId: number;

  constructor(private http: HttpClient) {
    this.postId = 0;
  }

  setPostId(postId:number){
    this.postId = postId;
  }

  
  getUsers(): Observable<any> {

    return this.http.get(`${this.apiUrl}/users`); 
  
  }

  getPost(postId: number): Observable<Post> {

    return this.http.get<Post>(`${this.apiUrl}/post/${postId}`);
  
  }

  getLikeCount(): Observable<any>{

    return this.http.get(`${this.apiUrl}/post/${this.postId}/likes`);

  }

  getComments(): Observable<any> {

    return this.http.get(`${this.apiUrl}/post/${this.postId}/comments`);

  }

}