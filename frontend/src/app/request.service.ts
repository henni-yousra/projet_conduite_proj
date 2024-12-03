import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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

    for(const proj of data)
    {
      res.push({id:proj.id, text: proj.name, description: proj.description})
    }

    return res;
  }

  async getProject(id: number) : Promise<Project>
  {
    // let res : Project; 

    let params = new HttpParams();
    params = params.append('id', id);

    const obs = this.http.get("projects", {params});
    const data: any = await lastValueFrom(obs);

    console.log(data);

    return data;
  }

  async createProject(projectName: string, projectDescription: string) {

    let form = new FormData();
    form.append("name", projectName);
    form.append("description", projectDescription);

    const obs = this.http.post("projects/addProject", form, {responseType: 'text'});
    await lastValueFrom(obs);
  }
}