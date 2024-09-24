import { Component, input, computed } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';
import { File } from '../../../../shared/interfaces/files.interface';

@Component({
  selector: 'app-card-file',
  standalone: true,
  imports: [RouterLink, MatCardModule],
  templateUrl: './card-file.component.html',
  styleUrl: './card-file.component.scss'
})
export class CardFileComponent {
  file = input.required<File>();

  fileName = computed(() => this.file().fileName);
}
