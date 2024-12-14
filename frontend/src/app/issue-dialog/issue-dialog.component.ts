import {ChangeDetectionStrategy, Component, Inject, inject, model} from '@angular/core';
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
import { Router } from '@angular/router';


export interface IssueDialogData {
  issueName: string;
  issueId: string; // US1 ...
  issueDescription: string;
  projId: number;
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
  constructor(
    public dialogRef: MatDialogRef<IssueDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: IssueDialogData,
    private router: Router
  ) {}

  /* issue = { name: '', description: '' };

  constructor(public dialogRef: MatDialogRef<IssueDialogComponent>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    this.dialogRef.close(this.issue); 
  }*/

    // TODO : clicking on "Tasks" from the issue dialog should navigate to the tasks page
  navigateToTasks(): void {
    this.dialogRef.close();
    this.router.navigate([this.data.projId,'/tasks', this.data.issueId]);
  }
}