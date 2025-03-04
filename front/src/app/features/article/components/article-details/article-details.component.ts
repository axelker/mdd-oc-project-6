import { Component, Input, OnInit } from '@angular/core';
import { Article } from '../../interfaces/article';

@Component({
  selector: 'app-article-details',
  standalone: true,
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.scss'
})
export class ArticleDetailsComponent {
 @Input({required:true}) article!: Article;

  constructor() {}
  
}
