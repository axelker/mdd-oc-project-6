import { Component } from '@angular/core';
import { BackButtonComponent } from "../../../../shared/components/back-button/back-button.component";

@Component({
  selector: 'app-unauth-nav-bar',
  standalone: true,
  imports: [BackButtonComponent],
  templateUrl: './unauth-nav-bar.component.html',
  styleUrl: './unauth-nav-bar.component.scss'
})
export class UnauthNavBarComponent {

}
