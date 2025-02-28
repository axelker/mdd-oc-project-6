import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  public isLogged = signal(false);
  private TOKEN_SESSION_KEY: string = "token";
  constructor() { }

  public getToken() : string {
    return localStorage.getItem(this.TOKEN_SESSION_KEY) ?? '';
  }

  public logUser(token:string):void {
    this.isLogged.set(true);
    localStorage.setItem(this.TOKEN_SESSION_KEY,token);
  }

  public logOutUser():void {
    this.isLogged.set(false);
    localStorage.removeItem(this.TOKEN_SESSION_KEY);
  }
}
