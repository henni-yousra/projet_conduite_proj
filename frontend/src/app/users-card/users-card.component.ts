import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButton} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { Output, EventEmitter } from '@angular/core';
import { User } from '../model/user';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users-card',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatGridListModule, MatButton, MatIconModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './users-card.component.html',
  styleUrl: './users-card.component.css'
})
export class UsersCardComponent {
  constructor(private router: Router) { }

  @Input() users!: User[];

  @Output() createUser = new EventEmitter();


  trackByUserId(index: number, user: User): number {
    return user.id;
  }

  
  onTileClick(id: number) {
    const user = this.users.find((usr) => usr.id == id);
    console.log("Clicked on user : ", user?.id);
    this.router.navigate(['/appusers', id]);
  }
}
