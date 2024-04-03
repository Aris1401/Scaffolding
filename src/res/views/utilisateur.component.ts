import { Component, OnInit } from '@angular/core';
import { Utilisateur } from './utilisateur.model';
import { UtilisateurService } from './utilisateur.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class UtilisateurComponent implements OnInit {

  utilisateurs: Utilisateur[] = [];
  newUtilisateur: Utilisateur = new Utilisateur();
  editedUtilisateur: Utilisateur = new Utilisateur();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private utilisateurService: UtilisateurService
  ) { }

  ngOnInit(): void {
    this.getUtilisateurs();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.utilisateurService.getUtilisateursPages(page).subscribe({
        next: (data) => {
          this.utilisateurs = data.content

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

  getUtilisateurs(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.utilisateurService.createUtilisateur(this.newUtilisateur).subscribe(() => {
      this.getUtilisateurs();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newUtilisateur = new Utilisateur();
  }

  onEdit(utilisateur: Utilisateur): void {
    this.editedUtilisateur = utilisateur;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.utilisateurService.updateUtilisateur(this.editedUtilisateur.id, this.editedUtilisateur).subscribe(() => {
      this.getUtilisateurs();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedUtilisateur = new Utilisateur();
  }

  onDelete(utilisateur: Utilisateur): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer utilisateur ?")) {
      this.utilisateurService.deleteUtilisateur(utilisateur.id).subscribe(() => {
        this.getUtilisateurs();
      });
    }
  }
}
  
