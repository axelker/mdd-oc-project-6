import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth.service';
import { ControlErrorService } from '../../../../shared/services/control-error.service';
import { UserInfo } from '../../../../core/interfaces/user-info';
import { ToastrService } from 'ngx-toastr';
import { UserRequest } from '../../../../core/interfaces/user-request';

@Component({
  selector: 'app-user-profil-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './user-profil-form.component.html',
  styleUrl: './user-profil-form.component.scss'
})
export class UserProfilFormComponent {
formGroup!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private controlErrorService: ControlErrorService,
    private authService: AuthService,
    private toastr: ToastrService,
  ) {
    this.initForm();
    this.authService.getUserInfo().subscribe((user:UserInfo) => {
      this.formGroup.patchValue(user);
    })
    
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

  initForm(): void {
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
  onSubmit(): void {
    const request: UserRequest = this.formGroup.getRawValue();
    this.authService.updateUserInfo(request).subscribe({
      next: (user) => {
        this.toastr.success('Profil mis à jour');
        this.formGroup.patchValue(user);}
    });
  }

}
