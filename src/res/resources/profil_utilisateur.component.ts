import { Component, OnInit } from '@angular/core';
import { Profil_utilisateur } from './profil_utilisateur.model';
import { Profil_utilisateurService } from './profil_utilisateur.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profil_utilisateur',
  templateUrl: './profil_utilisateur.component.html',
  styleUrls: ['./profil_utilisateur.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Profil_utilisateurComponent implements OnInit {

  profil_utilisateurs: Profil_utilisateur[] = [];
  newProfil_utilisateur: Profil_utilisateur = new Profil_utilisateur();
  editedProfil_utilisateur: Profil_utilisateur = new Profil_utilisateur();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private profil_utilisateurService: Profil_utilisateurService
  ) { }

  ngOnInit(): void {
    this.getProfil_utilisateurs();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.profil_utilisateurService.getProfil_utilisateursPages(page).subscribe({
        next: (data) => {
          this.profil_utilisateurs = data.content

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

  getProfil_utilisateurs(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.profil_utilisateurService.createProfil_utilisateur(this.newProfil_utilisateur).subscribe(() => {
      this.getProfil_utilisateurs();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newProfil_utilisateur = new Profil_utilisateur();
  }

  onEdit(profil_utilisateur: Profil_utilisateur): void {
    this.editedProfil_utilisateur = profil_utilisateur;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.profil_utilisateurService.updateProfil_utilisateur(this.editedProfil_utilisateur.puId, this.editedProfil_utilisateur).subscribe(() => {
      this.getProfil_utilisateurs();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedProfil_utilisateur = new Profil_utilisateur();
  }

  onDelete(profil_utilisateur: Profil_utilisateur): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer profil_utilisateur ?")) {
      this.profil_utilisateurService.deleteProfil_utilisateur(profil_utilisateur.puId).subscribe(() => {
        this.getProfil_utilisateurs();
      });
    }
  }
}
  
