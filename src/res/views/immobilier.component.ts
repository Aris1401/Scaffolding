import { Component, OnInit } from '@angular/core';
import { Immobilier } from './immobilier.model';
import { ImmobilierService } from './immobilier.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-immobilier',
  templateUrl: './immobilier.component.html',
  styleUrls: ['./immobilier.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class ImmobilierComponent implements OnInit {

  immobiliers: Immobilier[] = [];
  newImmobilier: Immobilier = new Immobilier();
  editedImmobilier: Immobilier = new Immobilier();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  constructor(

    private immobilierService: ImmobilierService
  ) { }

  ngOnInit(): void {
    this.getImmobiliers();

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
    this.immobilierService.createImmobilier(this.newImmobilier).subscribe(() => {
      this.getImmobiliers();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newImmobilier = new Immobilier();
  }

  onEdit(immobilier: Immobilier): void {
    this.editedImmobilier = immobilier;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.immobilierService.updateImmobilier(this.editedImmobilier.idImmobilier, this.editedImmobilier).subscribe(() => {
      this.getImmobiliers();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedImmobilier = new Immobilier();
  }

  onDelete(immobilier: Immobilier): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer immobilier ?")) {
      this.immobilierService.deleteImmobilier(immobilier.idImmobilier).subscribe(() => {
        this.getImmobiliers();
      });
    }
  }
}
  
