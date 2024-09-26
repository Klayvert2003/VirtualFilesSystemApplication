import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Directory } from '../../../../shared/interfaces/directory.interface';
import { DirectoriesService } from '../../../../shared/services/directories.service';
import { CardComponent } from "../card/card.component";
import { CardFileComponent } from '../card-file/card-file.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-card-details',
  standalone: true,
  imports: [RouterModule, CardComponent, CardFileComponent, MatButtonModule],
  templateUrl: './card-details.component.html',
  styleUrl: './card-details.component.scss'
})

export class CardDetailsComponent {
  snackBar = inject(MatSnackBar);

  directory: Directory | null = null;

  constructor(private route: ActivatedRoute, private directoryService: DirectoriesService, private location: Location) {}

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

        if ((this.directory?.subDirectories ?? []).length === 0 && (this.directory?.files ?? []).length === 0) {
          this.snackBar.open('Pasta vazia', 'Fechar');
        }
      },
      error: (err) => {
        console.error('Erro ao buscar diretório:', err);
      }
    });
  }

  goBack() {
    this.location.back();
  }
}
