import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Loan } from './loan.model';

@Injectable({
  providedIn: 'root'
})
export class LoanService {
  private apiUrl = 'http://localhost:8081/api/loans';

  constructor(private http: HttpClient) {}

  getLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(this.apiUrl);
  }

  addLoan(loan: Loan): Observable<Loan> {
    console.log('Sending loan to backend:', loan);
    return this.http.post<Loan>(this.apiUrl, loan);
  }

  returnLoan(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/return`, {});
  }

}
