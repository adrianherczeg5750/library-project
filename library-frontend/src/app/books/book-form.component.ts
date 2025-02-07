import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Book } from './book.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class BookFormComponent {
  @Input() book: Book = { title: '', author: '', quantity: 0 };
  @Output() save = new EventEmitter<Book>();
  @Output() cancel = new EventEmitter<void>();

  submitForm() {
    if (this.isValid()) {
      this.save.emit(this.book);
    }
  }

  isValid(): boolean {
    return this.book.title.trim() !== '' &&
      this.book.author.trim() !== '' &&
      this.book.quantity > 0;
  }
}
