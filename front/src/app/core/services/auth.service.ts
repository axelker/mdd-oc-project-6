import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthResponse } from '../interfaces/auth-response';
import { RegisterRequest } from '../interfaces/register-request';
import { LoginRequest } from '../interfaces/login-request';
import { UserInfo } from '../interfaces/user-info';
import { UserRequest } from '../interfaces/user-request';

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

  public getUserInfo(): Observable<UserInfo> {
   return this.http.get<UserInfo>(`${this.basePath}/me`);
  }

  public updateUserInfo(user: UserRequest):Observable<UserInfo> {
    return this.http.put<UserInfo>(`${this.basePath}/me`,user);
  }
}
