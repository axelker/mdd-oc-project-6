import { Routes } from '@angular/router';
import { canActivateUnAuth } from './core/guards/unauth.guard';
import { canActivateAuth } from './core/guards/auth.guard';

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
        path:'auth',
        loadChildren: () => import('./features/auth/auth.routes').then((v) => v.routes),
        canActivate:[canActivateUnAuth]
    }
];
