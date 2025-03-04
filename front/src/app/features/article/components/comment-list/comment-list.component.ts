import { Component, Input, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service';
import { Observable, EMPTY } from 'rxjs';
import { ArticleComment } from '../../interfaces/article-comment';
import { AsyncPipe } from '@angular/common';
import { CommentComponent } from "../comment/comment.component";

@Component({
  selector: 'app-comment-list',
  standalone: true,
  imports: [AsyncPipe, CommentComponent],
  templateUrl: './comment-list.component.html',
  styleUrl: './comment-list.component.scss'
})
export class CommentListComponent implements OnInit {
    @Input({required:true}) articleId!:number;

    comments$: Observable<ArticleComment[]> = EMPTY;


    constructor(private articleService:ArticleService) {}

    ngOnInit(): void {
      this.comments$ = this.articleService.getAllComments(this.articleId);
    }
}
