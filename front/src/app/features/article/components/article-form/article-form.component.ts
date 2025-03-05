import { Component } from '@angular/core';
import { ArticleService } from '../../services/article.service';
import { FormGroup, FormBuilder, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ControlErrorService } from '../../../../shared/services/control-error.service';
import { ArticleRequest } from '../../interfaces/article-request';
import { Article } from '../../interfaces/article';
import { Theme } from '../../../theme/interfaces/theme';
import { ThemeService } from '../../../theme/services/theme.service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-article-form',
  standalone: true,
  imports: [ReactiveFormsModule,NgClass],
  templateUrl: './article-form.component.html',
  styleUrl: './article-form.component.scss'
})
export class ArticleFormComponent {
 formGroup!: FormGroup;
 themeOptions: Theme[] =[];
  
  constructor(
    private fb: FormBuilder,
    private controlErrorService: ControlErrorService,
    private articleService: ArticleService,
    themeService:ThemeService,
    private router: Router,
  ) {
    themeService.getAllTheme().subscribe((themes) => {
      this.themeOptions = themes;
    });
    this.initForm();
  }

  private initForm(): void {
    this.formGroup = this.fb.group({
      theme: new FormControl('', [Validators.required]),
      title: new FormControl('', [Validators.required,Validators.maxLength(255)]),
      content: new FormControl('', [
        Validators.required,
        Validators.maxLength(2000),
      ]),
    });
  }
  get theme() {
    return this.formGroup.get('theme');
  }
  get title() {
    return this.formGroup.get('title');
  }
  get content() {
    return this.formGroup.get('content');
  }

  get themeError() {
    return this.controlErrorService.buildErrorMessage(
      "Theme",
      this.theme
    );
  }

  get titleError() {
    return this.controlErrorService.buildErrorMessage(
      'Titre',
      this.title
    );
  }

  get contentError() {
    return this.controlErrorService.buildErrorMessage(
      'Contenu',
      this.content
    );
  }

  emptyValueSelected(): boolean {
    return !this.theme?.value;
  }

  onSubmit(): void {
    console.log(this.formGroup.getRawValue())
    const request: ArticleRequest = {
      themeId: this.theme?.value,
      name: this.title?.value,
      description: this.content?.value,
    }
    this.articleService.create(request).subscribe({
      next: (article: Article) => this.router.navigate(['/articles/', article.id]),
    });
  }
}
