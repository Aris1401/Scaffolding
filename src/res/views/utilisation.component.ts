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

  constructor(

    private immobilierService : ImmobilierService,
    private utilisationService: UtilisationService
  ) { }

  ngOnInit(): void {
    this.getUtilisations();

    this.getImmobiliers();
  }

  getUtilisations(): void {
    this.utilisationService.getUtilisations().subscribe({
        next: (data) => {
            this.utilisations = data
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
  
