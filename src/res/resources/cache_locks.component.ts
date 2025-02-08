import { Component, OnInit } from '@angular/core';
import { Cache_locks } from './cache_locks.model';
import { Cache_locksService } from './cache_locks.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cache_locks',
  templateUrl: './cache_locks.component.html',
  styleUrls: ['./cache_locks.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Cache_locksComponent implements OnInit {

  cache_lockss: Cache_locks[] = [];
  newCache_locks: Cache_locks = new Cache_locks();
  editedCache_locks: Cache_locks = new Cache_locks();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private cache_locksService: Cache_locksService
  ) { }

  ngOnInit(): void {
    this.getCache_lockss();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.cache_locksService.getCache_lockssPages(page).subscribe({
        next: (data) => {
          this.cache_lockss = data.content

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

  getCache_lockss(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.cache_locksService.createCache_locks(this.newCache_locks).subscribe(() => {
      this.getCache_lockss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newCache_locks = new Cache_locks();
  }

  onEdit(cache_locks: Cache_locks): void {
    this.editedCache_locks = cache_locks;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.cache_locksService.updateCache_locks(this.editedCache_locks.key, this.editedCache_locks).subscribe(() => {
      this.getCache_lockss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedCache_locks = new Cache_locks();
  }

  onDelete(cache_locks: Cache_locks): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer cache_locks ?")) {
      this.cache_locksService.deleteCache_locks(cache_locks.key).subscribe(() => {
        this.getCache_lockss();
      });
    }
  }
}
  
