import { Routes } from '@angular/router';
import { ListComponent } from './features/list/list.component';

export const routes: Routes = [
    {
        path: '',
        component: ListComponent
    },
    {
        path: 'create-directory',
        loadComponent: () => import('./features/create/create.component').then(
            m => m.CreateComponent
        )
    },
    {
        path: 'visualize-directory/:id',
        loadComponent: () => import('./features/list/components/card-details/card-details.component').then(
            m => m.CardDetailsComponent
        )
    }
];
