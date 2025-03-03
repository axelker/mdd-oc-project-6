import { Component, Input } from '@angular/core';
import { Article } from '../../interfaces/article';
import { UserService } from '../../../user/services/user.service';
import { User } from '../../../user/services/interfaces/user';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-article-card',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './article-card.component.html',
  styleUrl: './article-card.component.scss'
})
export class ArticleCardComponent {
  @Input({required:true}) article!: Article;

  constructor(private userService: UserService) {}

  get author() : Observable<User> {
    return this.userService.getUserById(this.article.ownerId);
  }
}
