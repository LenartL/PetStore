import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgClass, NgIf} from "@angular/common";

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [
    NgClass,
    NgIf
  ],
  templateUrl: './button.component.html',
  styleUrl: './button.component.css'
})
export class ButtonComponent {
  @Input() btnTitle: string = '';
  @Output() clickEvent: EventEmitter<any> = new EventEmitter<any>();
  buttonClass = '';

  click($event: MouseEvent) {
    $event.preventDefault();
    this.clickEvent.emit();
  }
}
