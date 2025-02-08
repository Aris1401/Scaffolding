import { Component, OnInit } from '@angular/core';
import { Import_paiement } from './import_paiement.model';
import { Import_paiementService } from './import_paiement.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-import_paiement',
  templateUrl: './import_paiement.component.html',
  styleUrls: ['./import_paiement.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Import_paiementComponent implements OnInit {

  import_paiements: Import_paiement[] = [];
  newImport_paiement: Import_paiement = new Import_paiement();
  editedImport_paiement: Import_paiement = new Import_paiement();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private import_paiementService: Import_paiementService
  ) { }

  ngOnInit(): void {
    this.getImport_paiements();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.import_paiementService.getImport_paiementsPages(page).subscribe({
        next: (data) => {
          this.import_paiements = data.content

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

  getImport_paiements(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.import_paiementService.createImport_paiement(this.newImport_paiement).subscribe(() => {
      this.getImport_paiements();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newImport_paiement = new Import_paiement();
  }

  onEdit(import_paiement: Import_paiement): void {
    this.editedImport_paiement = import_paiement;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.import_paiementService.updateImport_paiement(this.editedImport_paiement.id, this.editedImport_paiement).subscribe(() => {
      this.getImport_paiements();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedImport_paiement = new Import_paiement();
  }

  onDelete(import_paiement: Import_paiement): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer import_paiement ?")) {
      this.import_paiementService.deleteImport_paiement(import_paiement.id).subscribe(() => {
        this.getImport_paiements();
      });
    }
  }
}
  
