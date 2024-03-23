import { Component, OnInit } from '@angular/core';
import { Reception } from './reception.model';
import { ReceptionService } from './reception.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { Commande } from './commande.model'
import { CommandeService } from './commande.service'

@Component({
  selector: 'app-reception',
  templateUrl: './reception.component.html',
  styleUrls: ['./reception.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class ReceptionComponent implements OnInit {

  receptions: Reception[] = [];
  newReception: Reception = new Reception();
  editedReception: Reception = new Reception();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  commandes : Commande[] = []

  constructor(

    private commandeService : CommandeService,
    private receptionService: ReceptionService
  ) { }

  ngOnInit(): void {
    this.getReceptions();

    this.getCommandes();
  }

  getReceptions(): void {
    this.receptionService.getReceptions().subscribe({
        next: (data) => {
            this.receptions = data
        }
    });
  }

    getCommandes(): void {
        this.commandeService.getCommandes().subscribe({
            next: (data) => {
                this.commandes = data
            }
        });
    }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.receptionService.createReception(this.newReception).subscribe(() => {
      this.getReceptions();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newReception = new Reception();
  }

  onEdit(reception: Reception): void {
    this.editedReception = reception;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.receptionService.updateReception(this.editedReception.idReception, this.editedReception).subscribe(() => {
      this.getReceptions();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedReception = new Reception();
  }

  onDelete(reception: Reception): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer reception ?")) {
      this.receptionService.deleteReception(reception.idReception).subscribe(() => {
        this.getReceptions();
      });
    }
  }
}
  
