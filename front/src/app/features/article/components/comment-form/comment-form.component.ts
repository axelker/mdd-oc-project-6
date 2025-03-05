import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgIcon } from '@ng-icons/core';
import { ArticleService } from '../../services/article.service';
import { FormGroup, FormBuilder, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommentRequest } from '../../interfaces/comment-request';
import { ArticleComment } from '../../interfaces/article-comment';

@Component({
  selector: 'app-comment-form',
  standalone: true,
  imports: [NgIcon,ReactiveFormsModule],
  templateUrl: './comment-form.component.html',
  styleUrl: './comment-form.component.scss'
})
export class CommentFormComponent {
  @Input({required:true}) articleId!:number;
  @Output() commentAdded = new EventEmitter<ArticleComment>();
  formGroup!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private articleService: ArticleService,
  ) {
    this.formGroup = this.fb.group({
      message: new FormControl('', [Validators.required,Validators.maxLength(2000)]),
    });
  }

  onSubmit(): void {
    const request: CommentRequest = this.formGroup.getRawValue();
    this.articleService.addComment(this.articleId,request).subscribe({
      next: (comment:ArticleComment) => {
        this.commentAdded.emit(comment);
        this.formGroup.reset();
    }
    });
  }
}
