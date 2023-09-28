import { Component, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-user-profile',
  template: `
    <ul *ngIf="auth.user$ | async as user">
      <li>{{ user.nickname }}</li>
    </ul>
    `,
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit{
  
  user: any;
  constructor(public auth: AuthService) {}
  

  ngOnInit(): void {
    this.auth.user$.subscribe((user) => {
      if (user) {
        this.user = user;
        console.log("User Details", this.user);
      }
    });  
  }
 
}
