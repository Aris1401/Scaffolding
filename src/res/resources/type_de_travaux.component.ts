import { Component, OnInit } from '@angular/core';
import { Type_de_travaux } from './type_de_travaux.model';
import { Type_de_travauxService } from './type_de_travaux.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-type_de_travaux',
  templateUrl: './type_de_travaux.component.html',
  styleUrls: ['./type_de_travaux.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Type_de_travauxComponent implements OnInit {

  type_de_travauxs: Type_de_travaux[] = [];
  newType_de_travaux: Type_de_travaux = new Type_de_travaux();
  editedType_de_travaux: Type_de_travaux = new Type_de_travaux();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private type_de_travauxService: Type_de_travauxService
  ) { }

  ngOnInit(): void {
    this.getType_de_travauxs();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.type_de_travauxService.getType_de_travauxsPages(page).subscribe({
        next: (data) => {
          this.type_de_travauxs = data.content

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

  getType_de_travauxs(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.type_de_travauxService.createType_de_travaux(this.newType_de_travaux).subscribe(() => {
      this.getType_de_travauxs();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newType_de_travaux = new Type_de_travaux();
  }

  onEdit(type_de_travaux: Type_de_travaux): void {
    this.editedType_de_travaux = type_de_travaux;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.type_de_travauxService.updateType_de_travaux(this.editedType_de_travaux.ttId, this.editedType_de_travaux).subscribe(() => {
      this.getType_de_travauxs();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedType_de_travaux = new Type_de_travaux();
  }

  onDelete(type_de_travaux: Type_de_travaux): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer type_de_travaux ?")) {
      this.type_de_travauxService.deleteType_de_travaux(type_de_travaux.ttId).subscribe(() => {
        this.getType_de_travauxs();
      });
    }
  }
}
  
