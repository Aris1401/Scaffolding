import { Component, OnInit } from '@angular/core';
import { Details_devis } from './details_devis.model';
import { Details_devisService } from './details_devis.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { Devis } from './devis.model'
import { DevisService } from './devis.service'
import { TypeDeTravaux } from './typeDeTravaux.model'
import { TypeDeTravauxService } from './typeDeTravaux.service'
import { Unite } from './unite.model'
import { UniteService } from './unite.service'

@Component({
  selector: 'app-details_devis',
  templateUrl: './details_devis.component.html',
  styleUrls: ['./details_devis.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Details_devisComponent implements OnInit {

  details_deviss: Details_devis[] = [];
  newDetails_devis: Details_devis = new Details_devis();
  editedDetails_devis: Details_devis = new Details_devis();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  deviss : Devis[] = []
  typeDeTravauxs : TypeDeTravaux[] = []
  unites : Unite[] = []

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private devisService : DevisService,
    private typeDeTravauxService : TypeDeTravauxService,
    private uniteService : UniteService,
    private details_devisService: Details_devisService
  ) { }

  ngOnInit(): void {
    this.getDetails_deviss();

    this.getDeviss();
    this.getTypeDeTravauxs();
    this.getUnites();
  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.details_devisService.getDetails_devissPages(page).subscribe({
        next: (data) => {
          this.details_deviss = data.content

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

  getDetails_deviss(): void {
    this.switchPage(1)
  }

    getDeviss(): void {
        this.devisService.getDeviss().subscribe({
            next: (data) => {
                this.deviss = data
            }
        });
    }
    getTypeDeTravauxs(): void {
        this.typeDeTravauxService.getTypeDeTravauxs().subscribe({
            next: (data) => {
                this.typeDeTravauxs = data
            }
        });
    }
    getUnites(): void {
        this.uniteService.getUnites().subscribe({
            next: (data) => {
                this.unites = data
            }
        });
    }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.details_devisService.createDetails_devis(this.newDetails_devis).subscribe(() => {
      this.getDetails_deviss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newDetails_devis = new Details_devis();
  }

  onEdit(details_devis: Details_devis): void {
    this.editedDetails_devis = details_devis;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.details_devisService.updateDetails_devis(this.editedDetails_devis.ddId, this.editedDetails_devis).subscribe(() => {
      this.getDetails_deviss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedDetails_devis = new Details_devis();
  }

  onDelete(details_devis: Details_devis): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer details_devis ?")) {
      this.details_devisService.deleteDetails_devis(details_devis.ddId).subscribe(() => {
        this.getDetails_deviss();
      });
    }
  }
}
  
