import { Component, Directive, HostListener, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { HttpClient } from '@angular/common/http';
import { Project } from './model/projet';
import { RequestService } from './request.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers:[RequestService]
})
export class AppComponent implements OnInit {

  constructor(private reqSvc: RequestService) {}

  title = 'front';

  projects: Project[] = [];

  ngOnInit()
  {
    this.reqSvc.getProjects().then((res) => this.projects = res);
  }

  onCreateProject(projectName: string) 
  {
    this.reqSvc.createProject(projectName);
  }
}