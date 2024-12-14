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


export interface TaskDialogData {
  taskName: string;
  taskId: string; // T1 ...
  taskDescription: string;
}

@Component({
  selector: 'app-task-dialog',
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
  templateUrl: './task-dialog.component.html',
  styleUrls: ['./task-dialog.component.css', '../../styles.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TaskDialogComponent {
  readonly dialogRef = inject(MatDialogRef<TaskDialogComponent>);
  readonly data = inject<TaskDialogData>(MAT_DIALOG_DATA);
}
