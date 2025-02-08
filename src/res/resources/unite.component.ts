import { Component, OnInit } from '@angular/core';
import { Unite } from './unite.model';
import { UniteService } from './unite.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-unite',
  templateUrl: './unite.component.html',
  styleUrls: ['./unite.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class UniteComponent implements OnInit {

  unites: Unite[] = [];
  newUnite: Unite = new Unite();
  editedUnite: Unite = new Unite();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private uniteService: UniteService
  ) { }

  ngOnInit(): void {
    this.getUnites();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.uniteService.getUnitesPages(page).subscribe({
        next: (data) => {
          this.unites = data.content

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

  getUnites(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.uniteService.createUnite(this.newUnite).subscribe(() => {
      this.getUnites();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newUnite = new Unite();
  }

  onEdit(unite: Unite): void {
    this.editedUnite = unite;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.uniteService.updateUnite(this.editedUnite.utId, this.editedUnite).subscribe(() => {
      this.getUnites();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedUnite = new Unite();
  }

  onDelete(unite: Unite): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer unite ?")) {
      this.uniteService.deleteUnite(unite.utId).subscribe(() => {
        this.getUnites();
      });
    }
  }
}
  
