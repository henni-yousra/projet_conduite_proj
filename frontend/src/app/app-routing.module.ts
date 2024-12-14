import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProjectPageComponent } from './project-page/project-page.component';
import { UsersCardComponent } from './users-card/users-card.component';
import { MembersComponent } from './members/members.component';
import { IssuesComponent } from './issues/issues.component';
import { TasksComponent } from './tasks/tasks.component';

export const routes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    { path: 'login', title: 'Login', component: LoginComponent },
    { path: 'dashboard', title:'Dashboard', component: DashboardComponent },
    { path: 'register', title:'Registration', component: RegisterComponent }, 
    { path: 'project/:id', title:'Project Page', component: ProjectPageComponent }, 
    { path: 'members/:id', title:'Project Members', component: MembersComponent },
    { path: 'issues/:id', title:'Project Issues', component: IssuesComponent },
    { path: 'tasks/:id', title: 'Issue Tasks',component: TasksComponent },
    { path : ':id/tasks/this.data.id', title: 'Issue Tasks', component: TasksComponent },
    /**
     * paths for Besoins Tâches, Documentation, Release, Sprints of each project
     * { path: 'Tâches/:id', component: TâcheComponent },
     * { path: 'Documentation/:id', component: DocumentationComponent },
     * { path: 'Release/:id', component: ReleaseComponent },
     * { path: 'Sprints/:id', component: SprintComponent },
     */
    //{ path: '**', redirectTo: 'login' }, // Fallback route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
