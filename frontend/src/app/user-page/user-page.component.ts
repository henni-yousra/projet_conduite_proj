import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { User } from '../model/user';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';
import { Project } from '../model/project';
import { CommonModule } from '@angular/common';


@Component({
	selector: 'app-user-page',
	standalone: true,
	imports: [RouterOutlet, CommonModule, RouterModule],
	templateUrl: './user-page.component.html',
	styleUrl: './user-page.component.css',
	providers: [RequestService]
})
export class UserPageComponent {
	@Input() id!: number;
	user!: User | null;
	projects: Project[] = []; // projcects that the user is a part of


	constructor(private route: ActivatedRoute, private reqSvc: RequestService, private router: Router) { }

	ngOnInit(): void {
		const userId = this.route.snapshot.paramMap.get('id');
		if (userId) {
			this.reqSvc.getUser(+userId).then((user) => {
				this.user = user;
				this.user.id = +userId; // turns the string into a number
				console.log('User:', this.user);
			});
		}

		this.reqSvc.getProjects().then((projects) => {
			this.projects = projects;
		});
	}
}
