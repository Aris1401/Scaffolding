import { Component, OnInit } from '@angular/core';
import { Mois } from './mois.model';
import { MoisService } from './mois.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mois',
  templateUrl: './mois.component.html',
  styleUrls: ['./mois.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class MoisComponent implements OnInit {

  moiss: Mois[] = [];
  newMois: Mois = new Mois();
  editedMois: Mois = new Mois();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private moisService: MoisService
  ) { }

  ngOnInit(): void {
    this.getMoiss();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.moisService.getMoissPages(page).subscribe({
        next: (data) => {
          this.moiss = data.content

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

  getMoiss(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.moisService.createMois(this.newMois).subscribe(() => {
      this.getMoiss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newMois = new Mois();
  }

  onEdit(mois: Mois): void {
    this.editedMois = mois;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.moisService.updateMois(this.editedMois.mId, this.editedMois).subscribe(() => {
      this.getMoiss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedMois = new Mois();
  }

  onDelete(mois: Mois): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer mois ?")) {
      this.moisService.deleteMois(mois.mId).subscribe(() => {
        this.getMoiss();
      });
    }
  }
}
  
