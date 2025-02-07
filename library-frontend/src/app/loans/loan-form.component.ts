import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { Loan } from './loan.model';
import { Book } from '../books/book.model';
import { User } from '../users/user.model';
import { BookService } from '../books/book.service';
import { UserService } from '../users/user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-loan-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './loan-form.component.html'
})
export class LoanFormComponent implements OnInit {
  @Input() loan!: Loan;
  @Output() save = new EventEmitter<Loan>();
  @Output() cancel = new EventEmitter<void>();

  availableBooks: Book[] = [];
  availableUsers: User[] = [];

  constructor(private bookService: BookService, private userService: UserService) {}

  ngOnInit() {

    this.bookService.getBooks().subscribe(books => {
      this.availableBooks = books;
    });

    this.userService.getUsers().subscribe(users => {
      this.availableUsers = users;
    });
  }

  quantityError: boolean = false;

  validateQuantity() {
    if (this.loan.book && this.loan.quantity > this.loan.book.quantity) {
      this.quantityError = true;
    } else {
      this.quantityError = false;
    }
  }

  onSave() {
    if (!this.quantityError) {
      this.loan.takenOutAt = new Date().toISOString();
      this.save.emit(this.loan);
    }
  }


  onCancel() {
    this.cancel.emit();
  }
}
