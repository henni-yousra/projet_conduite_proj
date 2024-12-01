import { Component, Directive, HostListener, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { Project } from './model/projet';
import { RequestService } from './request.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HomeComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers:[RequestService]
})
export class AppComponent
{

} 