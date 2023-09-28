import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
// import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  username: string = "";
  password: string = "";


  constructor(private authService: AuthService, private router: Router) { }

  login(): void {
    this.authService.login(this.username, this.password).subscribe();
    this.router.navigate(['/cycles']);
  }

  // loginWithGitHub() {
  //   this.oauthService.initCodeFlow();
  // }
}
