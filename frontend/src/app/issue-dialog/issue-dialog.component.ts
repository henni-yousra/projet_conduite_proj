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


export interface IssueDialogData {
  issueName: string;
  issueId: string; // US1 ...
  issueDescription: string;
}


@Component({
  selector: 'app-issue-dialog',
  templateUrl: './issue-dialog.component.html',
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
  styleUrls: ['./issue-dialog.component.css', '../../styles.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class IssueDialogComponent {
  readonly dialogRef = inject(MatDialogRef<IssueDialogComponent>);
  readonly data = inject<IssueDialogData>(MAT_DIALOG_DATA);
  /* issue = { name: '', description: '' };

  constructor(public dialogRef: MatDialogRef<IssueDialogComponent>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    this.dialogRef.close(this.issue); 
  }*/
}