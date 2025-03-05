import { Component, Input } from '@angular/core';
import { ArticleComment } from '../../interfaces/article-comment';
import { CommentComponent } from "../comment/comment.component";

@Component({
  selector: 'app-comment-list',
  standalone: true,
  imports: [CommentComponent],
  templateUrl: './comment-list.component.html',
  styleUrl: './comment-list.component.scss'
})
export class CommentListComponent {
    @Input() comments: ArticleComment[] = [];

}
