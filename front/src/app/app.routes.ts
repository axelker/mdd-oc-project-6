import { Routes } from '@angular/router';
import { canActivateUnAuth } from './core/guards/unauth.guard';
import { canActivateAuth } from './core/guards/auth.guard';
import { NotFoundComponent } from './shared/components/not-found/not-found.component';

export const routes: Routes = [
    {
        path:'', 
        redirectTo: 'articles', 
        pathMatch: 'full' 
    },
    {
        path:'articles',
        loadChildren: () => import('./features/article/article.routes').then((v) => v.routes),
        canActivate:[canActivateAuth]
    },
    {
        path:'themes',
        loadChildren: () => import('./features/theme/theme.routes').then((v) => v.routes),
        canActivate:[canActivateAuth]
    },
    {
        path:'auth',
        loadChildren: () => import('./features/auth/auth.routes').then((v) => v.routes),
        canActivate:[canActivateUnAuth]
    },
    { path: '404', component: NotFoundComponent },
    { path: '**', redirectTo: '404' }
];
