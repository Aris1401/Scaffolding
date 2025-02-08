import { Component, OnInit } from '@angular/core';
import { Failed_jobs } from './failed_jobs.model';
import { Failed_jobsService } from './failed_jobs.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-failed_jobs',
  templateUrl: './failed_jobs.component.html',
  styleUrls: ['./failed_jobs.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Failed_jobsComponent implements OnInit {

  failed_jobss: Failed_jobs[] = [];
  newFailed_jobs: Failed_jobs = new Failed_jobs();
  editedFailed_jobs: Failed_jobs = new Failed_jobs();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private failed_jobsService: Failed_jobsService
  ) { }

  ngOnInit(): void {
    this.getFailed_jobss();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.failed_jobsService.getFailed_jobssPages(page).subscribe({
        next: (data) => {
          this.failed_jobss = data.content

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

  getFailed_jobss(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.failed_jobsService.createFailed_jobs(this.newFailed_jobs).subscribe(() => {
      this.getFailed_jobss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newFailed_jobs = new Failed_jobs();
  }

  onEdit(failed_jobs: Failed_jobs): void {
    this.editedFailed_jobs = failed_jobs;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.failed_jobsService.updateFailed_jobs(this.editedFailed_jobs.id, this.editedFailed_jobs).subscribe(() => {
      this.getFailed_jobss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedFailed_jobs = new Failed_jobs();
  }

  onDelete(failed_jobs: Failed_jobs): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer failed_jobs ?")) {
      this.failed_jobsService.deleteFailed_jobs(failed_jobs.id).subscribe(() => {
        this.getFailed_jobss();
      });
    }
  }
}
  
