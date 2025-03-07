import { Routes } from '@angular/router';
import { canActivateUnAuth } from './core/guards/unauth.guard';
import { canActivateAuth } from './core/guards/auth.guard';
import { NotFoundComponent } from './shared/components/not-found/not-found.component';
import { AuthLayoutComponent } from './core/layout/auth-layout/auth-layout.component';

export const routes: Routes = [
  {
    path: '',
    canActivate: [canActivateAuth],
    component: AuthLayoutComponent,
    children: [
      {
        path: '',
        redirectTo: 'articles',
        pathMatch: 'full',
      },
      {
        path: 'articles',
        loadChildren: () =>
          import('./features/article/article.routes').then((v) => v.routes),
      },
      {
        path: 'themes',
        loadChildren: () =>
          import('./features/theme/theme.routes').then((v) => v.routes),
      },
      {
        path: 'users',
        loadChildren: () =>
          import('./features/user/user.routes').then((v) => v.routes),
      },
    ],
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./features/auth/auth.routes').then((v) => v.routes),
    canActivate: [canActivateUnAuth],
  },
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '404' },
];
