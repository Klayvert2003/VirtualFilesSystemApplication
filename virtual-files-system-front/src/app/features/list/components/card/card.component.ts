import { Component, computed, input, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card'
import { MatButtonModule } from '@angular/material/button'
import { Directory } from '../../../../shared/interfaces/directory.interface';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
    directory = input.required<Directory>();

    directoryName = computed(() => this.directory().directoryName);
}
