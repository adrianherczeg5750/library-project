import { Routes } from '@angular/router';
import { BookListComponent } from './books/book-list.component';
import { BookFormComponent } from './books/book-form.component';
import { LoanListComponent } from './loans/loan-list.component';
import { LoanFormComponent } from './loans/loan-form.component';
import { UserListComponent } from './users/user-list.component';

export const routes: Routes = [
  { path: '', redirectTo: '/books', pathMatch: 'full' },
  { path: 'books', component: BookListComponent },
  { path: 'books/new', component: BookFormComponent },
  { path: 'books/edit/:id', component: BookFormComponent },
  { path: 'loans', component: LoanListComponent },
  { path: 'loans/new', component: LoanFormComponent },
  { path: 'users', component: UserListComponent }
];
