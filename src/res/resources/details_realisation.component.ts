import { Component, OnInit } from '@angular/core';
import { Details_realisation } from './details_realisation.model';
import { Details_realisationService } from './details_realisation.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { RealisationTravaux } from './realisationTravaux.model'
import { RealisationTravauxService } from './realisationTravaux.service'
import { TypeDeTravaux } from './typeDeTravaux.model'
import { TypeDeTravauxService } from './typeDeTravaux.service'
import { Unite } from './unite.model'
import { UniteService } from './unite.service'

@Component({
  selector: 'app-details_realisation',
  templateUrl: './details_realisation.component.html',
  styleUrls: ['./details_realisation.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Details_realisationComponent implements OnInit {

  details_realisations: Details_realisation[] = [];
  newDetails_realisation: Details_realisation = new Details_realisation();
  editedDetails_realisation: Details_realisation = new Details_realisation();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  realisationTravauxs : RealisationTravaux[] = []
  typeDeTravauxs : TypeDeTravaux[] = []
  unites : Unite[] = []

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private realisationTravauxService : RealisationTravauxService,
    private typeDeTravauxService : TypeDeTravauxService,
    private uniteService : UniteService,
    private details_realisationService: Details_realisationService
  ) { }

  ngOnInit(): void {
    this.getDetails_realisations();

    this.getRealisationTravauxs();
    this.getTypeDeTravauxs();
    this.getUnites();
  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.details_realisationService.getDetails_realisationsPages(page).subscribe({
        next: (data) => {
          this.details_realisations = data.content

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

  getDetails_realisations(): void {
    this.switchPage(1)
  }

    getRealisationTravauxs(): void {
        this.realisationTravauxService.getRealisationTravauxs().subscribe({
            next: (data) => {
                this.realisationTravauxs = data
            }
        });
    }
    getTypeDeTravauxs(): void {
        this.typeDeTravauxService.getTypeDeTravauxs().subscribe({
            next: (data) => {
                this.typeDeTravauxs = data
            }
        });
    }
    getUnites(): void {
        this.uniteService.getUnites().subscribe({
            next: (data) => {
                this.unites = data
            }
        });
    }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.details_realisationService.createDetails_realisation(this.newDetails_realisation).subscribe(() => {
      this.getDetails_realisations();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newDetails_realisation = new Details_realisation();
  }

  onEdit(details_realisation: Details_realisation): void {
    this.editedDetails_realisation = details_realisation;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.details_realisationService.updateDetails_realisation(this.editedDetails_realisation.drId, this.editedDetails_realisation).subscribe(() => {
      this.getDetails_realisations();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedDetails_realisation = new Details_realisation();
  }

  onDelete(details_realisation: Details_realisation): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer details_realisation ?")) {
      this.details_realisationService.deleteDetails_realisation(details_realisation.drId).subscribe(() => {
        this.getDetails_realisations();
      });
    }
  }
}
  
