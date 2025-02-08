import { Component, OnInit } from '@angular/core';
import { Type_de_finition } from './type_de_finition.model';
import { Type_de_finitionService } from './type_de_finition.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-type_de_finition',
  templateUrl: './type_de_finition.component.html',
  styleUrls: ['./type_de_finition.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Type_de_finitionComponent implements OnInit {

  type_de_finitions: Type_de_finition[] = [];
  newType_de_finition: Type_de_finition = new Type_de_finition();
  editedType_de_finition: Type_de_finition = new Type_de_finition();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private type_de_finitionService: Type_de_finitionService
  ) { }

  ngOnInit(): void {
    this.getType_de_finitions();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.type_de_finitionService.getType_de_finitionsPages(page).subscribe({
        next: (data) => {
          this.type_de_finitions = data.content

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

  getType_de_finitions(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.type_de_finitionService.createType_de_finition(this.newType_de_finition).subscribe(() => {
      this.getType_de_finitions();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newType_de_finition = new Type_de_finition();
  }

  onEdit(type_de_finition: Type_de_finition): void {
    this.editedType_de_finition = type_de_finition;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.type_de_finitionService.updateType_de_finition(this.editedType_de_finition.tfId, this.editedType_de_finition).subscribe(() => {
      this.getType_de_finitions();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedType_de_finition = new Type_de_finition();
  }

  onDelete(type_de_finition: Type_de_finition): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer type_de_finition ?")) {
      this.type_de_finitionService.deleteType_de_finition(type_de_finition.tfId).subscribe(() => {
        this.getType_de_finitions();
      });
    }
  }
}
  
