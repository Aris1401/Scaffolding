import { Component, OnInit } from '@angular/core';
import { Import_type_maison_travaux } from './import_type_maison_travaux.model';
import { Import_type_maison_travauxService } from './import_type_maison_travaux.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-import_type_maison_travaux',
  templateUrl: './import_type_maison_travaux.component.html',
  styleUrls: ['./import_type_maison_travaux.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Import_type_maison_travauxComponent implements OnInit {

  import_type_maison_travauxs: Import_type_maison_travaux[] = [];
  newImport_type_maison_travaux: Import_type_maison_travaux = new Import_type_maison_travaux();
  editedImport_type_maison_travaux: Import_type_maison_travaux = new Import_type_maison_travaux();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private import_type_maison_travauxService: Import_type_maison_travauxService
  ) { }

  ngOnInit(): void {
    this.getImport_type_maison_travauxs();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.import_type_maison_travauxService.getImport_type_maison_travauxsPages(page).subscribe({
        next: (data) => {
          this.import_type_maison_travauxs = data.content

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

  getImport_type_maison_travauxs(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.import_type_maison_travauxService.createImport_type_maison_travaux(this.newImport_type_maison_travaux).subscribe(() => {
      this.getImport_type_maison_travauxs();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newImport_type_maison_travaux = new Import_type_maison_travaux();
  }

  onEdit(import_type_maison_travaux: Import_type_maison_travaux): void {
    this.editedImport_type_maison_travaux = import_type_maison_travaux;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.import_type_maison_travauxService.updateImport_type_maison_travaux(this.editedImport_type_maison_travaux.id, this.editedImport_type_maison_travaux).subscribe(() => {
      this.getImport_type_maison_travauxs();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedImport_type_maison_travaux = new Import_type_maison_travaux();
  }

  onDelete(import_type_maison_travaux: Import_type_maison_travaux): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer import_type_maison_travaux ?")) {
      this.import_type_maison_travauxService.deleteImport_type_maison_travaux(import_type_maison_travaux.id).subscribe(() => {
        this.getImport_type_maison_travauxs();
      });
    }
  }
}
  
