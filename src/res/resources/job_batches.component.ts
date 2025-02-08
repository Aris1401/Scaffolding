import { Component, OnInit } from '@angular/core';
import { Job_batches } from './job_batches.model';
import { Job_batchesService } from './job_batches.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-job_batches',
  templateUrl: './job_batches.component.html',
  styleUrls: ['./job_batches.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Job_batchesComponent implements OnInit {

  job_batchess: Job_batches[] = [];
  newJob_batches: Job_batches = new Job_batches();
  editedJob_batches: Job_batches = new Job_batches();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private job_batchesService: Job_batchesService
  ) { }

  ngOnInit(): void {
    this.getJob_batchess();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.job_batchesService.getJob_batchessPages(page).subscribe({
        next: (data) => {
          this.job_batchess = data.content

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

  getJob_batchess(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.job_batchesService.createJob_batches(this.newJob_batches).subscribe(() => {
      this.getJob_batchess();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newJob_batches = new Job_batches();
  }

  onEdit(job_batches: Job_batches): void {
    this.editedJob_batches = job_batches;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.job_batchesService.updateJob_batches(this.editedJob_batches.id, this.editedJob_batches).subscribe(() => {
      this.getJob_batchess();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedJob_batches = new Job_batches();
  }

  onDelete(job_batches: Job_batches): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer job_batches ?")) {
      this.job_batchesService.deleteJob_batches(job_batches.id).subscribe(() => {
        this.getJob_batchess();
      });
    }
  }
}
  
