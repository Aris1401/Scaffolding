import { Component, OnInit } from '@angular/core';
import { Devis } from './devis.model';
import { DevisService } from './devis.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { TypeDeMaison } from './typeDeMaison.model'
import { TypeDeMaisonService } from './typeDeMaison.service'

@Component({
  selector: 'app-devis',
  templateUrl: './devis.component.html',
  styleUrls: ['./devis.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class DevisComponent implements OnInit {

  deviss: Devis[] = [];
  newDevis: Devis = new Devis();
  editedDevis: Devis = new Devis();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  typeDeMaisons : TypeDeMaison[] = []

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private typeDeMaisonService : TypeDeMaisonService,
    private devisService: DevisService
  ) { }

  ngOnInit(): void {
    this.getDeviss();

    this.getTypeDeMaisons();
  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.devisService.getDevissPages(page).subscribe({
        next: (data) => {
          this.deviss = data.content

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

  getDeviss(): void {
    this.switchPage(1)
  }

    getTypeDeMaisons(): void {
        this.typeDeMaisonService.getTypeDeMaisons().subscribe({
            next: (data) => {
                this.typeDeMaisons = data
            }
        });
    }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.devisService.createDevis(this.newDevis).subscribe(() => {
      this.getDeviss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newDevis = new Devis();
  }

  onEdit(devis: Devis): void {
    this.editedDevis = devis;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.devisService.updateDevis(this.editedDevis.dId, this.editedDevis).subscribe(() => {
      this.getDeviss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedDevis = new Devis();
  }

  onDelete(devis: Devis): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer devis ?")) {
      this.devisService.deleteDevis(devis.dId).subscribe(() => {
        this.getDeviss();
      });
    }
  }
}
  
