import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { Aluno } from '../models/aluno.model';
import { AlunoService } from '../services/aluno.service';

@Component({
  selector: 'app-aluno-novo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './aluno-novo.component.html',
  styleUrl: './aluno-novo.component.css'
})
export class AlunoNovoComponent {

  aluno: Aluno = {
    nome: '',
    sexo: '',
    dt_nasc: ''
  };

  constructor(
    private alunoService: AlunoService,
    private router: Router
  ) { }

  onSubmit(): void {
    if (this.isFormValid()) {
      this.alunoService.createAluno(this.aluno).subscribe({
        next: () => {
          this.router.navigate(['/aluno']);
        },
        error: (error) => {
          console.error('Erro ao criar aluno:', error);
          alert('Erro ao criar aluno. Verifique os dados e tente novamente.');
        }
      });
    }
  }

  isFormValid(): boolean {
    return this.aluno.nome.trim() !== '' && 
           this.aluno.sexo.trim() !== '' && 
           this.aluno.dt_nasc !== '';
  }

  onCancel(): void {
    this.router.navigate(['/aluno']);
  }
}
