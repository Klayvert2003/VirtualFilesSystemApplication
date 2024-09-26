import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DirectoriesService } from '../../shared/services/directories.service';
import { Directory } from '../../shared/interfaces/directory.interface';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';

@Component({
  selector: 'app-update-directory',
  standalone: true,
  imports: [MatLabel, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './update-directory.component.html',
  styleUrl: './update-directory.component.scss'
})
export class UpdateDirectoryComponent implements OnInit {
    directoryId: number = 0;
    directory: Directory | null = null;

    location = inject(Location);
    snackBar = inject(MatSnackBar);
    activatedRoute = inject(ActivatedRoute);
    directoryService = inject(DirectoriesService);

    form = new FormGroup(
      {
        directoryName: new FormControl<string>(
          '',
          {
            nonNullable: true,
            validators: Validators.required
          }
        )
      }
    );

    ngOnInit(): void {
        this.directoryId = Number(this.activatedRoute.snapshot.paramMap.get("id"));

        this.directoryService.findById(this.directoryId).subscribe({
          next: (data) => {
            this.directory = data;
          }
        })
    }

    onSubmit() {
        const updatedDirectory: Directory = {
            directoryId: this.directoryId,
            directoryName: this.form.controls.directoryName.value,
            parentDirectory: this.directory?.parentDirectory === null ? null : {
              directoryId: Number(this.directory?.parentDirectory?.directoryId),
              directoryName: '',
              parentDirectory: null,
              subDirectories: [],
              files: []
            },
            subDirectories: [],
            files: []
        };

        this.directoryService.updateDirectory(updatedDirectory).subscribe({
            next: () => {
              this.snackBar.open("Diretório atualizado com sucesso!", "Ok");
              this.location.back();;
            }
        });
    }
}
