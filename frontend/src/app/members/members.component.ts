import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { CommonModule } from '@angular/common';
import { Project } from '../model/project';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButton } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { UsersCardComponent } from '../users-card/users-card.component';


@Component({
	selector: 'app-members',
	standalone: true,
	imports: [RouterOutlet, CommonModule, RouterModule, MatCardModule, MatGridListModule, MatButton, MatIconModule, FormsModule, MatCheckboxModule],
	templateUrl: './members.component.html',
	styleUrl: './members.component.css'
})
export class MembersComponent implements OnInit {
	members: User[] = [];
	@Input() users: User[] = [];
	project!: Project | null;
	selectedUsers: User[] = [];


	constructor(private route: ActivatedRoute, private reqSvc: RequestService, private router: Router) { }

	async ngOnInit(): Promise<void> {
		const projectId = this.route.snapshot.paramMap.get('id');
		console.log('Project ID:', projectId);
	  
		if (projectId) {
			// Wait for project details
			this.project = await this.reqSvc.getProject(+projectId);
			this.project.id = +projectId; // Ensure project ID is a number
			console.log('Project:', this.project);
	  
			// Wait for members and access the members array from the response object
			const membersResponse = await this.reqSvc.getMembers(+projectId);
			this.members = membersResponse.members; // Now this.members will be an array of User
	  
			console.log('--Members Response:', this.members);
		}
	  
		// Wait for users
		const usersResponse = await this.reqSvc.getUsers();
		this.users = Array.isArray(usersResponse) ? usersResponse : [];
		console.log('--Users Response:', this.users);
		console.log('---Members:', this.members);
	  
		this.selectedUsers = [];
	}
	
	

	async reloadMembers(): Promise<void> {
		if (this.project?.id !== undefined) {
			const membersResponse = await this.reqSvc.getMembers(+this.project?.id);
			this.members = membersResponse.members; // Now this.members will be an array of User
		}
	}
	

	trackByMemberId(index: number, member: User): number {
		return member.id;
	}

	trackByUserId(index: number, user: User): number {
		return user.id;
	}

	addSelectedMembers(): void {
		this.selectedUsers = this.users.filter(user => user.selected);
		console.log('Selected Users:', this.selectedUsers);

		// Ensure members is initialized as an array
		if (!Array.isArray(this.members)) {
			this.members = [];
			console.log('Members initialized:', this.members);
		}

		// Add these members to the project
		/* if (this.project?.id !== undefined) {
			console.log('proj good');
			this.reqSvc.addMembersToProject(this.project.id, this.selectedUsers);
		}
		console.log('Members added to the project');

		this.selectedUsers.forEach(user => {
		if (!this.members.some(member => member.id === user.id)) {
			this.members.push(user);
		}
		}); */

		if (this.project?.id !== undefined) {
			this.reqSvc.addMembersToProject(this.project.id, this.selectedUsers)
				.then(async () => {
					// Re-fetch members after adding
					await this.reloadMembers();
				});
		}

		// Reset the selected property and reset the selected users
		this.users.forEach(user => user.selected = false);
		this.selectedUsers = [];
		console.log('Selected Users reset:', this.selectedUsers);
		console.log('Members :', this.members);
	}

	// Function to navigate back to the dashboard page
	goBack() {
		// Navigate to the project page
		this.router.navigate(['/project', this.project?.id]);
	}
}
