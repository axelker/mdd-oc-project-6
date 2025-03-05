import { Component, Input } from '@angular/core';
import { BackButtonComponent } from "../back-button/back-button.component";

@Component({
  selector: 'app-page-header',
  standalone: true,
  imports: [BackButtonComponent],
  templateUrl: './page-header.component.html',
  styleUrl: './page-header.component.scss'
})
export class PageHeaderComponent {
  @Input() title: string = '';

}
