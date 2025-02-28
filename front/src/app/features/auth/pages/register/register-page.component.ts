import { Component } from '@angular/core';
import { RegisterFormComponent } from "../../components/register-form/register-form.component";
import { TopBarComponent } from "../../components/top-bar/top-bar.component";

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [RegisterFormComponent, TopBarComponent],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterPageComponent {

}
