import { Component } from '@angular/core';
import { DocumentationService } from '../documentation-service.service';

@Component({
  selector: 'app-documentation',
  templateUrl: './documentation.component.html',
  styleUrls: ['./documentation.component.css']
})

export class DocumentationComponent {
  documentationText: string = '';
  projectId: number = 1;

  constructor(private documentationService: DocumentationService) {}

  saveDocumentation(): void {
    this.documentationService.saveDocumentation(this.projectId, this.documentationText)
      .subscribe(response => {
        console.log('Documentation saved:', response);
      }, error => {
        console.error('Error saving documentation:', error);
      });
  }

  getDocumentation(): void {
    this.documentationService.getDocumentation(this.projectId)
      .subscribe(
        response => {
          this.documentationText = response.documentationText ;
        },
        error => {
          console.error('Error fetching documentation:', error);
        }
      );
  }

  ngOnInit(): void {
    this.getDocumentation(); // Fetch the documentation when the component loads
  }
}
