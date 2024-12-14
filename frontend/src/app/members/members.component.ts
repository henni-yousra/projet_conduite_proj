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
	usersToDisplay : User[] = [];


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
		this.usersToDisplay = this.users;
	}
	
	

	async reloadMembers(): Promise<void> {
		/**
		 * This function is used to reload the members array after adding members to the project.
		 * 'SCRUM_MASTER' : blue marine
		 * 'MEMBER' : soft beige
		 */
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

	async addSelectedMembers(): Promise<void> {
		this.selectedUsers = this.usersToDisplay.filter(user => user.selected);
		console.log('Selected Users:', this.selectedUsers);

		// Ensure members is initialized as an array
		if (!Array.isArray(this.members)) {
			this.members = [];
			console.log('Members initialized:', this.members);
		}

		if (this.project?.id !== undefined) {
			await this.reqSvc.addMembersToProject(this.project.id, this.selectedUsers)
				.then(async () => {
					// Re-fetch members after adding
					await this.reloadMembers();
				});
		}

		// remove the selectedUsers from the usersToDisplay array 
		this.usersToDisplay = this.usersToDisplay.filter(
			user => !this.selectedUsers.includes(user)
		);

		// Reset the selected property and reset the selected users			
		this.usersToDisplay.forEach(user => user.selected = false);
		this.selectedUsers = [];
		console.log('Selected Users reset:', this.selectedUsers);
		console.log('Members :', this.members);
	}

	async removeMembersFromProject(): Promise<void> {
		if (!this.project?.id || this.members.length === 0) {
			console.log('No project or members to remove.');
			return;
		}
		const membersToRemove = this.members.filter(member => member.selected);
		console.log('Members to Remove:', membersToRemove);
	
		if (membersToRemove.length === 0) {
			console.log('No members selected for removal.');
			return;
		}	
		await this.reqSvc.removeMembersFromProject(this.project.id, membersToRemove)
			.then(async () => {
				await this.reloadMembers();
			})
			.catch(err => {
				console.error('Error removing members:', err);
			});
		// add removed members back to the usersToDisplay array
		membersToRemove.forEach(member => member.selected = false);
		this.usersToDisplay = this.usersToDisplay.concat(membersToRemove);
		console.log('Members after removal:', this.members);
	}
	

	// Function to navigate back to the dashboard page
	goBack() {
		// Navigate to the project page
		this.router.navigate(['/project', this.project?.id]);
	}
}
