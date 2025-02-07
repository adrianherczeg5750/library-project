import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { User } from './user.model';
import { UserFormComponent } from './user-form.component';
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  standalone: true,
  imports: [CommonModule, UserFormComponent],
  providers: [UserService]
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  selectedUser: User | null = null;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers().subscribe(data => this.users = data);
  }

  updateUser(user: User) {
    if (user.id) {
      this.userService.updateUser(user.id, user).subscribe(() => {
        this.selectedUser = null;
        this.loadUsers();
      });
    }
  }

  addUser(user: User) {
    this.userService.addUser(user).subscribe(() => this.loadUsers());
  }

  deleteUser(id: number) {
    if (confirm('Biztosan törölni szeretnéd?')) {
      this.userService.deleteUser(id).subscribe(() => {
        if (this.selectedUser && this.selectedUser.id === id) {
          this.selectedUser = null;
        }
        this.loadUsers();
      });
    }
  }


  selectUser(user: User) {
    this.selectedUser = { ...user };
  }

  cancelEdit() {
    this.selectedUser = null;
  }
}
