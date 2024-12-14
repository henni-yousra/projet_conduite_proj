import { Component, EventEmitter, inject, Input, model, Output, signal } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatButton } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { ProjectsCardComponent } from '../projects-card/projects-card.component';
import { CreateProjectDialogComponent, ProjectDialogData } from '../create-project-dialog/create-project-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Project } from '../model/project';
import { User } from '../model/user';
import { UsersCardComponent } from '../users-card/users-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatDividerModule, MatButton, MatIconModule, ProjectsCardComponent, UsersCardComponent ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  @Input() projects!: Project[];
  @Input() users!: User[];

  readonly dialog = inject(MatDialog);
  
    // Output EventEmitter with object containing projectName and projectDescription
    @Output() createProject = new EventEmitter<{ projectName: string, projectDescription: string }>();


  openCreateProjectDialog(): void {
    const dialogRef = this.dialog.open(CreateProjectDialogComponent, { data: {} }); //data: {  projectName: this.projectName() }

    dialogRef.afterClosed().subscribe((result: ProjectDialogData) => {
      if (result) {
        // Emit the projectName and projectDescription as an object
        this.createProject.emit({
          projectName: result.projectName,
          projectDescription: result.projectDescription
        });
      }
    });
  }


}
