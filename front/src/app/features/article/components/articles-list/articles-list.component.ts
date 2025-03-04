import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Article } from '../../interfaces/article';
import { AsyncPipe } from '@angular/common';
import { ArticleCardComponent } from '../article-card/article-card.component';
import { ArticleService } from '../../services/article.service';
import { EMPTY, Observable, of } from 'rxjs';

@Component({
  selector: 'app-articles-list',
  standalone: true,
  imports: [ArticleCardComponent, AsyncPipe],
  templateUrl: './articles-list.component.html',
  styleUrl: './articles-list.component.scss'
})
export class ArticlesListComponent implements OnInit, OnChanges {
  @Input() isDescending : boolean = true;
  articles$: Observable<Article[]> = EMPTY;

  constructor(private articleService: ArticleService) {}
  ngOnInit(): void {
    this.articles$ = this.articleService.getAllArticles(this.isDescending);
  }
  ngOnChanges(changes: SimpleChanges): void {
      if(changes['isDescending']){
        this.articles$ = this.articleService.getAllArticles(this.isDescending);
      }
  }
}
