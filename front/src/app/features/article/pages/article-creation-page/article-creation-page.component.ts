import { Component } from '@angular/core';
import { ArticleFormComponent } from "../../components/article-form/article-form.component";
import { PageTitleHeaderComponent } from "../../../../shared/components/page-title-header/page-title-header.component";

@Component({
  selector: 'app-article-creation-page',
  standalone: true,
  imports: [ArticleFormComponent, PageTitleHeaderComponent],
  templateUrl: './article-creation-page.component.html',
  styleUrl: './article-creation-page.component.scss'
})
export class ArticleCreationPageComponent {
  
}
