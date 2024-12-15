import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssueDialogComponent } from './issue-dialog.component';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('IssueDialogComponent', () => {
  let component: IssueDialogComponent;
  let fixture: ComponentFixture<IssueDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IssueDialogComponent, MatDialogModule, BrowserAnimationsModule],
      providers: [{provide: MatDialogRef, useValue: {}},
        {provide: MAT_DIALOG_DATA, useValue: []}]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IssueDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
