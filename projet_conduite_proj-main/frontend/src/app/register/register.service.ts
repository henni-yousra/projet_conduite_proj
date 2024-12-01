import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  private apiUrl = 'http://localhost:5000/register'; // replace with your backend API endpoint

  constructor(private http: HttpClient) {}

  // Method to register the user
  registerUser(userData: { username: string; email: string; password: string }): Observable<any> {
    return this.http.post(this.apiUrl, userData);
  }
}
