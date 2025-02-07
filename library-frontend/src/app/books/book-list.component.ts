import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookService } from './book.service';
import { Book } from './book.model';
import { BookFormComponent } from "./book-form.component";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css'],
  standalone: true,
  imports: [CommonModule, BookFormComponent],
  providers: [BookService]
})
export class BookListComponent implements OnInit {
  books: Book[] = [];
  selectedBook: Book | null = null;
  errorMessage: string = "";
  constructor(private bookService: BookService) {}

  ngOnInit() {
    this.loadBooks();
  }

  loadBooks() {
    this.bookService.getBooks().subscribe(data => this.books = data);
  }

  addBook(book: Book) {
    this.bookService.addBook(book).subscribe(() => {
      this.selectedBook = null;
      this.loadBooks();
    });
  }

  updateBook(book: Book) {
    if (book.id) {
      this.bookService.updateBook(book.id, book).subscribe(() => {
        this.selectedBook = null;
        this.loadBooks();
      });
    }
  }

  deleteBook(id: number) {
    if (confirm('Biztosan törölni szeretnéd?')) {
      this.bookService.deleteBook(id).subscribe({
        next: () => {
          this.loadBooks();
          this.errorMessage = '';
        },
        error: (err) => {
          console.error('Hiba történt:', err);
          this.errorMessage = 'Nem törölhető a könyv, mert még van aktív kölcsönzése!';
        }
      });
    }
  }


  selectBook(book: Book) {
    this.selectedBook = { ...book };
  }

  cancelEdit() {
    this.selectedBook = null;
  }
}
