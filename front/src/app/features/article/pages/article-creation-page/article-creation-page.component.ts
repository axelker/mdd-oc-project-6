import { Component } from '@angular/core';
import { ArticleFormComponent } from "../../components/article-form/article-form.component";
import { PageHeaderComponent } from "../../../../shared/components/page-header/page-header.component";

@Component({
  selector: 'app-article-creation-page',
  standalone: true,
  imports: [ArticleFormComponent, PageHeaderComponent],
  templateUrl: './article-creation-page.component.html',
  styleUrl: './article-creation-page.component.scss'
})
export class ArticleCreationPageComponent {
  
}
