import { Component, OnInit } from '@angular/core';
import { Sessions } from './sessions.model';
import { SessionsService } from './sessions.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class SessionsComponent implements OnInit {

  sessionss: Sessions[] = [];
  newSessions: Sessions = new Sessions();
  editedSessions: Sessions = new Sessions();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private sessionsService: SessionsService
  ) { }

  ngOnInit(): void {
    this.getSessionss();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.sessionsService.getSessionssPages(page).subscribe({
        next: (data) => {
          this.sessionss = data.content

          // Pagination
          this.totalOfPages = data.totalPages
          this.totalOfElements = data.totalElements
          this.currentPage = data.number

          this.isFirstPage = data.first
          this.isLastPage = data.last
        }
      })
    }

    previousPage() {
      let previousPageNumber = this.currentPage;
      if (previousPageNumber < 1) previousPageNumber = 1;

      this.switchPage(previousPageNumber);
    }

    nextPage() {
      let nextPageNumber = this.currentPage + 2;
      if (nextPageNumber > this.totalOfPages) nextPageNumber = this.totalOfPages;

      this.switchPage(nextPageNumber);
    }
    // End pagination

  getSessionss(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.sessionsService.createSessions(this.newSessions).subscribe(() => {
      this.getSessionss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newSessions = new Sessions();
  }

  onEdit(sessions: Sessions): void {
    this.editedSessions = sessions;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.sessionsService.updateSessions(this.editedSessions.id, this.editedSessions).subscribe(() => {
      this.getSessionss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedSessions = new Sessions();
  }

  onDelete(sessions: Sessions): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer sessions ?")) {
      this.sessionsService.deleteSessions(sessions.id).subscribe(() => {
        this.getSessionss();
      });
    }
  }
}
  
