import { Routes } from '@angular/router';
import { ArticlePagesComponent } from './pages/article-pages/article-pages.component';
import { ArticleCreationPageComponent } from './pages/article-creation-page/article-creation-page.component';
import { ArticleDetailsPageComponent } from './pages/article-details-page/article-details-page.component';

export const routes: Routes = [
    {
        path: '',
        component:ArticlePagesComponent
    },
    {
        path:':id',
        component: ArticleDetailsPageComponent,
    },
    {
        path:'create',
        component: ArticleCreationPageComponent
    }
];
