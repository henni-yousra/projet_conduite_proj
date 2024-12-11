import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { Project } from '../model/project';
import { text } from 'express';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-project-page',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterModule],
  templateUrl: './project-page.component.html',
  styleUrl: './project-page.component.css',
  providers:[RequestService]
})
export class ProjectPageComponent implements OnInit
{
  @Input() id!: number;
  project!: Project | null;
  users: User[] = [];

  constructor(private route: ActivatedRoute, private reqSvc: RequestService, private router: Router){ }

  ngOnInit(): void {
    const projectId = this.route.snapshot.paramMap.get('id');
    if (projectId) {
      this.reqSvc.getProject(+projectId).then((project) => {
        this.project = project;
        this.project.id = +projectId; // turns the string into a number
        console.log('Project:', this.project);
      });
    }

    this.reqSvc.getUsers().then((users) => {
      this.users = users;
    });
  }

  onTileClick(id: number | undefined)
  {
    //console.log("Clicked on project : ",this.project?.id);
    console.log("Clicked on project : ",id);
    console.log("--Clicked on project : ",this.project?.id);
    console.log("Clicked on project : ",this.project?.name);
    //nagivate to the page given as parameter
    //this.router.navigate([page, this.project?.id]);
  }

  // Function to navigate back to the dashboard page
  goBack() {
    // Navigate to the dashboard page
    this.router.navigate(['/dashboard']);
  }

  deleteProject() {
    // Delete the project
    if (this.project) {
      this.reqSvc.deleteProject(this.project?.id).then(() => {
        // Navigate to the dashboard page
        this.router.navigate(['/dashboard']);
      });
    }
  }

}
