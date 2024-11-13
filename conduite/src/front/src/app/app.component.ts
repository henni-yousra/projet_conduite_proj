import { Component, Directive, HostListener, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ConfigService } from './config.service';
import { HttpClient } from '@angular/common/http';
import { Project } from './model/projet';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers:[ConfigService]
})
export class AppComponent implements OnInit {
  http: HttpClient;

  constructor(private config: ConfigService) 
  { 
    this.http = config.http;
  }

  title = 'front';

  projects: Project[] = [];

  ngOnInit()
  {
    this.http.get("projects").subscribe((data: any) => 
      {
        let res : Project[] = []; 
        const projects_data = data["_embedded"]["projects"];

        for(const proj of projects_data)
        {
          res.push({text: proj.name, description: proj.description})
        }
        
        this.projects = res;
      });
  }

  onCreateProject(projectName: string) {

    let form = new FormData();
    form.append("name", projectName);
    form.append("description", projectName);

    this.http.post("projects/addProject", form).subscribe(
      (res) => console.log(res)
    );
  }
}