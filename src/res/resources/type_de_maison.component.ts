import { Component, OnInit } from '@angular/core';
import { Type_de_maison } from './type_de_maison.model';
import { Type_de_maisonService } from './type_de_maison.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-type_de_maison',
  templateUrl: './type_de_maison.component.html',
  styleUrls: ['./type_de_maison.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Type_de_maisonComponent implements OnInit {

  type_de_maisons: Type_de_maison[] = [];
  newType_de_maison: Type_de_maison = new Type_de_maison();
  editedType_de_maison: Type_de_maison = new Type_de_maison();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private type_de_maisonService: Type_de_maisonService
  ) { }

  ngOnInit(): void {
    this.getType_de_maisons();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.type_de_maisonService.getType_de_maisonsPages(page).subscribe({
        next: (data) => {
          this.type_de_maisons = data.content

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

  getType_de_maisons(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.type_de_maisonService.createType_de_maison(this.newType_de_maison).subscribe(() => {
      this.getType_de_maisons();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newType_de_maison = new Type_de_maison();
  }

  onEdit(type_de_maison: Type_de_maison): void {
    this.editedType_de_maison = type_de_maison;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.type_de_maisonService.updateType_de_maison(this.editedType_de_maison.tmId, this.editedType_de_maison).subscribe(() => {
      this.getType_de_maisons();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedType_de_maison = new Type_de_maison();
  }

  onDelete(type_de_maison: Type_de_maison): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer type_de_maison ?")) {
      this.type_de_maisonService.deleteType_de_maison(type_de_maison.tmId).subscribe(() => {
        this.getType_de_maisons();
      });
    }
  }
}
  
