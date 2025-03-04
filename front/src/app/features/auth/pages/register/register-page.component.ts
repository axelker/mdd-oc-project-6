import { Component } from '@angular/core';
import { RegisterFormComponent } from "../../components/register-form/register-form.component";
import { UnauthNavBarComponent } from "../../components/unauth-top-bar/unauth-nav-bar.component";

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [RegisterFormComponent, UnauthNavBarComponent],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterPageComponent {

}
