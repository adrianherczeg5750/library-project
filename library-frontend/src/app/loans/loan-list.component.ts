import { Component, OnInit } from '@angular/core';
import { LoanService } from './loan.service';
import { Loan } from './loan.model';
import { CommonModule } from "@angular/common";
import {Book} from "../books/book.model";
import { BookService } from '../books/book.service';
import { LoanFormComponent } from './loan-form.component';


@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.component.html',
  styleUrls: ['./loan-list.component.css'],
  standalone: true,
  imports: [CommonModule, LoanFormComponent],
  providers: [LoanService, BookService]
})
export class LoanListComponent implements OnInit {
  loans: Loan[] = [];
  selectedLoan: Loan | null = null;
  availableBooks: Book[] = [];

  constructor(private loanService: LoanService, private bookService: BookService) {}

  loadAvailableBooks() {
    this.bookService.getBooks().subscribe(books => {
      this.availableBooks = books.filter(book => book.quantity > 0);
    });
  }

  ngOnInit() {
    this.loadLoans();
    this.loadAvailableBooks();

  }

  loadLoans() {
    this.loanService.getLoans().subscribe(data => {
      this.loans = data.filter(loan => !loan.broughtBackAt);
    });
  }

  returnLoan(loan: Loan) {
    if (!loan.id || loan.broughtBackAt) return;

    this.loanService.returnLoan(loan.id).subscribe(() => {
      console.log(`Loan ${loan.id} successfully returned.`);
      this.loadLoans();
    }, error => {
      console.error('Error returning loan:', error);
    });
  }

  openLoanForm() {
    this.selectedLoan = new Loan();
  }
  addLoan(loan: Loan) {
    console.log('Loan received in addLoan():', loan);

    this.loanService.addLoan(loan).subscribe(() => {
      console.log('Loan successfully added to backend!');
      this.selectedLoan = null;
      this.loadLoans();
    }, error => {
      console.error('Error adding loan:', error);
    });
  }

}
