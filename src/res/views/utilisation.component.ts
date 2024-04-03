import { Component, OnInit } from '@angular/core';
import { Utilisation } from './utilisation.model';
import { UtilisationService } from './utilisation.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { Immobilier } from './immobilier.model'
import { ImmobilierService } from './immobilier.service'

@Component({
  selector: 'app-utilisation',
  templateUrl: './utilisation.component.html',
  styleUrls: ['./utilisation.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class UtilisationComponent implements OnInit {

  utilisations: Utilisation[] = [];
  newUtilisation: Utilisation = new Utilisation();
  editedUtilisation: Utilisation = new Utilisation();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  immobiliers : Immobilier[] = []

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private immobilierService : ImmobilierService,
    private utilisationService: UtilisationService
  ) { }

  ngOnInit(): void {
    this.getUtilisations();

    this.getImmobiliers();
  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.utilisationService.getUtilisationsPages(page).subscribe({
        next: (data) => {
          this.utilisations = data.content

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

  getUtilisations(): void {
    this.switchPage(1)
  }

    getImmobiliers(): void {
        this.immobilierService.getImmobiliers().subscribe({
            next: (data) => {
                this.immobiliers = data
            }
        });
    }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.utilisationService.createUtilisation(this.newUtilisation).subscribe(() => {
      this.getUtilisations();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newUtilisation = new Utilisation();
  }

  onEdit(utilisation: Utilisation): void {
    this.editedUtilisation = utilisation;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.utilisationService.updateUtilisation(this.editedUtilisation.idUtilisation, this.editedUtilisation).subscribe(() => {
      this.getUtilisations();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedUtilisation = new Utilisation();
  }

  onDelete(utilisation: Utilisation): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer utilisation ?")) {
      this.utilisationService.deleteUtilisation(utilisation.idUtilisation).subscribe(() => {
        this.getUtilisations();
      });
    }
  }
}
  
