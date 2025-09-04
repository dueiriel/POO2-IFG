import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { Aluno } from '../models/aluno.model';
import { Curso } from '../models/curso.model';
import { Matricula } from '../models/matricula.model';
import { AlunoService } from '../services/aluno.service';
import { CursoService } from '../services/curso.service';
import { MatriculaService } from '../services/matricula.service';

@Component({
  selector: 'app-matriculas',
  standalone: true,
  imports: [CommonModule, FormsModule, NgbModule],
  templateUrl: './matriculas.component.html',
  styleUrl: './matriculas.component.css'
})
export class MatriculasComponent implements OnInit {

  aluno: Aluno | null = null;
  cursosDisponiveis: Curso[] = [];
  matriculasAluno: Matricula[] = [];
  cursoSelecionado: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private alunoService: AlunoService,
    private cursoService: CursoService,
    private matriculaService: MatriculaService
  ) { }

  ngOnInit(): void {
    const idAluno = this.route.snapshot.paramMap.get('id');
    if (idAluno) {
      this.carregarDados(+idAluno);
    }
  }

  carregarDados(idAluno: number): void {
    // Carregar dados do aluno
    this.alunoService.getAlunoById(idAluno).subscribe({
      next: (aluno) => {
        this.aluno = aluno;
        this.carregarMatriculas(idAluno);
      },
      error: (error) => {
        console.error('Erro ao carregar aluno:', error);
        alert('Erro ao carregar dados do aluno');
      }
    });

    // Carregar todos os cursos
    this.cursoService.getCursos().subscribe({
      next: (page) => {
        this.cursosDisponiveis = page.content;
      },
      error: (error) => {
        console.error('Erro ao carregar cursos:', error);
      }
    });
  }

  carregarMatriculas(idAluno: number): void {
    this.matriculaService.getMatriculasPorAluno(idAluno).subscribe({
      next: (matriculas) => {
        this.matriculasAluno = matriculas;
      },
      error: (error) => {
        console.error('Erro ao carregar matrículas:', error);
      }
    });
  }

  matricularAluno(): void {
    if (!this.aluno || !this.cursoSelecionado) {
      alert('Selecione um curso para matricular');
      return;
    }

    this.matriculaService.matricularAluno(this.aluno.idaluno!, this.cursoSelecionado).subscribe({
      next: (matricula) => {
        alert(`Aluno ${matricula.nomeAluno} matriculado com sucesso no curso ${matricula.nomeCurso}!`);
        this.carregarMatriculas(this.aluno!.idaluno!);
        this.cursoSelecionado = null;
      },
      error: (error) => {
        console.error('Erro ao matricular aluno:', error);
        alert('Erro ao matricular aluno: ' + (error.message || 'Erro desconhecido'));
      }
    });
  }

  removerMatricula(idCurso: number): void {
    if (!this.aluno) return;

    if (confirm('Deseja realmente remover esta matrícula?')) {
      this.matriculaService.removerMatricula(this.aluno.idaluno!, idCurso).subscribe({
        next: (response) => {
          alert(response);
          this.carregarMatriculas(this.aluno!.idaluno!);
        },
        error: (error) => {
          console.error('Erro ao remover matrícula:', error);
          alert('Erro ao remover matrícula: ' + (error.message || 'Erro desconhecido'));
        }
      });
    }
  }

  cursosNaoMatriculados(): Curso[] {
    if (!this.cursosDisponiveis || !this.matriculasAluno) {
      return this.cursosDisponiveis || [];
    }

    const cursosMatriculados = this.matriculasAluno.map(m => m.idCurso);
    return this.cursosDisponiveis.filter(curso => 
      !cursosMatriculados.includes(curso.idCurso)
    );
  }

  voltar(): void {
    this.router.navigate(['/aluno']);
  }
}
