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
import { AuthRequest } from '../../../../core/interfaces/auth-request';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../../../core/services/auth.service';
import { SessionService } from '../../../../core/services/session.service';
import { AuthResponse } from '../../../../core/interfaces/auth-response';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [ReactiveFormsModule,NgIf],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent {
 formGroup!: FormGroup;

    constructor(
      private fb: FormBuilder,
      private controlErrorService: ControlErrorService,
      private authService: AuthService,
      private router: Router,
      private toastr: ToastrService,
      private sessionService: SessionService
    ) {
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
  onSubmit(): void {
      const authRequest: AuthRequest = this.formGroup.getRawValue();
      this.authService.login(authRequest).subscribe({
        next: (res: AuthResponse) => {
          this.sessionService.logUser(res.token);
          this.router.navigate(['/articles']);
        },
        error: (err: Error) => this.toastr.error(err.message),
      });
  }
}
