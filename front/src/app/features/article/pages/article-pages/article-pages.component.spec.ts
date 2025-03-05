import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticlePagesComponent } from './article-pages.component';

describe('ArticlePagesComponent', () => {
  let component: ArticlePagesComponent;
  let fixture: ComponentFixture<ArticlePagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticlePagesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticlePagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
