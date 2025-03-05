import { Component } from '@angular/core';
import { ThemeListComponent } from "../../components/theme-list/theme-list.component";

@Component({
  selector: 'app-theme-page',
  standalone: true,
  imports: [ThemeListComponent],
  templateUrl: './theme-page.component.html',
  styleUrl: './theme-page.component.scss'
})
export class ThemePageComponent {

}
