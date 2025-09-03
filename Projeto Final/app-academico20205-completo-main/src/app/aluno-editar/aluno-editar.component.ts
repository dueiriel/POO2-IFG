import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { Aluno } from '../models/aluno.model';
import { AlunoService } from '../services/aluno.service';

@Component({
  selector: 'app-aluno-editar',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './aluno-editar.component.html',
  styleUrl: './aluno-editar.component.css'
})
export class AlunoEditarComponent implements OnInit {

  aluno: Aluno = {
    nome: '',
    sexo: '',
    dt_nasc: ''
  };
  
  alunoId: number = 0;

  constructor(
    private alunoService: AlunoService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.alunoId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadAluno();
  }

  loadAluno(): void {
    this.alunoService.getAlunoById(this.alunoId).subscribe({
      next: (aluno: Aluno) => {
        this.aluno = aluno;
      },
      error: (error) => {
        console.error('Erro ao carregar aluno:', error);
        alert('Erro ao carregar dados do aluno.');
        this.router.navigate(['/aluno']);
      }
    });
  }

  onSubmit(): void {
    if (this.isFormValid()) {
      this.alunoService.updateAluno(this.alunoId, this.aluno).subscribe({
        next: () => {
          this.router.navigate(['/aluno']);
        },
        error: (error) => {
          console.error('Erro ao atualizar aluno:', error);
          alert('Erro ao atualizar aluno. Verifique os dados e tente novamente.');
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
