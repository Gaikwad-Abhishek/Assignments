import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app.post.service';

@Component({
  selector: 'app-like-comment',
  templateUrl: './like-comment.component.html',
  styleUrls: ['./like-comment.component.css']
})
export class LikeCommentComponent implements OnInit {
  
  comments?: any[];
  likeCount?: number;
  notify?: string;

  constructor(private postService: PostService){}

  ngOnInit(): void {
    this.getComments();
    this.getlikes();
  }

  getlikes(){
    this.postService.getLikeCount().subscribe(
      (response) => {
        if (response != null) {
          this.likeCount = response;
          console.log(response);
          this.notify = "";
        } else {
          this.notify = "No Post with the given ID";
        }
      }
      );
  }

  getComments(){
    this.postService.getComments().subscribe(
    (response) => {
      if (response != null) {
        this.comments = response;
        console.log(response);
        this.notify = "";
      } else {
        this.notify = "No Post with the given ID";
      }
    }
    );
  }
}
