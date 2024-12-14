import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButton} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { Output, EventEmitter } from '@angular/core';
import { Project } from '../model/project';
import { Router } from '@angular/router';

@Component({
  selector: 'app-projects-card',
  standalone: true,
  imports: [MatCardModule, MatGridListModule, MatButton, MatIconModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './projects-card.component.html',
  styleUrl: './projects-card.component.css'
})
export class ProjectsCardComponent 
{
  constructor(private router: Router) 
  { 
  
  }

  @Input() projects!: Project[];

  @Output() createProject = new EventEmitter();

  onTileClick(id: number)
  {
    const project = this.projects.find((proj) => proj.id == id);
    console.log("Clicked on project : ",project?.id);
    this.router.navigate(['/project', id]);
  }
}
