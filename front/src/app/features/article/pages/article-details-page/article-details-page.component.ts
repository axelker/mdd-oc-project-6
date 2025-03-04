import { Component, OnInit } from '@angular/core';
import { ArticleDetailsComponent } from "../../components/article-details/article-details.component";
import { PageHeaderComponent } from "../../../../shared/components/page-header/page-header.component";
import { ActivatedRoute } from '@angular/router';
import { CommentListComponent } from "../../components/comment-list/comment-list.component";
import { CommentFormComponent } from "../../components/comment-form/comment-form.component";

@Component({
  selector: 'app-article-details-page',
  standalone: true,
  imports: [ArticleDetailsComponent, PageHeaderComponent, CommentListComponent, CommentFormComponent],
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
