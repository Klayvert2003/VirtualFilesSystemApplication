import { Component, input, computed, inject } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';
import { File } from '../../../../shared/interfaces/files.interface';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { FilesService } from '../../../../shared/services/files.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../../../shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-card-file',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, RouterLink, MatIconModule, MatMenuModule],
  templateUrl: './card-file.component.html',
  styleUrl: './card-file.component.scss'
})
export class CardFileComponent {
  file = input.required<File>();

  dialog = inject(MatDialog);
  snackBar = inject(MatSnackBar);
  fileService = inject(FilesService);

  fileName = computed(() => this.file().fileName);

  onClickDelete(fileId: number): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: { name: this.fileName() }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.fileService.deleteFile(fileId).subscribe({
          next: () => {
            this.snackBar.open("Arquivo deletado!", "Fechar");

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
    });
  }
}
