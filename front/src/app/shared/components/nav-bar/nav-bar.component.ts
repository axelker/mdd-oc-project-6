import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { SessionService } from '../../../core/services/session.service';
import { NgIcon } from '@ng-icons/core';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterLink,NgIcon,RouterLinkActive],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss'
})
export class NavBarComponent {

  constructor(private sessionService: SessionService) { }


  logout(): void {
    this.sessionService.logOutUser();
  }
}
