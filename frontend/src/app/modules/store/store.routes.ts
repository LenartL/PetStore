import {Routes} from '@angular/router';
import {HomePageComponent} from "./page/home-page/home-page.component";
import {OrderListComponent} from "./component/order-list/order-list.component";
import {OrderFormComponent} from "./component/order-form/order-form.component";

export const storeRoutes: Routes = [
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
      },
      {
        path: 'order-new',
        component: OrderFormComponent
      },
      {
        path: 'order/:id',
        component: OrderFormComponent
      }
    ]
  },

];
