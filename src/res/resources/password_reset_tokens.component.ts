import { Component, OnInit } from '@angular/core';
import { Password_reset_tokens } from './password_reset_tokens.model';
import { Password_reset_tokensService } from './password_reset_tokens.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-password_reset_tokens',
  templateUrl: './password_reset_tokens.component.html',
  styleUrls: ['./password_reset_tokens.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class Password_reset_tokensComponent implements OnInit {

  password_reset_tokenss: Password_reset_tokens[] = [];
  newPassword_reset_tokens: Password_reset_tokens = new Password_reset_tokens();
  editedPassword_reset_tokens: Password_reset_tokens = new Password_reset_tokens();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private password_reset_tokensService: Password_reset_tokensService
  ) { }

  ngOnInit(): void {
    this.getPassword_reset_tokenss();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.password_reset_tokensService.getPassword_reset_tokenssPages(page).subscribe({
        next: (data) => {
          this.password_reset_tokenss = data.content

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

  getPassword_reset_tokenss(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.password_reset_tokensService.createPassword_reset_tokens(this.newPassword_reset_tokens).subscribe(() => {
      this.getPassword_reset_tokenss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newPassword_reset_tokens = new Password_reset_tokens();
  }

  onEdit(password_reset_tokens: Password_reset_tokens): void {
    this.editedPassword_reset_tokens = password_reset_tokens;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.password_reset_tokensService.updatePassword_reset_tokens(this.editedPassword_reset_tokens.email, this.editedPassword_reset_tokens).subscribe(() => {
      this.getPassword_reset_tokenss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedPassword_reset_tokens = new Password_reset_tokens();
  }

  onDelete(password_reset_tokens: Password_reset_tokens): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer password_reset_tokens ?")) {
      this.password_reset_tokensService.deletePassword_reset_tokens(password_reset_tokens.email).subscribe(() => {
        this.getPassword_reset_tokenss();
      });
    }
  }
}
  
