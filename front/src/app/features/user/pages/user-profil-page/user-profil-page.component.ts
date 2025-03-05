import { Component } from '@angular/core';
import { UserProfilFormComponent } from "../../components/user-profil-form/user-profil-form.component";
import { ThemeListComponent } from "../../../theme/components/theme-list/theme-list.component";

@Component({
  selector: 'app-user-profil-page',
  standalone: true,
  imports: [UserProfilFormComponent, ThemeListComponent],
  templateUrl: './user-profil-page.component.html',
  styleUrl: './user-profil-page.component.scss'
})
export class UserProfilPageComponent {

}
