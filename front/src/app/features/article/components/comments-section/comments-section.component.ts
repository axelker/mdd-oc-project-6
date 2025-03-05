import { Component, Input, OnInit, signal } from '@angular/core';
import { ArticleComment } from '../../interfaces/article-comment';
import { ArticleService } from '../../services/article.service';
import { CommentFormComponent } from "../comment-form/comment-form.component";
import { CommentListComponent } from "../comment-list/comment-list.component";

@Component({
  selector: 'app-comments-section',
  standalone: true,
  imports: [CommentFormComponent, CommentListComponent],
  templateUrl: './comments-section.component.html',
  styleUrl: './comments-section.component.scss'
})
export class CommentsSectionComponent implements OnInit {
  @Input() articleId!: number;
  
  comments = signal<ArticleComment[]>([]);

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.loadComments();
  }

  loadComments() {
    this.articleService.getAllComments(this.articleId).subscribe(comments => {
      this.comments.set(comments);
    });
  }

  addComment(newComment: ArticleComment) {
    this.comments.update(comments => [...comments, newComment]);
  }
}
