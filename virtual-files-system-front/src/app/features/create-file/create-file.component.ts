import { Component, inject } from '@angular/core';
import { FilesService } from '../../shared/services/files.service';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { MatSnackBar } from '@angular/material/snack-bar'
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-create-file',
  standalone: true,
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './create-file.component.html',
  styleUrl: './create-file.component.scss'
})
export class CreateFileComponent {
    filesService = inject(FilesService);
    activatedRoute = inject(ActivatedRoute);
    matSnackBar = inject(MatSnackBar);
    location = inject(Location);
    router = inject(Router);

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

    onSubmit() {
      const directoryId = Number(this.activatedRoute.snapshot.paramMap.get("directoryId"));
  
      if (directoryId !== null) {
        this.filesService.saveFile(
          {
              fileName: this.form.controls.fileName.value,
              directory: {
                directoryId: directoryId,
                directoryName: '',
                subDirectories: [],
                files: []
              }
          }
        ).subscribe(() => {
          this.matSnackBar.open(
            "Arquivo criado com sucesso!",
            "Ok",
            {
              duration: 3000,
              horizontalPosition: 'right',
              verticalPosition: 'top'
            }
          );
  
          this.location.back();
        });
      }
    }
}
