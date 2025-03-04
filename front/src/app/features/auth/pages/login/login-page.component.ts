import { Component } from '@angular/core';
import { LoginFormComponent } from "../../components/login-form/login-form.component";
import { UnauthNavBarComponent } from "../../components/unauth-top-bar/unauth-nav-bar.component";

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [LoginFormComponent, UnauthNavBarComponent],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

}
