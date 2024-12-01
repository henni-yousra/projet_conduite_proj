
import { ChangeDetectorRef, Component, Directive, HostListener, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Project } from '../model/projet';
import { HomeComponent } from '../home/home.component';
import { Router } from '@angular/router';
import { RequestService } from '../request.service';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterOutlet, HomeComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers:[RequestService]
})

export class DashboardComponent implements OnInit {

  constructor(private reqSvc: RequestService, private router: Router, private changeDetectorRef: ChangeDetectorRef) 
  { 
  
  }

  projects: Project[] = [];

  ngOnInit()
  {
    this.reqSvc.getProjects().then((res) => this.projects = res);
  }
  onCreateProject(projectData: { projectName: string, projectDescription: string }) {
    this.reqSvc.createProject(projectData.projectName, projectData.projectDescription).then(() => {
      this.reqSvc.getProjects().then((res) => {
        this.projects = res;
      });
    });
  }
  
  logout() {
    // Navigate to the registration page
    this.router.navigate(['/login']);
  }
}