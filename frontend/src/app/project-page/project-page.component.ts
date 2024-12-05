import { Component, Input } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Project } from '../model/project';
import { text } from 'express';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-project-page',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './project-page.component.html',
  styleUrl: './project-page.component.css',
  providers:[RequestService]
})
export class ProjectPageComponent 
{
  @Input() id!: number;

  project = {id:this.id, name:"", description:""}

  constructor(private reqSvc: RequestService, private router: Router)
  {
    this.reqSvc.getProjects().then((res) => 
      {
        this.project = res.find((proj) => proj.id == this.id)!;
      });
    //this.reqSvc.getProject(this.id).then((res) => this.project = res);
  }

  // Function to navigate back to the dashboard page
  goBack() {
    // Navigate to the dashboard page
    this.router.navigate(['/dashboard']);
  }

}
