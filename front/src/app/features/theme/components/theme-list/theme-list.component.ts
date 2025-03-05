import { Component, Input, OnInit, signal } from '@angular/core';
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
  @Input() subscribed : boolean | null = null;
  themes = signal<Theme[]>([]);
  

  constructor(private themeService: ThemeService) {}
  ngOnInit(): void {
    this.initThemes();

  }

  initThemes(): void {
    this.themeService.getAllTheme(this.subscribed).subscribe((themes) => {
      this.themes.set(themes);
    });
  }

  updateThemesSubById(){
    this.initThemes()
  }

  subscribe(id:number){
    this.themeService.subscribe(id).subscribe(() => {
      this.updateThemesSubById();
    });
  }

  unsubscribe(id:number){ 
    this.themeService.unsubscribe(id).subscribe(() => {
      this.updateThemesSubById();
    });
  }
}
