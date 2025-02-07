export class Book {
  id?: number;
  title: string;
  author: string;
  quantity: number;

  constructor() {
    this.title = '';
    this.author = '';
    this.quantity = 0;
  }
}
