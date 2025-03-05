import { Component, EventEmitter, Input, Output } from '@angular/core';
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
  @Output() unsubscribe : EventEmitter<number> = new EventEmitter<number>();
  @Output() subscribe : EventEmitter<number> = new EventEmitter<number>();
  constructor() {}

  sub(sub:boolean) {
    if(sub){
      this.subscribe.emit(this.theme.id);
      return;
    }
    this.unsubscribe.emit(this.theme.id);
  }
}
