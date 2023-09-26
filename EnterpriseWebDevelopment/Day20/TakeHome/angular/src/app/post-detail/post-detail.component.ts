import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { PostService } from 'src/app.post.service';
import { Post } from '../post.detail';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {

  private postId?: number;
  post?: Post;
  notify?: string;

  constructor(private route: ActivatedRoute, private postservice: PostService, private router: Router) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.postId = params['postId'];
      
      if(this.postId)
      this.postservice.setPostId(this.postId);
      this.getPost();
    });
  }

  getPost(): void {
    if (this.postId !== undefined) {
      this.postservice.getPost(this.postId).subscribe(
        (response) => {
          if (response != null) {
            this.post = response;
            console.log(response);
            this.notify = "";
          } else {
            this.notify = "No Post with the given ID";
            this.postId = undefined;
            this.post = undefined;
          }
        }
      );
    }
  }



}
