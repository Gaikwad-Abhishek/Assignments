import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PostService } from 'src/app.post.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'forum-project';
  postId: string = '';

  constructor(private postService: PostService,private router: Router) {

  }

  setId(postId: any) {
    this.postService.setPostId(parseInt(postId));
  }

  navigateToOtherComponent() {
    if (this.postId) {
      this.router.navigate(['/post-detail'], { queryParams: { postId: this.postId } });
    }
  }


}
