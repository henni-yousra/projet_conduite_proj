import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButton} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';

@Component({
  selector: 'app-user-card',
  standalone: true,
  imports: [MatCardModule, MatGridListModule, MatButton, MatIconModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './user-card.component.html',
  styleUrl: './user-card.component.css'
})
export class UserCardComponent {
  constructor(private router: Router) { }

  @Input() users!: User[];

  @Output() createUser = new EventEmitter();

  onTileClick(id: number) {
    this.router.navigate(['/user', id]);
  }
}
