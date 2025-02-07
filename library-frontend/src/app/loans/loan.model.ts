import { Book } from '../books/book.model';
import { User } from '../users/user.model';

export class Loan {
  id?: number;
  book: Book | null;
  user: User | null;
  quantity: number;
  takenOutAt: string;
  broughtBackAt?: string | null;

  constructor() {
    this.book = null;
    this.user = null;
    this.quantity = 1;
    this.takenOutAt = new Date().toISOString();
    this.broughtBackAt = null;
  }
}
