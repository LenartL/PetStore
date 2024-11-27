import {Component, Input} from '@angular/core';
import {RouterLink} from "@angular/router";
import {NgbNavModule} from "@ng-bootstrap/ng-bootstrap";

export interface HeaderNavigation {
  linkName: string,
  url: string[],
  title?: string
}

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink,
    NgbNavModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  @Input() navigation!: HeaderNavigation[];
  active = 0;


}
