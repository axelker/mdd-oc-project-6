import { Component, Input } from '@angular/core';
import { Theme } from '../../interfaces/theme';

@Component({
  selector: 'app-theme-card',
  standalone: true,
  imports: [],
  templateUrl: './theme-card.component.html',
  styleUrl: './theme-card.component.scss'
})
export class ThemeCardComponent {
  @Input({required:true}) theme!: Theme;
  constructor() {}

}
