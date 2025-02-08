import { Component, OnInit } from '@angular/core';
import { Cache } from './cache.model';
import { CacheService } from './cache.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cache',
  templateUrl: './cache.component.html',
  styleUrls: ['./cache.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class CacheComponent implements OnInit {

  caches: Cache[] = [];
  newCache: Cache = new Cache();
  editedCache: Cache = new Cache();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private cacheService: CacheService
  ) { }

  ngOnInit(): void {
    this.getCaches();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.cacheService.getCachesPages(page).subscribe({
        next: (data) => {
          this.caches = data.content

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

  getCaches(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.cacheService.createCache(this.newCache).subscribe(() => {
      this.getCaches();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newCache = new Cache();
  }

  onEdit(cache: Cache): void {
    this.editedCache = cache;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.cacheService.updateCache(this.editedCache.key, this.editedCache).subscribe(() => {
      this.getCaches();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedCache = new Cache();
  }

  onDelete(cache: Cache): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer cache ?")) {
      this.cacheService.deleteCache(cache.key).subscribe(() => {
        this.getCaches();
      });
    }
  }
}
  
