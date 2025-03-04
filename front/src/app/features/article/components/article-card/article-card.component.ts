import { Component, Input, OnInit } from '@angular/core';
import { Article } from '../../interfaces/article';
import { UserService } from '../../../user/services/user.service';
import { User } from '../../../user/interfaces/user';
import { EMPTY, Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-article-card',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './article-card.component.html',
  styleUrl: './article-card.component.scss'
})
export class ArticleCardComponent implements OnInit {
  @Input({required:true}) article!: Article;
  author$:Observable<User> = EMPTY;
  
  constructor(private userService: UserService) {
    
  }

  ngOnInit(): void {
    this.author$ = this.userService.getUserById(this.article.ownerId);
  }

}
