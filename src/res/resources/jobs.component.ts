import { Component, OnInit } from '@angular/core';
import { Jobs } from './jobs.model';
import { JobsService } from './jobs.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class JobsComponent implements OnInit {

  jobss: Jobs[] = [];
  newJobs: Jobs = new Jobs();
  editedJobs: Jobs = new Jobs();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private jobsService: JobsService
  ) { }

  ngOnInit(): void {
    this.getJobss();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.jobsService.getJobssPages(page).subscribe({
        next: (data) => {
          this.jobss = data.content

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

  getJobss(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.jobsService.createJobs(this.newJobs).subscribe(() => {
      this.getJobss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newJobs = new Jobs();
  }

  onEdit(jobs: Jobs): void {
    this.editedJobs = jobs;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.jobsService.updateJobs(this.editedJobs.id, this.editedJobs).subscribe(() => {
      this.getJobss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedJobs = new Jobs();
  }

  onDelete(jobs: Jobs): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer jobs ?")) {
      this.jobsService.deleteJobs(jobs.id).subscribe(() => {
        this.getJobss();
      });
    }
  }
}
  
