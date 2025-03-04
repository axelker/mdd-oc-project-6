import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ControlErrorService } from '../../../../shared/services/control-error.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../../../core/services/auth.service';
import { SessionService } from '../../../../core/services/session.service';
import { AuthResponse } from '../../../../core/interfaces/auth-response';
import { LoginRequest } from '../../../../core/interfaces/login-request';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [ReactiveFormsModule],
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
      identifier: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
      ]),
    });
  }
  get identifier() {
    return this.formGroup.get('identifier');
  }

  get password() {
    return this.formGroup.get('password');
  }

  get identifierError() {
    return this.controlErrorService.buildErrorMessage("Nom d'utilisateur",this.identifier);
  }

  get passwordError() {
    return this.controlErrorService.buildErrorMessage("Mot de passe",this.password);
  }
  onSubmit(): void {
      const request: LoginRequest = this.formGroup.getRawValue();
      this.authService.login(request).subscribe({
        next: (res: AuthResponse) => {
          this.sessionService.logUser(res.token);
          this.router.navigate(['/articles']);
        },
         error: (err:HttpErrorResponse) =>  {
            this.toastr.error(err.error.message);
        }
      });
  }
}
