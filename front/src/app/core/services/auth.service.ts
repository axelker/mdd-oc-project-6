import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthResponse } from '../interfaces/auth-response';
import { RegisterRequest } from '../interfaces/register-request';
import { User } from '../interfaces/user';
import { LoginRequest } from '../interfaces/login-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private basePath:string = "/api/auth";
  constructor(private http:HttpClient) { }


  public login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.basePath}/login`,request);
  }

  public register(request: RegisterRequest): Observable<void> {
    return this.http.post<void>(`${this.basePath}/register`,request);
  }

  public getUser(): Observable<User> {
   return this.http.get<User>(`${this.basePath}/me`);
  }

  public updateUser(user: User):Observable<User> {
    return this.http.put<User>(`${this.basePath}/me`,user);
  }
}
