import { Component } from '@angular/core';
import { ArticlesListComponent } from "../../components/articles-list/articles-list.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-article-pages',
  standalone: true,
  imports: [ArticlesListComponent,RouterLink],
  templateUrl: './article-pages.component.html',
  styleUrl: './article-pages.component.scss'
})
export class ArticlePagesComponent {
}
