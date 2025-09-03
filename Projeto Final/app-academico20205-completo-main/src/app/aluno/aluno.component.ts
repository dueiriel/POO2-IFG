import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { Aluno } from '../models/aluno.model';
import { Page } from '../models/page.model';
import { AlunoService } from '../services/aluno.service';

@Component({
  selector: 'app-aluno',
  standalone: true,
  imports: [CommonModule, FormsModule, NgbModule],
  templateUrl: './aluno.component.html',
  styleUrl: './aluno.component.css'
})
export class AlunoComponent implements OnInit {

  alunos: Aluno[] = [];
  searchTerm: string = '';
  currentPage: number = 0;
  pageSize: number = 10;
  totalPages: number = 0;
  totalElements: number = 0;

  constructor(
    private alunoService: AlunoService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadAlunos();
  }

  loadAlunos(): void {
    this.alunoService.getAlunos(this.currentPage, this.pageSize, this.searchTerm)
      .subscribe((page: Page<Aluno>) => {
        this.alunos = page.content;
        this.totalPages = page.totalPages;
        this.totalElements = page.totalElements;
      });
  }

  onSearch(): void {
    this.currentPage = 0;
    this.loadAlunos();
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.loadAlunos();
  }

  editarAluno(id: number | undefined): void {
    if (id) {
      this.router.navigate(['/aluno-editar', id]);
    }
  }

  gerenciarMatriculas(id: number | undefined): void {
    if (id) {
      this.router.navigate(['/matriculas', id]);
    }
  }

  excluirAluno(id: number | undefined): void {
    if (id) {
      // Usando confirm nativo do JavaScript por simplicidade
      if (confirm('Deseja realmente excluir este aluno?')) {
        this.alunoService.deleteAluno(id).subscribe({
          next: () => {
            alert('Aluno excluído com sucesso!');
            this.loadAlunos();
          },
          error: (error) => {
            console.error('Erro ao excluir aluno:', error);
            alert('Erro ao excluir aluno. Tente novamente.');
          }
        });
      }
    }
  }

  novoAluno(): void {
    this.router.navigate(['/aluno-novo']);
  }

  formatarData(data: string): string {
    const date = new Date(data + 'T00:00:00'); // Adiciona horário para evitar problemas de timezone
    return date.toLocaleDateString('pt-BR');
  }
}
