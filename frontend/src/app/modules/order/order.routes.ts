import {Routes} from '@angular/router';
import {HomePageComponent} from "./page/home-page/home-page.component";
import {OrderListComponent} from "./component/order-list/order-list.component";

export const orderRoutes: Routes = [
  {
    path: '',
    component: HomePageComponent,
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'order-list'
      },
      {
        path: 'order-list',
        component: OrderListComponent
      }
    ]
  },

];
