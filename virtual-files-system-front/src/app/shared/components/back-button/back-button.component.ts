import { Location } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-back-button',
  standalone: true,
  imports: [MatButtonModule],
  templateUrl: './back-button.component.html',
  styleUrl: './back-button.component.scss'
})
export class BackButtonComponent {
    location = inject(Location);

    goBack() {
      this.location.back();
    }
}
