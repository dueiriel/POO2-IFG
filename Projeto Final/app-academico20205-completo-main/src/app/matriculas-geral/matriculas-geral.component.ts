import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { Matricula } from '../models/matricula.model';
import { MatriculaService } from '../services/matricula.service';

@Component({
  selector: 'app-matriculas-geral',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './matriculas-geral.component.html',
  styleUrl: './matriculas-geral.component.css'
})
export class MatriculasGeralComponent implements OnInit {

  matriculas: Matricula[] = [];
  matriculasFiltradas: Matricula[] = [];
  filtroTexto: string = '';

  constructor(
    private matriculaService: MatriculaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.carregarMatriculas();
  }

  carregarMatriculas(): void {
    this.matriculaService.getMatriculas().subscribe({
      next: (matriculas) => {
        this.matriculas = matriculas;
        this.matriculasFiltradas = matriculas;
      },
      error: (error) => {
        console.error('Erro ao carregar matrículas:', error);
        alert('Erro ao carregar matrículas');
      }
    });
  }

  filtrarMatriculas(): void {
    if (!this.filtroTexto.trim()) {
      this.matriculasFiltradas = this.matriculas;
      return;
    }

    const filtro = this.filtroTexto.toLowerCase();
    this.matriculasFiltradas = this.matriculas.filter(matricula =>
      matricula.nomeAluno.toLowerCase().includes(filtro) ||
      matricula.nomeCurso.toLowerCase().includes(filtro)
    );
  }

  limparFiltro(): void {
    this.filtroTexto = '';
    this.matriculasFiltradas = this.matriculas;
  }

  gerenciarMatriculasAluno(idAluno: number): void {
    this.router.navigate(['/matriculas', idAluno]);
  }

  agrupadoPorAluno(): { [key: string]: Matricula[] } {
    const agrupado: { [key: string]: Matricula[] } = {};
    
    this.matriculasFiltradas.forEach(matricula => {
      const chave = `${matricula.idAluno}-${matricula.nomeAluno}`;
      if (!agrupado[chave]) {
        agrupado[chave] = [];
      }
      agrupado[chave].push(matricula);
    });
    
    return agrupado;
  }

  agrupadoPorCurso(): { [key: string]: Matricula[] } {
    const agrupado: { [key: string]: Matricula[] } = {};
    
    this.matriculasFiltradas.forEach(matricula => {
      const chave = `${matricula.idCurso}-${matricula.nomeCurso}`;
      if (!agrupado[chave]) {
        agrupado[chave] = [];
      }
      agrupado[chave].push(matricula);
    });
    
    return agrupado;
  }

  getTotalMatriculas(): number {
    return this.matriculas.length;
  }

  getTotalAlunos(): number {
    const alunosUnicos = new Set(this.matriculas.map(m => m.idAluno));
    return alunosUnicos.size;
  }

  getTotalCursos(): number {
    const cursosUnicos = new Set(this.matriculas.map(m => m.idCurso));
    return cursosUnicos.size;
  }
}
