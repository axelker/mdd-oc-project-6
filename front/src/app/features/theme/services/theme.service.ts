import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Theme } from '../interfaces/theme';
import { Observable } from 'rxjs';
import { searchThemeMode } from '../types/search-theme-mode.type';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private basePath:string = "/api/themes";
  constructor(private http:HttpClient) { }

  public getThemeById(id:number) : Observable<Theme> {
    return this.http.get<Theme>(`${this.basePath}/${id}`);
  }

  public getAllTheme(searchSubscribed: searchThemeMode = 'all') : Observable<Theme[]> {
    const subscribed = searchSubscribed === 'all' ? null : searchSubscribed ==='subscribed' ? true : false;
    const subParam = subscribed !== null ? `subscribed=${subscribed}` : '';
    return this.http.get<Theme[]>(`${this.basePath}?${subParam}`);
  }

  public subscribe(id:number): Observable<void> {
    return this.http.post<void>(`${this.basePath}/${id}/subscribe`,{});
  }

  public unsubscribe(id:number): Observable<void> {
    return this.http.delete<void>(`${this.basePath}/${id}/unsubscribe`);
  }
}
