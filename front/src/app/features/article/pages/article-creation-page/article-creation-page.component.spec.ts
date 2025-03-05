import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleCreationPageComponent } from './article-creation-page.component';

describe('ArticleCreationPageComponent', () => {
  let component: ArticleCreationPageComponent;
  let fixture: ComponentFixture<ArticleCreationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticleCreationPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleCreationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
