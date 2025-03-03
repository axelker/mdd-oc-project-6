import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { ThemeService } from '../../../theme/services/theme.service';
import { User } from '../../../user/services/interfaces/user';
import { UserService } from '../../../user/services/user.service';
import { Article } from '../../interfaces/article';
import { Theme } from '../../../theme/services/interfaces/theme';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-article-details',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.scss'
})
export class ArticleDetailsComponent {
 @Input({required:true}) article!: Article;

  constructor(private userService: UserService,private themeService:ThemeService) {}

  get author() : Observable<User> {
    return this.userService.getUserById(this.article.ownerId);
  }

  get theme() : Observable<Theme> {
    return this.themeService.getThemeById(this.article.themeId);
  }
}
