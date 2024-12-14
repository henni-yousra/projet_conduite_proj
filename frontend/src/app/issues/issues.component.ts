import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Project } from '../model/project';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButton } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialog } from '@angular/material/dialog';
import { IssueDialogComponent, IssueDialogData } from '../issue-dialog/issue-dialog.component';
import { MatListModule } from '@angular/material/list';


@Component({
	selector: 'app-issues',
	standalone: true,
	imports: [RouterOutlet, CommonModule, RouterModule, MatCardModule, MatGridListModule, MatButton, MatIconModule, FormsModule, MatCheckboxModule, MatListModule],
	templateUrl: './issues.component.html',
	styleUrls: ['./issues.component.css', '../../styles.css'],
})
export class IssuesComponent {
	project!: Project | null;
	issues: { name: string, id: string ,description: string }[] = [];

	constructor(private route: ActivatedRoute, private reqSvc: RequestService, private router: Router, public dialog: MatDialog) { }


	async ngOnInit(): Promise<void> {
		const projectId = this.route.snapshot.paramMap.get('id');
		console.log('Project ID:', projectId);

		if (projectId) {
			// Wait for project details
			this.project = await this.reqSvc.getProject(+projectId);
			this.project.id = +projectId; // Ensure project ID is a number
			console.log('Project:', this.project);
		}

	}

	/* openDialog(): void {
		const dialogRef = this.dialog.open(IssueDialogComponent, {
		  width: '250px'
		});
	
		dialogRef.afterClosed().subscribe(result => {
		  if (result) {
			this.issues.push(result);
		  }
		});
	  } */
	openCreateIssueDialog(): void {
		const dialogRef = this.dialog.open(IssueDialogComponent, { data: {} }); //data: {  projectName: this.projectName() }
	
		dialogRef.afterClosed().subscribe((result: IssueDialogData) => {
			if (result) {
			// Emit the projectName and projectDescription as an object
			this.issues.push(
				{
					name: result.issueName,
					id: result.issueId,
					description: result.issueDescription
				}
			);
			}
		});
	}

	trackById(index: number, issue: { name: string, id: string, description: string }): string {
		return issue.id;
	}

	onTileClick(issue: { name: string, id: string, description: string }): void {
		const dialogRef = this.dialog.open(IssueDialogComponent, {
		  data: {
			issueName: issue.name,
			issueDescription: issue.description
		  }
		});
	
		dialogRef.afterClosed().subscribe((result: IssueDialogData) => {
		  if (result) {
			issue.name = result.issueName;
			issue.description = result.issueDescription;
		  }
		});
	  }


	// Function to navigate back to the dashboard page
	goBack() {
		// Navigate to the project page
		this.router.navigate(['/project', this.project?.id]);
	}

}
