import { Component, Inject } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-auth-button',
  template: `
  <!-- <button (click)="auth.loginWithRedirect()">Log in</button> -->
  <ng-container *ngIf="auth.isAuthenticated$ | async; else loggedOut">
      <button (click)="auth.logout({ logoutParams: { returnTo: document.location.origin } })">
        Log out
      </button>
    </ng-container>

    <ng-template #loggedOut>
      <button (click)="auth.loginWithRedirect()">Log in</button>
    </ng-template>
  `,
  styleUrls: ['./auth-button.component.scss']
})
export class AuthButtonComponent {

  // constructor(public auth: AuthService) {}
  constructor(@Inject(DOCUMENT) public document: Document, public auth: AuthService) {}
}
