import { Component, OnInit, signal } from '@angular/core';
import { ThemeService } from '../../services/theme.service';
import { Theme } from '../../interfaces/theme';
import { ThemeCardComponent } from "../theme-card/theme-card.component";

@Component({
  selector: 'app-theme-list',
  standalone: true,
  imports: [ThemeCardComponent],
  templateUrl: './theme-list.component.html',
  styleUrl: './theme-list.component.scss'
})
export class ThemeListComponent implements OnInit {
  themes = signal<Theme[]>([]);
  

  constructor(private themeService: ThemeService) {}
  ngOnInit(): void {
    this.initThemes();

  }

  initThemes(): void {
    this.themeService.getAllTheme().subscribe((themes) => {
      this.themes.set(themes);
    });
  }

  updateThemesSubById(id:number,sub:boolean){
    this.themes.update((themes) => {
      return themes.map((theme) => {
        if(theme.id === id){
          return {...theme,subscribed:sub};
        }
        return theme;
      });
    });
  }

  subscribe(id:number){
    this.themeService.subscribe(id).subscribe(() => {
      this.updateThemesSubById(id,true);
    });
  }

  unsubscribe(id:number){ 
    this.themeService.unsubscribe(id).subscribe(() => {
      this.updateThemesSubById(id,false);
    });
  }
}
