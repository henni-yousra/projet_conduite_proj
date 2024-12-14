import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

export interface User {
  name: string;
  email: string;
  role: string;
  password: string;
}


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})



export class RegisterComponent {
  user: User = { name: '', email: '', role: '', password: '' };
  errorMessage!: string;

  constructor(private http: HttpClient, private router: Router) {}
  
  registerUser() {
    this.http.post('http://localhost:5000/auth/register', this.user, { responseType: 'text' })
      .subscribe(
        response => {
          console.log('Registration successful', response);  // response will be plain text

          // Navigate to login page after successful registration
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Error registering user', error);
          this.errorMessage = 'This email is already registered. Please choose another one';
        }
      );
  }
  
  goToLogin() {
    // Navigate to the registration page
    this.router.navigate(['/login']);
  }
}