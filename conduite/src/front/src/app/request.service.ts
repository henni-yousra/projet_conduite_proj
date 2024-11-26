import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Project } from './model/projet';
import { catchError, lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(public http: HttpClient) { }

  async getProjects() : Promise<Project[]>
  {
    let res : Project[] = []; 

    const obs = this.http.get("projects");
    const data: any = await lastValueFrom(obs);

    const projects_data = data["_embedded"]["projects"];

    for(const proj of projects_data)
    {
      res.push({text: proj.name, description: proj.description})
    }

    return res;
  }

  createProject(projectName: string) {

    let form = new FormData();
    form.append("name", projectName);
    form.append("description", projectName);

    this.http.post("projects/addProject", form).subscribe(
      (res) => console.log(res)
    );
  }
}
