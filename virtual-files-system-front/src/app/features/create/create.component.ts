import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { MatSnackBar } from '@angular/material/snack-bar'
import { DirectoriesService } from '../../shared/services/directories.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create',
  standalone: true,
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss'
})
export class CreateComponent {
  directoryService = inject(DirectoriesService);
  matSnackBar = inject(MatSnackBar);
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
