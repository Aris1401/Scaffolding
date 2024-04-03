import { Component, OnInit } from '@angular/core';
import { Commande } from './commande.model';
import { CommandeService } from './commande.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { Fournisseur } from './fournisseur.model'
import { FournisseurService } from './fournisseur.service'
import { Immobilier } from './immobilier.model'
import { ImmobilierService } from './immobilier.service'

@Component({
  selector: 'app-commande',
  templateUrl: './commande.component.html',
  styleUrls: ['./commande.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class CommandeComponent implements OnInit {

  commandes: Commande[] = [];
  newCommande: Commande = new Commande();
  editedCommande: Commande = new Commande();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  fournisseurs : Fournisseur[] = []
  immobiliers : Immobilier[] = []

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private fournisseurService : FournisseurService,
    private immobilierService : ImmobilierService,
    private commandeService: CommandeService
  ) { }

  ngOnInit(): void {
    this.getCommandes();

    this.getFournisseurs();
    this.getImmobiliers();
  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.commandeService.getCommandesPages(page).subscribe({
        next: (data) => {
          this.commandes = data.content

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

  getCommandes(): void {
    this.switchPage(1)
  }

    getFournisseurs(): void {
        this.fournisseurService.getFournisseurs().subscribe({
            next: (data) => {
                this.fournisseurs = data
            }
        });
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
    this.commandeService.createCommande(this.newCommande).subscribe(() => {
      this.getCommandes();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newCommande = new Commande();
  }

  onEdit(commande: Commande): void {
    this.editedCommande = commande;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.commandeService.updateCommande(this.editedCommande.idCommande, this.editedCommande).subscribe(() => {
      this.getCommandes();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedCommande = new Commande();
  }

  onDelete(commande: Commande): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer commande ?")) {
      this.commandeService.deleteCommande(commande.idCommande).subscribe(() => {
        this.getCommandes();
      });
    }
  }
}
  
