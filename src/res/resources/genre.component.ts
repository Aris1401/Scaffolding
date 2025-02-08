import { Component, OnInit } from '@angular/core';
import { Genre } from './genre.model';
import { GenreService } from './genre.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class GenreComponent implements OnInit {

  genres: Genre[] = [];
  newGenre: Genre = new Genre();
  editedGenre: Genre = new Genre();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private genreService: GenreService
  ) { }

  ngOnInit(): void {
    this.getGenres();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.genreService.getGenresPages(page).subscribe({
        next: (data) => {
          this.genres = data.content

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

  getGenres(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.genreService.createGenre(this.newGenre).subscribe(() => {
      this.getGenres();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newGenre = new Genre();
  }

  onEdit(genre: Genre): void {
    this.editedGenre = genre;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.genreService.updateGenre(this.editedGenre.gId, this.editedGenre).subscribe(() => {
      this.getGenres();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedGenre = new Genre();
  }

  onDelete(genre: Genre): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer genre ?")) {
      this.genreService.deleteGenre(genre.gId).subscribe(() => {
        this.getGenres();
      });
    }
  }
}
  
