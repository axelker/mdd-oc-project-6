import { Component } from '@angular/core';
import { ArticlesListComponent } from "../../components/articles-list/articles-list.component";
import { RouterLink } from '@angular/router';
import { NgIcon } from '@ng-icons/core';

@Component({
  selector: 'app-article-pages',
  standalone: true,
  imports: [ArticlesListComponent,RouterLink,NgIcon],
  templateUrl: './article-pages.component.html',
  styleUrl: './article-pages.component.scss'
})
export class ArticlePagesComponent {
  isDescending = true;

  filter() {
    this.isDescending = !this.isDescending;
  }
}
