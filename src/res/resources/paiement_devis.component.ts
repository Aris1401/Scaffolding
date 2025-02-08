import { Component, OnInit } from '@angular/core';
import { Paiement_devis } from './paiement_devis.model';
import { Paiement_devisService } from './paiement_devis.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { RealisationTravaux } from './realisationTravaux.model'
import { RealisationTravauxService } from './realisationTravaux.service'

@Component({
  selector: 'app-paiement_devis',
  templateUrl: './paiement_devis.component.html',
  styleUrls: ['./paiement_devis.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Paiement_devisComponent implements OnInit {

  paiement_deviss: Paiement_devis[] = [];
  newPaiement_devis: Paiement_devis = new Paiement_devis();
  editedPaiement_devis: Paiement_devis = new Paiement_devis();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  realisationTravauxs : RealisationTravaux[] = []

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private realisationTravauxService : RealisationTravauxService,
    private paiement_devisService: Paiement_devisService
  ) { }

  ngOnInit(): void {
    this.getPaiement_deviss();

    this.getRealisationTravauxs();
  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.paiement_devisService.getPaiement_devissPages(page).subscribe({
        next: (data) => {
          this.paiement_deviss = data.content

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

  getPaiement_deviss(): void {
    this.switchPage(1)
  }

    getRealisationTravauxs(): void {
        this.realisationTravauxService.getRealisationTravauxs().subscribe({
            next: (data) => {
                this.realisationTravauxs = data
            }
        });
    }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.paiement_devisService.createPaiement_devis(this.newPaiement_devis).subscribe(() => {
      this.getPaiement_deviss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newPaiement_devis = new Paiement_devis();
  }

  onEdit(paiement_devis: Paiement_devis): void {
    this.editedPaiement_devis = paiement_devis;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.paiement_devisService.updatePaiement_devis(this.editedPaiement_devis.pdId, this.editedPaiement_devis).subscribe(() => {
      this.getPaiement_deviss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedPaiement_devis = new Paiement_devis();
  }

  onDelete(paiement_devis: Paiement_devis): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer paiement_devis ?")) {
      this.paiement_devisService.deletePaiement_devis(paiement_devis.pdId).subscribe(() => {
        this.getPaiement_deviss();
      });
    }
  }
}
  
