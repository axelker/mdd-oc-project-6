import { Injectable } from '@angular/core';
import { SessionService } from '../services/session.service';
import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JwtInterceptorService implements HttpInterceptor {
  
  constructor(private sessionService: SessionService) { }

  public intercept(request: HttpRequest<any>, next: HttpHandler) {
    const token = this.sessionService.getToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }
    return next.handle(request);
  }
}
