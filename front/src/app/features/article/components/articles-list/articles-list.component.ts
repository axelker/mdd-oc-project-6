import { Component, Input } from '@angular/core';
import { Article } from '../../interfaces/article';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-articles-list',
  standalone: true,
  imports: [NgFor],
  templateUrl: './articles-list.component.html',
  styleUrl: './articles-list.component.scss'
})
export class ArticlesListComponent {
  articles: Article[] = [];
}
