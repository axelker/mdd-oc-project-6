import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ControlErrorService } from '../../../../shared/services/control-error.service';
import { AuthService } from '../../../../core/services/auth.service';
import { AuthRequest } from '../../../../core/interfaces/auth-request';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.scss',
})
export class RegisterFormComponent {
  formGroup!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private controlErrorService: ControlErrorService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.formGroup = this.fb.group({
      username: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(
          '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$'
        ),
      ]),
    });
  }

  get username() {
    return this.formGroup.get('username');
  }
  get email() {
    return this.formGroup.get('email');
  }
  get password() {
    return this.formGroup.get('password');
  }

  get usernameError() {
    return this.controlErrorService.buildErrorMessage(
      "Nom d'utilisateur",
      this.username
    );
  }

  get emailError() {
    return this.controlErrorService.buildErrorMessage(
      'Adresse e-mail',
      this.email
    );
  }

  get passwordError() {
    return this.controlErrorService.buildErrorMessage(
      'Mot de passe',
      this.password
    );
  }
  onSubmit(): void {
    const authRequest: AuthRequest = this.formGroup.getRawValue();
    this.authService.register(authRequest).subscribe({
      next: () => this.router.navigate(['/auth/login']),
      error: (err:Error) => (this.toastr.error(err.message)),
    });
  }
}
