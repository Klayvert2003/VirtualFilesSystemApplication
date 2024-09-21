import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatInputModule } from '@angular/material/input'
import { DirectoriesService } from '../../shared/services/directories.service';

@Component({
  selector: 'app-create',
  standalone: true,
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss'
})
export class CreateComponent {
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

  onSubmit() {
    this.directoryService.saveRootDirectory(
      {
          directoryName: this.form.controls.directoryName.value
      }
    ).subscribe(() => {
      alert('Diretório criado!')
    })
  }
}
