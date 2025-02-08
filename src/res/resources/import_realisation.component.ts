import { Component, OnInit } from '@angular/core';
import { Import_realisation } from './import_realisation.model';
import { Import_realisationService } from './import_realisation.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-import_realisation',
  templateUrl: './import_realisation.component.html',
  styleUrls: ['./import_realisation.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Import_realisationComponent implements OnInit {

  import_realisations: Import_realisation[] = [];
  newImport_realisation: Import_realisation = new Import_realisation();
  editedImport_realisation: Import_realisation = new Import_realisation();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private import_realisationService: Import_realisationService
  ) { }

  ngOnInit(): void {
    this.getImport_realisations();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.import_realisationService.getImport_realisationsPages(page).subscribe({
        next: (data) => {
          this.import_realisations = data.content

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

  getImport_realisations(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.import_realisationService.createImport_realisation(this.newImport_realisation).subscribe(() => {
      this.getImport_realisations();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newImport_realisation = new Import_realisation();
  }

  onEdit(import_realisation: Import_realisation): void {
    this.editedImport_realisation = import_realisation;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.import_realisationService.updateImport_realisation(this.editedImport_realisation.id, this.editedImport_realisation).subscribe(() => {
      this.getImport_realisations();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedImport_realisation = new Import_realisation();
  }

  onDelete(import_realisation: Import_realisation): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer import_realisation ?")) {
      this.import_realisationService.deleteImport_realisation(import_realisation.id).subscribe(() => {
        this.getImport_realisations();
      });
    }
  }
}
  
