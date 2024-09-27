import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { MatSnackBar } from '@angular/material/snack-bar'
import { DirectoriesService } from '../../shared/services/directories.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { BackButtonComponent } from "../../shared/components/back-button/back-button.component";

@Component({
  selector: 'app-create',
  standalone: true,
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, BackButtonComponent],
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss'
})
export class CreateComponent {
  directoryService = inject(DirectoriesService);
  activatedRoute = inject(ActivatedRoute);
  matSnackBar = inject(MatSnackBar);
  location = inject(Location);
  router = inject(Router);

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

  onSubmit() {
    const directoryId = Number(this.activatedRoute.snapshot.paramMap.get("id"));

    if (directoryId !== 0) {
      this.directoryService.saveSubDirectory(
        {
            directoryName: this.form.controls.directoryName.value,
            parentDirectory: {
              directoryId: directoryId,
              directoryName: '',
              subDirectories: [],
              files: []
            }
        }
      ).subscribe(() => {
        this.matSnackBar.open("Subdiretório criado com sucesso!", "Ok");
        this.location.back();
      })
    } else {
        this.directoryService.saveRootDirectory(
          {
              directoryName: this.form.controls.directoryName.value
          }
        ).subscribe(() => {
          this.matSnackBar.open(
            "Diretório criado com sucesso!",
            "Ok",
            {
              duration: 3000,
              horizontalPosition: 'right',
              verticalPosition: 'top'
            }
          );

          this.router.navigateByUrl('/');
        })
      }
  }
}
