import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from './user.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class UserFormComponent {
  @Input() user: User = { name: '', address: '' };
  @Output() save = new EventEmitter<User>();
  @Output() cancel = new EventEmitter<void>();

  submitForm() {
    if (this.isValid()) {
      this.save.emit(this.user);
    }
  }

  isValid(): boolean {
    return this.user.name.trim() !== '' && this.user.address.trim() !== '';
  }
}
