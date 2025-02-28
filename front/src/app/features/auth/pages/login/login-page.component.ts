import { Component } from '@angular/core';
import { BackButtonComponent } from '../../../../shared/components/back-button/back-button.component';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [BackButtonComponent],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

}
