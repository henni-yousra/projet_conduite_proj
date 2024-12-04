import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProjectPageComponent } from './project-page/project-page.component';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'register', component: RegisterComponent }, 
    { path: 'project/:id', component: ProjectPageComponent }, 
    /**
     * paths for Besoins Tâches, Documentation, Release, Sprints of each project
     * { path: 'Besoins/:id', component: BesoinComponent },
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
