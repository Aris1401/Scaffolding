import { Component, OnInit } from '@angular/core';
import { Fournisseur } from './fournisseur.model';
import { FournisseurService } from './fournisseur.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-fournisseur',
  templateUrl: './fournisseur.component.html',
  styleUrls: ['./fournisseur.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class FournisseurComponent implements OnInit {

  fournisseurs: Fournisseur[] = [];
  newFournisseur: Fournisseur = new Fournisseur();
  editedFournisseur: Fournisseur = new Fournisseur();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private fournisseurService: FournisseurService
  ) { }

  ngOnInit(): void {
    this.getFournisseurs();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.fournisseurService.getFournisseursPages(page).subscribe({
        next: (data) => {
          this.fournisseurs = data.content

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

  getFournisseurs(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.fournisseurService.createFournisseur(this.newFournisseur).subscribe(() => {
      this.getFournisseurs();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newFournisseur = new Fournisseur();
  }

  onEdit(fournisseur: Fournisseur): void {
    this.editedFournisseur = fournisseur;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.fournisseurService.updateFournisseur(this.editedFournisseur.idFournisseur, this.editedFournisseur).subscribe(() => {
      this.getFournisseurs();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedFournisseur = new Fournisseur();
  }

  onDelete(fournisseur: Fournisseur): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer fournisseur ?")) {
      this.fournisseurService.deleteFournisseur(fournisseur.idFournisseur).subscribe(() => {
        this.getFournisseurs();
      });
    }
  }
}
  
