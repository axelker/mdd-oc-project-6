import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ControlErrorService } from '../../../../shared/services/control-error.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [ReactiveFormsModule,NgIf],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent {
 formGroup!: FormGroup;

  constructor(private fb: FormBuilder,public controlErrorService: ControlErrorService) {
    this.formGroup = this.fb.group({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
      ]),
    });
  }
  get username() {
    return this.formGroup.get('username');
  }

  get password() {
    return this.formGroup.get('password');
  }

  get usernameError() {
    return this.controlErrorService.buildErrorMessage("Nom d'utilisateur",this.username);
  }

  get passwordError() {
    return this.controlErrorService.buildErrorMessage("Mot de passe",this.password);
  }
  onSubmit() : void {
    console.log(this.formGroup.getRawValue())
  }
}
