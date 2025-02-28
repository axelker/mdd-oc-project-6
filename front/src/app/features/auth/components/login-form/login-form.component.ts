import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { TopBarComponent } from "../top-bar/top-bar.component";

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [ReactiveFormsModule, TopBarComponent],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent {
 formGroup!: FormGroup;

  constructor(private fb: FormBuilder) {
    this.fb.group({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
      ]),
    });
  }

  onSubmit() : void {

  }
}
