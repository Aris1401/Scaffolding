import { Component, OnInit } from '@angular/core';
import { Realisation_travaux } from './realisation_travaux.model';
import { Realisation_travauxService } from './realisation_travaux.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { Devis } from './devis.model'
import { DevisService } from './devis.service'
import { TypeDeFinition } from './typeDeFinition.model'
import { TypeDeFinitionService } from './typeDeFinition.service'
import { TypeDeMaison } from './typeDeMaison.model'
import { TypeDeMaisonService } from './typeDeMaison.service'
import { Utilisateur } from './utilisateur.model'
import { UtilisateurService } from './utilisateur.service'

@Component({
  selector: 'app-realisation_travaux',
  templateUrl: './realisation_travaux.component.html',
  styleUrls: ['./realisation_travaux.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Realisation_travauxComponent implements OnInit {

  realisation_travauxs: Realisation_travaux[] = [];
  newRealisation_travaux: Realisation_travaux = new Realisation_travaux();
  editedRealisation_travaux: Realisation_travaux = new Realisation_travaux();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  deviss : Devis[] = []
  typeDeFinitions : TypeDeFinition[] = []
  typeDeMaisons : TypeDeMaison[] = []
  utilisateurs : Utilisateur[] = []

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private devisService : DevisService,
    private typeDeFinitionService : TypeDeFinitionService,
    private typeDeMaisonService : TypeDeMaisonService,
    private utilisateurService : UtilisateurService,
    private realisation_travauxService: Realisation_travauxService
  ) { }

  ngOnInit(): void {
    this.getRealisation_travauxs();

    this.getDeviss();
    this.getTypeDeFinitions();
    this.getTypeDeMaisons();
    this.getUtilisateurs();
  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.realisation_travauxService.getRealisation_travauxsPages(page).subscribe({
        next: (data) => {
          this.realisation_travauxs = data.content

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

  getRealisation_travauxs(): void {
    this.switchPage(1)
  }

    getDeviss(): void {
        this.devisService.getDeviss().subscribe({
            next: (data) => {
                this.deviss = data
            }
        });
    }
    getTypeDeFinitions(): void {
        this.typeDeFinitionService.getTypeDeFinitions().subscribe({
            next: (data) => {
                this.typeDeFinitions = data
            }
        });
    }
    getTypeDeMaisons(): void {
        this.typeDeMaisonService.getTypeDeMaisons().subscribe({
            next: (data) => {
                this.typeDeMaisons = data
            }
        });
    }
    getUtilisateurs(): void {
        this.utilisateurService.getUtilisateurs().subscribe({
            next: (data) => {
                this.utilisateurs = data
            }
        });
    }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.realisation_travauxService.createRealisation_travaux(this.newRealisation_travaux).subscribe(() => {
      this.getRealisation_travauxs();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newRealisation_travaux = new Realisation_travaux();
  }

  onEdit(realisation_travaux: Realisation_travaux): void {
    this.editedRealisation_travaux = realisation_travaux;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.realisation_travauxService.updateRealisation_travaux(this.editedRealisation_travaux.rtId, this.editedRealisation_travaux).subscribe(() => {
      this.getRealisation_travauxs();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedRealisation_travaux = new Realisation_travaux();
  }

  onDelete(realisation_travaux: Realisation_travaux): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer realisation_travaux ?")) {
      this.realisation_travauxService.deleteRealisation_travaux(realisation_travaux.rtId).subscribe(() => {
        this.getRealisation_travauxs();
      });
    }
  }
}
  
