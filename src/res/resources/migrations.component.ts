import { Component, OnInit } from '@angular/core';
import { Migrations } from './migrations.model';
import { MigrationsService } from './migrations.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-migrations',
  templateUrl: './migrations.component.html',
  styleUrls: ['./migrations.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class MigrationsComponent implements OnInit {

  migrationss: Migrations[] = [];
  newMigrations: Migrations = new Migrations();
  editedMigrations: Migrations = new Migrations();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private migrationsService: MigrationsService
  ) { }

  ngOnInit(): void {
    this.getMigrationss();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.migrationsService.getMigrationssPages(page).subscribe({
        next: (data) => {
          this.migrationss = data.content

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

  getMigrationss(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.migrationsService.createMigrations(this.newMigrations).subscribe(() => {
      this.getMigrationss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newMigrations = new Migrations();
  }

  onEdit(migrations: Migrations): void {
    this.editedMigrations = migrations;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.migrationsService.updateMigrations(this.editedMigrations.id, this.editedMigrations).subscribe(() => {
      this.getMigrationss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedMigrations = new Migrations();
  }

  onDelete(migrations: Migrations): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer migrations ?")) {
      this.migrationsService.deleteMigrations(migrations.id).subscribe(() => {
        this.getMigrationss();
      });
    }
  }
}
  
