import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButton} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { Output, EventEmitter } from '@angular/core';
import { Project } from '../model/projet';

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
  @Input() projects!: Project[];

  @Output() createProject = new EventEmitter();
}
