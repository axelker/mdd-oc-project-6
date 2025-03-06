import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private TOKEN_SESSION_KEY: string = "token";
  constructor(private router: Router) { }

  public getToken() : string {
    return localStorage.getItem(this.TOKEN_SESSION_KEY) ?? '';
  }
  
  public isLogged():boolean {
    return !!this.getToken();
  }
  
  public logUser(token:string):void {
    localStorage.setItem(this.TOKEN_SESSION_KEY,token);
  }

  public logOutUser():void {
    localStorage.removeItem(this.TOKEN_SESSION_KEY);
    this.router.navigate(['/auth']);
  }
}
