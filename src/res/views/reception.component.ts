import { Component, OnInit } from '@angular/core';
import { Reception } from './reception.model';
import { ReceptionService } from './reception.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { Commande } from './commande.model'
import { CommandeService } from './commande.service'

@Component({
  selector: 'app-reception',
  templateUrl: './reception.component.html',
  styleUrls: ['./reception.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class ReceptionComponent implements OnInit {

  receptions: Reception[] = [];
  newReception: Reception = new Reception();
  editedReception: Reception = new Reception();
  isCreateModalOpen = false;
  isEditModalOpen = false;

  commandes : Commande[] = []

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private commandeService : CommandeService,
    private receptionService: ReceptionService
  ) { }

  ngOnInit(): void {
    this.getReceptions();

    this.getCommandes();
  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.receptionService.getReceptionsPages(page).subscribe({
        next: (data) => {
          this.receptions = data.content

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

  getReceptions(): void {
    this.switchPage(1)
  }

    getCommandes(): void {
        this.commandeService.getCommandes().subscribe({
            next: (data) => {
                this.commandes = data
            }
        });
    }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.receptionService.createReception(this.newReception).subscribe(() => {
      this.getReceptions();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newReception = new Reception();
  }

  onEdit(reception: Reception): void {
    this.editedReception = reception;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.receptionService.updateReception(this.editedReception.idReception, this.editedReception).subscribe(() => {
      this.getReceptions();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedReception = new Reception();
  }

  onDelete(reception: Reception): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer reception ?")) {
      this.receptionService.deleteReception(reception.idReception).subscribe(() => {
        this.getReceptions();
      });
    }
  }
}
  
