import { Component, computed, inject, input } from '@angular/core';
import { MatCardModule } from '@angular/material/card'
import { MatButtonModule } from '@angular/material/button'
import { MatIconModule } from '@angular/material/icon'
import { MatMenuModule } from '@angular/material/menu'
import { Directory } from '../../../../shared/interfaces/directory.interface';
import { RouterLink } from '@angular/router';
import { DirectoriesService } from '../../../../shared/services/directories.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../../../shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, RouterLink, MatIconModule, MatMenuModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  dialog = inject(MatDialog);
  snackBar = inject(MatSnackBar);
  directory = input.required<Directory>();
  directoryService = inject(DirectoriesService);
  directoryName = computed(() => this.directory().directoryName);

  onClickDelete(directoryId: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: { name: this.directoryName() }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.directoryService.deleteDirectory(directoryId).subscribe({
          next: () => {
            this.snackBar.open("Diretório deletado!", "Fechar");

            setTimeout(() => {
              location.reload();
            }, 1000);
          },
          error: (error) => {
            console.error("Erro ao deletar o diretório:", error);
            this.snackBar.open("Erro ao deletar o diretório!", "Fechar");
          }
        });
      }
    })
  }
}
