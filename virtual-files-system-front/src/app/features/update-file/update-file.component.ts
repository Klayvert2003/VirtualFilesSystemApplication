import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { File } from '../../shared/interfaces/files.interface';
import { FilesService } from '../../shared/services/files.service';

@Component({
  selector: 'app-update-file',
  standalone: true,
  imports: [MatLabel, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './update-file.component.html',
  styleUrl: './update-file.component.scss'
})
export class UpdateFileComponent {
  fileId: number = 0;
  file: File | null = null;

  location = inject(Location);
  snackBar = inject(MatSnackBar);
  activatedRoute = inject(ActivatedRoute);
  fileService = inject(FilesService);

  form = new FormGroup(
    {
      fileName: new FormControl<string>(
        '',
        {
          nonNullable: true,
          validators: Validators.required
        }
      )
    }
  );

  ngOnInit(): void {
      this.fileId = Number(this.activatedRoute.snapshot.paramMap.get("id"));

      this.fileService.findById(this.fileId).subscribe({
        next: (data) => {
          this.file = data;
        }
      })
  }

  onSubmit() {
      const updatedfile: File = {
          id: this.fileId,
          fileName: this.form.controls.fileName.value,
          directory: {
            directoryId: Number(this.file?.directory?.directoryId),
            directoryName: '',
            parentDirectory: this.file?.directory?.parentDirectory === null ? null : {
              directoryId: Number(this.file?.directory?.parentDirectory?.directoryId),
              directoryName: '',
              parentDirectory: null,
              subDirectories: [],
              files: []
            },
            subDirectories: [],
            files: []
          }
      };

      this.fileService.updateFile(updatedfile).subscribe({
          next: () => {
            this.snackBar.open("Arquivo atualizado com sucesso!", "Ok");
            this.location.back();;
          }
      });
  }
}
