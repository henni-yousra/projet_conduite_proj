
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

  onCreateProject(projectName: string) 
  {
    this.reqSvc.createProject(projectName).then(() => 
    {
      this.reqSvc.getProjects().then((res) =>  
        {
          this.projects = res;
          //this.changeDetectorRef.detectChanges();
        });
    })

  }
  logout() {
    // Navigate to the registration page
    this.router.navigate(['/login']);
  }
}