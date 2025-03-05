import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Theme } from '../interfaces/theme';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private basePath:string = "/api/themes";
  constructor(private http:HttpClient) { }

  public getThemeById(id:number) : Observable<Theme> {
    return this.http.get<Theme>(`${this.basePath}/${id}`);
  }

  public getAllTheme() : Observable<Theme[]> {
    return this.http.get<Theme[]>(`${this.basePath}`);
  }
}
