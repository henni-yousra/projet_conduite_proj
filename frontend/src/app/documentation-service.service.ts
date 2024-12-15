import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DocumentationService {
  private apiUrl = 'http://localhost:5000/api/documentation'; // Your Spring Boot backend URL

  constructor(private http: HttpClient) {}

  saveDocumentation(projectId: number, documentationText: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/save`, { projectId, documentationText });
  }

  // Get the current documentation
  getDocumentation(projectId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/current/${projectId}`);
  }
  
}
