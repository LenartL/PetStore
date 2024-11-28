import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'store'
  },
  {
    path: 'store',
    loadChildren: () => import('./modules/store/store.routes')
      .then(r => r.storeRoutes)
  }
];
