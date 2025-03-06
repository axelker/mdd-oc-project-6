import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { SessionService } from '../../../../core/services/session.service';
import { NgIcon } from '@ng-icons/core';
import { NgIf } from '@angular/common';
import { NavLinksComponent } from "../nav-links/nav-links.component";

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterLink, NgIcon, NgIf, RouterLinkActive, NavLinksComponent],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss'
})
export class NavBarComponent {
  isMenuOpen = false;
  constructor(private sessionService: SessionService) { }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
  closeMenu() : void {
    this.isMenuOpen = false;
  }

  logout(): void {
    this.sessionService.logOutUser();
  }


}
