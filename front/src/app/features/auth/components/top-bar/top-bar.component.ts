import { Component } from '@angular/core';
import { BackButtonComponent } from "../../../../shared/components/back-button/back-button.component";

@Component({
  selector: 'app-top-bar',
  standalone: true,
  imports: [BackButtonComponent],
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.scss'
})
export class TopBarComponent {

}
