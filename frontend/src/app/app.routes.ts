import {Routes} from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'order'
  },
  {
    path: 'order',
    loadChildren: () => import('./modules/order/order.routes')
      .then(r => r.orderRoutes)
  }
];
