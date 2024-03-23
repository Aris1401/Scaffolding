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

  getCommandes(): void {
    this.commandeService.getCommandes().subscribe({
        next: (data) => {
            this.commandes = data
        }
    });
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
  
