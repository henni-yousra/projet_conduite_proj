import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Project } from '../model/project';
import { IssuesComponent } from '../issues/issues.component';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButton } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialog } from '@angular/material/dialog';
import { TaskDialogComponent, TaskDialogData } from '../task-dialog/task-dialog.component';
import { MatListModule } from '@angular/material/list';


@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterModule, MatCardModule, MatGridListModule, MatButton, MatIconModule, FormsModule, MatCheckboxModule, MatListModule],
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css', '../../styles.css'],
})
export class TasksComponent {
  // link to a task should be : /project/
  project!: Project | null; // later on we will retrieve the tasks from the project
  tasks: { name: string, id: string, description: string }[] = [];
  issue: { name: string, id: string, description: string }[] = [];

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

    // this.issue = await this.reqSvc.getIssues(); // get
    // this.tasks = await this.reqSvc.getTasks();
    
	}

  openCreateTaskDialog(): void {
    const dialogRef = this.dialog.open(TaskDialogComponent, { data: {} }); //data: {  projectName: this.projectName() }

    dialogRef.afterClosed().subscribe((result: TaskDialogData) => {
      if (result) {
        this.tasks.push(
          {
            name: result.taskName,
            id: result.taskId,
            description: result.taskDescription
          }
        );
      }
    });
  }

  trackById(index: number, task: { name: string, id: string, description: string }): string {
		return task.id;
	}

  onTileClick(issue: { name: string, id: string, description: string }): void {
		const dialogRef = this.dialog.open(TaskDialogComponent, {
		  data: {
			issueName: issue.name,
			issueDescription: issue.description
		  }
		});
	
		dialogRef.afterClosed().subscribe((result: TaskDialogData) => {
		  if (result) {
			issue.name = result.taskName;
			issue.description = result.taskDescription;
		  }
		});
	  }


	// Function to navigate back to the dashboard page
	goBack() {
		// Navigate to the project page
		this.router.navigate(['/project', this.project?.id]);
	}


}
