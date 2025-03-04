import { Component, Input } from '@angular/core';
import { ArticleComment } from '../../interfaces/article-comment';

@Component({
  selector: 'app-comment',
  standalone: true,
  imports: [],
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.scss'
})
export class CommentComponent {
  @Input({required:true}) comment!: ArticleComment;
}
