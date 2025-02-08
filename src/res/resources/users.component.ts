import { Component, OnInit } from '@angular/core';
import { Users } from './users.model';
import { UsersService } from './users.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  imports: [
      FormsModule,
      CommonModule
    ],
    standalone: true
})
export class UsersComponent implements OnInit {

  userss: Users[] = [];
  newUsers: Users = new Users();
  editedUsers: Users = new Users();
  isCreateModalOpen = false;
  isEditModalOpen = false;

    // Pagination
    totalOfPages : number = 0
    totalOfElements : number = 0
    currentPage : number = 0

    isFirstPage  = false
    isLastPage = false

  constructor(

    private usersService: UsersService
  ) { }

  ngOnInit(): void {
    this.getUserss();

  }

  // Pagination
    generateRange(): number[] {
      return Array(this.totalOfPages).fill(0).map((x, i) => i);
    }

    switchPage(page : number) {
      this.usersService.getUserssPages(page).subscribe({
        next: (data) => {
          this.userss = data.content

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

  getUserss(): void {
    this.switchPage(1)
  }

  onCreate(): void {
    this.isCreateModalOpen = true;
  }

  onSubmitCreate(): void {
    this.usersService.createUsers(this.newUsers).subscribe(() => {
      this.getUserss();
      this.onCloseCreateModal();
    });
  }

  onCloseCreateModal(): void {
    this.isCreateModalOpen = false;
    this.newUsers = new Users();
  }

  onEdit(users: Users): void {
    this.editedUsers = users;
    this.isEditModalOpen = true;
  }

  onSubmitEdit(): void {
    this.usersService.updateUsers(this.editedUsers.id, this.editedUsers).subscribe(() => {
      this.getUserss();
      this.onCloseEditModal();
    });
  }

  onCloseEditModal(): void {
    this.isEditModalOpen = false;
    this.editedUsers = new Users();
  }

  onDelete(users: Users): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer users ?")) {
      this.usersService.deleteUsers(users.id).subscribe(() => {
        this.getUserss();
      });
    }
  }
}
  
