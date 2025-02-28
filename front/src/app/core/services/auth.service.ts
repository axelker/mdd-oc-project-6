import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthResponse } from '../interfaces/auth-response';
import { AuthRequest } from '../interfaces/auth-request';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private basePath:string = "api/auth";
  constructor(private http:HttpClient) { }


  public login(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.basePath}/login`,authRequest);
  }

  public register(authRequest: AuthRequest): Observable<void> {
    return this.http.post<void>(`${this.basePath}/register`,authRequest);
  }

  public getUser(): Observable<User> {
   return this.http.get<User>(`${this.basePath}/me`);
  }

  public updateUser(user: User):Observable<User> {
    return this.http.put<User>(`${this.basePath}/me`,user);
  }
}
