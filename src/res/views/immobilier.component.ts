import { Component, OnInit } from '@angular/core';
import { Immobilier } from './immobilier.model';
import { ImmobilierService } from './immobilier.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-immobilier',
  templateUrl: './immobilier.component.html',
  styleUrls: ['./immobilier.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class ImmobilierComponent implements OnInit {

  immobiliers: Immobilier[] = [];
  newImmobilier: Immobilier = new Immobilier();
  editedImmobilier: Immobilier = new Immobilier();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private immobilierService: ImmobilierService
  ) { }

  ngOnInit(): void {
    this.getImmobiliers();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.immobilierService.getImmobiliersPages(page).subscribe({
        next: (data) => {
          this.immobiliers = data.content

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

  getImmobiliers(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.immobilierService.createImmobilier(this.newImmobilier).subscribe(() => {
      this.getImmobiliers();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newImmobilier = new Immobilier();
  }

  onEdit(immobilier: Immobilier): void {
    this.editedImmobilier = immobilier;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.immobilierService.updateImmobilier(this.editedImmobilier.idImmobilier, this.editedImmobilier).subscribe(() => {
      this.getImmobiliers();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedImmobilier = new Immobilier();
  }

  onDelete(immobilier: Immobilier): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer immobilier ?")) {
      this.immobilierService.deleteImmobilier(immobilier.idImmobilier).subscribe(() => {
        this.getImmobiliers();
      });
    }
  }
}
  
