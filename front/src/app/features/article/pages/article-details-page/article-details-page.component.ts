import { Component, OnInit } from '@angular/core';
import { ArticleDetailsComponent } from "../../components/article-details/article-details.component";
import { PageHeaderComponent } from "../../../../shared/components/page-header/page-header.component";
import { ActivatedRoute } from '@angular/router';
import { CommentsSectionComponent } from "../../components/comments-section/comments-section.component";

@Component({
  selector: 'app-article-details-page',
  standalone: true,
  imports: [ArticleDetailsComponent, PageHeaderComponent, CommentsSectionComponent],
  templateUrl: './article-details-page.component.html',
  styleUrl: './article-details-page.component.scss'
})
export class ArticleDetailsPageComponent implements OnInit {
  articleId!:number;
  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.articleId = this.activatedRoute.snapshot.params['id'];
  }

}
