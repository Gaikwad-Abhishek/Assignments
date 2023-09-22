import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { PostService } from 'src/app.post.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit{

  private postId: number | undefined;
  post: any;

  notify: string = "";
  constructor(private route: ActivatedRoute, private postservice: PostService, private router: Router) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.postId = params['postId'];
      this.getPost();
    });
    console.log("InIt");
  }
  
  getPost(): void {
    if(this.postId !== undefined){  
    this.postservice.getPost(this.postId).subscribe(
      (response) => {
        if(response != null){
        this.post = response;
        console.log(response);
        this.notify = "";
        }else{
          this.notify = "No Post with the given ID";
          this.postId = undefined;
          this.post = null;
          // this.router.navigate(['/home']);

        }
      }
    );
    }
  }



}
