
import { ChangeDetectorRef, Component, Directive, HostListener, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Project } from '../model/project';
import { HomeComponent } from '../home/home.component';
import { Router } from '@angular/router';
import { RequestService } from '../request.service';
import { User } from '../model/user';
import { UsersCardComponent } from '../users-card/users-card.component';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterOutlet, HomeComponent, UsersCardComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers:[RequestService]
})

export class DashboardComponent implements OnInit {

  constructor(private reqSvc: RequestService, private router: Router) 
  { 
  
  }

  projects: Project[] = [];
  users: User[] = [];
  //userName: string | null = '';

  ngOnInit()
  {
    //this.userName = localStorage.getItem('user_name');
    
    this.reqSvc.getProjects().then((res) => {
      this.projects = res;
      console.log("-Projects : ", this.projects);
      console.log("-res : ", res);
    });
    this.reqSvc.getUsers().then((res) => {
      this.users = res;
      console.log("-Users : ", this.users);
      console.log("-res : ", res);
    });
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