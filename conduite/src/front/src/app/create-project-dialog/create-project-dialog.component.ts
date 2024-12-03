import {ChangeDetectionStrategy, Component, inject, model} from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

export interface ProjectDialogData {
  projectName: string;
}

@Component({
  selector: 'create-project-dialog',
  templateUrl: 'create-project-dialog.component.html',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreateProjectDialogComponent {
  readonly dialogRef = inject(MatDialogRef<CreateProjectDialogComponent>);
  readonly data = inject<ProjectDialogData>(MAT_DIALOG_DATA);
  //readonly projectName = model(this.data.projectName);
}
