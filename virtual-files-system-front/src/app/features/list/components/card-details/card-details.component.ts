import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule, Router } from '@angular/router';
import { Directory } from '../../../../shared/interfaces/directory.interface';
import { DirectoriesService } from '../../../../shared/services/directories.service';
import { CardComponent } from "../card/card.component";
import { CardFileComponent } from '../card-file/card-file.component';

@Component({
  selector: 'app-card-details',
  standalone: true,
  imports: [RouterModule, CardComponent, CardFileComponent],
  templateUrl: './card-details.component.html',
  styleUrl: './card-details.component.scss'
})

export class CardDetailsComponent {
  directory: Directory | null = null;

  constructor(private route: ActivatedRoute, private router: Router, private directoryService: DirectoriesService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const directoryId = Number(params.get('id'));
      this.loadDirectory(directoryId);
    });
  }

  loadDirectory(directoryId: number) {
    this.directoryService.findById(directoryId).subscribe({
      next: (data) => {
        this.directory = data;
      },
      error: (err) => {
        console.error('Erro ao buscar diretório:', err);
      }
    });
  }
}
