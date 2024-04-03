import { Component, OnInit } from '@angular/core';
import { Configuration } from './configuration.model';
import { ConfigurationService } from './configuration.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class ConfigurationComponent implements OnInit {

  configurations: Configuration[] = [];
  newConfiguration: Configuration = new Configuration();
  editedConfiguration: Configuration = new Configuration();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private configurationService: ConfigurationService
  ) { }

  ngOnInit(): void {
    this.getConfigurations();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.configurationService.getConfigurationsPages(page).subscribe({
        next: (data) => {
          this.configurations = data.content

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

  getConfigurations(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.configurationService.createConfiguration(this.newConfiguration).subscribe(() => {
      this.getConfigurations();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newConfiguration = new Configuration();
  }

  onEdit(configuration: Configuration): void {
    this.editedConfiguration = configuration;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.configurationService.updateConfiguration(this.editedConfiguration.id, this.editedConfiguration).subscribe(() => {
      this.getConfigurations();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedConfiguration = new Configuration();
  }

  onDelete(configuration: Configuration): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer configuration ?")) {
      this.configurationService.deleteConfiguration(configuration.id).subscribe(() => {
        this.getConfigurations();
      });
    }
  }
}
  
