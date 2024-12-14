import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  // send the login info to all components

  constructor(private http: HttpClient, private router: Router) {}

  onLogin() {
    const loginData = {
      email: this.username,  // Using 'username' as email
      password: this.password
    };

    console.log('Login data:', loginData);  // Log the data being sent

    this.http.post('http://localhost:5000/auth/login', loginData, { responseType: 'json' })
  .subscribe(
    (response: any) => {
      console.log('Login response:', response); // Log successful response
      if (response.message === 'Login successful.') {
        // Store the token in localStorage or sessionStorage
        localStorage.setItem('auth_token', response.token); 

        // retrieve the user name and store it in localStorage put print it on the dashboard
        this.http.get<{ name: string }>('http://localhost:5000/auth/getName', { responseType: 'json' })
          .subscribe((nameResponse) => {
            localStorage.setItem('user_name', nameResponse.name);
          });

        // Redirect to dashboard if login is successful
        this.router.navigate(['/dashboard']);
      } else {
        this.errorMessage = response.message; // Display error message if response indicates failure
      }
    },
    error => {
      console.error('Error logging in', error);
      // Display the error message from the backend or a general error message
      this.errorMessage = error.error.message || 'Login failed. Please check your credentials and try again!';
    }
  );

  }

  goToRegister() {
    // Navigate to the registration page
    this.router.navigate(['/register']);
  }
}
