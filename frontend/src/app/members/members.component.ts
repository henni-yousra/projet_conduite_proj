import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule, RouterOutlet } from '@angular/router';
import { RequestService } from '../request.service';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { CommonModule } from '@angular/common';
import { Project } from '../model/project';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButton} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
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
  @Input() members: User[] = [];
  @Input() users: User[] = [];
  project!: Project | null;


  constructor(private route: ActivatedRoute, private reqSvc: RequestService, private router: Router){ }

  ngOnInit(): void {
    const projectId = this.route.snapshot.paramMap.get('id');
    console.log('Project ID:', projectId);
    if (projectId) {
      this.reqSvc.getProject(+projectId).then((project) => {
        this.project = project;
        this.project.id = +projectId; // turns the string into a number
        console.log('Project:', this.project);
      });
    }

    // get all users
    this.reqSvc.getUsers().then((users) => {
      this.users = users;
    });

  }

  trackByMemberId(index: number, member: User): number {
    return member.id;
  }

  trackByUserId(index: number, user: User): number {
    return user.id;
  }

  addSelectedMembers(): void {
    const selectedUsers = this.users.filter(user => user.selected);
    console.log('Selected Users:', selectedUsers);

    //check if members is defined
    if (!this.members) {
      this.members = [];
      console.log('Members:', this.members);
    }

    selectedUsers.forEach(user => {
      if (!this.members.some(member => member.id === user.id)) {
        this.members.push(user);
      }
    });

    console.log('Selected Members :', this.members);

    // add these members to the project
    if (this.project?.id !== undefined) {
      this.reqSvc.addMembersToProject(this.project.id, this.members);
    }

    // reset the selected property
    this.users.forEach(user => user.selected = false);
  }

  // Function to navigate back to the dashboard page
  goBack() {
    // Navigate to the project page
    this.router.navigate(['/project', this.project?.id]);
  }
}
