import { Component, EventEmitter, inject, Input, model, Output, signal } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatButton } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { ProjectsCardComponent } from '../projects-card/projects-card.component'
import { CreateProjectDialogComponent, ProjectDialogData } from '../create-project-dialog/create-project-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Project } from '../model/projet';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatDividerModule, MatButton, MatIconModule, ProjectsCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  @Input() projects!: Project[];

  readonly dialog = inject(MatDialog);

  openCreateProjectDialog(): void {
    const dialogRef = this.dialog.open(CreateProjectDialogComponent, { data: {} }); //data: {  projectName: this.projectName() }

    dialogRef.afterClosed().subscribe((result: ProjectDialogData) => {
      if (result) {
        console.log(result.projectName);//
        this.createProject.emit(result.projectName);
      }
    });
  }

  @Output() createProject = new EventEmitter<string>();
}
