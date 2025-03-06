import { Routes } from "@angular/router";
import { UserProfilPageComponent } from "./pages/user-profil-page/user-profil-page.component";

export const routes: Routes = [
    {
        path:'',
        redirectTo:'profil',
        pathMatch:'full'
    },
    {
        path: 'profil',
        component:UserProfilPageComponent
    }

];