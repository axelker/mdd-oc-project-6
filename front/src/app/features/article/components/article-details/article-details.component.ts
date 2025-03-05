import { Component, Input, OnInit } from '@angular/core';
import { Article } from '../../interfaces/article';
import { ArticleService } from '../../services/article.service';
import { EMPTY, Observable } from 'rxjs';
import { AsyncPipe, DatePipe } from '@angular/common';

@Component({
  selector: 'app-article-details',
  standalone: true,
  imports:[AsyncPipe,DatePipe],
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.scss',
})
export class ArticleDetailsComponent implements OnInit {
  @Input({required:true}) articleId!:number;
  article$: Observable<Article> = EMPTY;

  constructor(
    private articleService: ArticleService,
  ) {}
  ngOnInit(): void {
    this.article$ = this.articleService.getArticleById(this.articleId);
  }
}
