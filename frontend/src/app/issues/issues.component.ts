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
	/**
	 * Not finished unfortunately...
	 */
	project!: Project | null;
	issues: IssueDialogData[] = [];

	constructor(private route: ActivatedRoute, private reqSvc: RequestService, private router: Router, public dialog: MatDialog) { }


	async ngOnInit(): Promise<void> {
		const projectId = this.route.snapshot.paramMap.get('id');
		console.log('Project ID:', projectId);

		if (projectId) {
			// Wait for project details
			this.project = await this.reqSvc.getProject(+projectId);
			this.project.id = +projectId; // Ensure project ID is a number
			console.log('Project:', this.project);
			const issuesResponse = await this.reqSvc.getIssues(+projectId);
			this.issues = issuesResponse.issues; // Now this.issues will be an array of User
	  
			console.log('--Issues Response:', this.issues); 
		}
		
		// this.issues = await this.reqSvc.getIssues();
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
			this.issues.push(result);
			}
		});
	}

	trackById(index: number, issue: { name: string, id: string, description: string }): string {
		return issue.id;
	}

	async onTileClick(issue:IssueDialogData ): Promise<void> {
		const dialogRef = this.dialog.open(IssueDialogComponent, {
		  data: {}
		});
	
		dialogRef.afterClosed().subscribe(async (result: IssueDialogData) => {
		  if (result) {
			await this.reqSvc.addIssuesToProject(this.project?.id as number, this.issues);
		  }
		});
	  }


	// Function to navigate back to the dashboard page
	goBack() {
		// Navigate to the project page
		this.router.navigate(['/project', this.project?.id]);
	}

}
