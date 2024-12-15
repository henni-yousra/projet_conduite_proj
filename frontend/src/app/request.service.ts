import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Project } from './model/project';
import { User } from './model/user';
import { catchError, lastValueFrom } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class RequestService {

	constructor(public http: HttpClient) { }

	async getProjects(): Promise<Project[]> {
		let res = this.http.get<Project[]>('/projects').toPromise().then(projects => projects ?? []);
		console.log("getProjects : ", res);
		return res;
	}

	async getUsers(): Promise<User[]> {
		let res = this.http.get<User[]>('/appusers').toPromise().then(users => users ?? []);
		console.log("getUsers : ", res);
		return res;
	}


	async getProject(id: number): Promise<Project> {
		let res = this.http.get<Project>(`/projects/${id}`).toPromise();
		return res as Promise<Project>;
	}

	async updateProject(project: Project): Promise<void> {
		return this.http.put<void>(`/projects/${project.id}`, project).toPromise();
	}


	async createProject(projectName: string, projectDescription: string) {

		let form = new FormData();
		form.append("name", projectName);
		form.append("description", projectDescription);

		const obs = this.http.post("projects/addProject", form, { responseType: 'text' });
		await lastValueFrom(obs);
	}


	async getUser(id: number): Promise<User> {
		let params = new HttpParams();
		params = params.append('id', id);

		const obs = this.http.get("appusers", { params });
		const data: any = await lastValueFrom(obs);

		console.log(data);

		return data;
	}

	async getMembers(projectId: number): Promise<{ members: User[] }> {
		const url = `/projects/${projectId}/getMembers`;
		console.log(`Fetching members for project ID: ${projectId}, URL: ${url}`);
		return this.http.get<{ members: User[] }>(url).toPromise()
			.then(response => {
				console.log('Fetched Members:', response);
				return response ?? { members: [] }; // Ensure we return an object with a `members` property
			})
			.catch(error => {
				console.error('Error fetching members:', error);
				return { members: [] }; // Return an empty array of members in case of an error
			});
	}

	async getUserProjects(userId: number): Promise<Project[]> {
		const url = `/appusers/${userId}/projects`;
		const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
		return this.http.get<Project[]>(url).toPromise().then(projects => projects ?? []);
	}
	

	async addMembersToProject(projectId: number, members: User[]): Promise<any> {
		const url = `/projects/${projectId}/addMembers`;
		const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
		console.log("addMembersToProject : ", members);
		return this.http.post(url, members, { headers }).toPromise();
	}

	async removeMembersFromProject(projectId: number, members: User[]): Promise<any> {
		const url = `/projects/${projectId}/removeMembers`;
		const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
		console.log("addMembersToProject : ", members);
		return this.http.post(url, members, { headers }).toPromise();
	}

	async deleteProject(id: number): Promise<void> {
		const url = `/projects/deleteProject/${id}`;
    	return this.http.delete<void>(url).toPromise();
	}

	/* async addIssuesToProject(projectId: number, issues: any): Promise<any> {
		const url = `/projects/${projectId}/addIssues`;
		const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
		return this.http.post(url, issues, { headers }).toPromise();
	} */
}