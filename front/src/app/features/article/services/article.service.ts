import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../interfaces/article';
import { ArticleRequest } from '../interfaces/article-request';
import { CommentRequest } from '../interfaces/comment-request';
import { ArticleComment } from '../interfaces/article-comment';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private basePath: string = "/api/articles";
  constructor(private http: HttpClient) { }

  public getAllArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.basePath}`);
  }

  public getArticleById(id:number) : Observable<Article> {
    return this.http.get<Article>(`${this.basePath}/${id}`);
  }

  public create(request:ArticleRequest): Observable<Article> {
    return this.http.post<Article>(`${this.basePath}`,request);
  }

  public getAllComments(articleId:number): Observable<ArticleComment[]> {
    return this.http.get<ArticleComment[]>(`${this.basePath}/${articleId}/comments`);
  }
  public addComment(articleId:number,request: CommentRequest): Observable<ArticleComment> {
    return this.http.post<ArticleComment>(`${this.basePath}/${articleId}/comments`,request);
  }
}
