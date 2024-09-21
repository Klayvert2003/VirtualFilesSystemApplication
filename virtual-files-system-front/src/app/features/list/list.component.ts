import { Component, inject } from '@angular/core';
import { Directory } from '../../shared/interfaces/directory.interface';
import { DirectoriesService } from '../../shared/services/directories.service';
import { CardComponent } from './components/card/card.component';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [CardComponent, RouterLink, MatButtonModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {
    directories: Directory[] = [];

    directoriesService = inject(DirectoriesService);

    ngOnInit() {
      this.directoriesService.getAll().subscribe((directories) => {
        this.directories = directories;
      })
    }
}
