import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Project } from './model/project';
import { User } from './model/user';
import { catchError, lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(public http: HttpClient) { }

  /* async getProjects() : Promise<Project[]>
  {
    let res : Project[] = []; 

    const obs = this.http.get("projects");
    const data: any = await lastValueFrom(obs);

    for(const proj of data)
    {
      res.push({id:proj.id, text: proj.name, description: proj.description})
    }

    return res;
  } */
  getProjects(): Promise<Project[]> {
    let res = this.http.get<Project[]>('/projects').toPromise().then(projects => projects ?? []);
    console.log("getProjects : ", res);
    return res;
  }

  async getUsers() : Promise<User[]>
  {
    
    /* let res : User[] = []; 
    let theData: any;

    const obs = this.http.get("appusers");
    const data: any = await lastValueFrom(obs);

    for(const user of data)
    {
      res.push({id:user.id, name: user.name, email: user.email, password: user.password, role: user.role})
    } */

    /* this.http.get<any[]>('http://localhost:5000/appusers')
      .subscribe(
        data => {
          theData = data;
        },
        error => {
          console.error('Error loading users', error);
        }
      );

      for(const user of theData)
        {
          res.push({id:user.id, name: user.name, email: user.email, password: user.password, role: user.role})
        }
         console.log("gtUsers");
        console.log(res);
        */
    //return res; 
    let res = this.http.get<User[]>('/appusers').toPromise().then(users => users ?? []);
    console.log("getUsers : ", res);
    return res;
  }


  async getProject(id: number) : Promise<Project>
  {
    // let res : Project; 

    /* let params = new HttpParams();
    params = params.append('id', id);

    const obs = this.http.get("projects", {params});
    const data: any = await lastValueFrom(obs);

    console.log(data);

    return data; */
    let res = this.http.get<Project>(`/projects/${id}`).toPromise();
    return res as Promise<Project>;
  }

  updateProject(project: Project): Promise<void> {
    return this.http.put<void>(`/projects/${project.id}`, project).toPromise();
  }

  
  async createProject(projectName: string, projectDescription: string) {

    let form = new FormData();
    form.append("name", projectName);
    form.append("description", projectDescription);

    const obs = this.http.post("projects/addProject", form, {responseType: 'text'});
    await lastValueFrom(obs);
  }

  /* async deleteProject(id: number) {
    let params = new HttpParams();
    params = params.append('id', id);

    const obs = this.http.delete("projects/deleteProject", {params, responseType: 'text'});
    await lastValueFrom(obs);
  } */



  async getUser(id: number) : Promise<User>
  {
    let params = new HttpParams();
    params = params.append('id', id);

    const obs = this.http.get("appusers", {params});
    const data: any = await lastValueFrom(obs);

    console.log(data);

    return data;
  }


  async addMembersToProject(projectId : number, projMembers: User[]) {
    console.log('Adding members to project:', projMembers);
    let form = new FormData();
    form.append("projectId", projectId.toString());
    form.append("members", JSON.stringify(projMembers));

    //console.log("projectId", projectId.toString());
    //console.log("members", JSON.stringify(projMembers));
    console.log("form", form);

    console.log("addMembersToProject");
    let addMembersUrl = `/projects/${projectId}/addMembers`;
    const obs = this.http.post(addMembersUrl, form, {responseType: 'text'});
    
    await lastValueFrom(obs);
  }
}