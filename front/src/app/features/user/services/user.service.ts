import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './interfaces/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private basePath:string = "/api/user";
  constructor(private http:HttpClient) { }

  public getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.basePath}/${id}`);
  }
}
