import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Matricula } from '../models/matricula.model';

@Injectable({
  providedIn: 'root'
})
export class MatriculaService {

  private baseUrl = 'http://localhost:8080/academico/matriculas';

  constructor(private httpClient: HttpClient) { }

  // Listar todas as matrículas
  getMatriculas(): Observable<Matricula[]> {
    return this.httpClient.get<Matricula[]>(this.baseUrl);
  }

  // Matricular aluno em curso
  matricularAluno(idAluno: number, idCurso: number): Observable<Matricula> {
    const matriculaData = { idAluno: idAluno, idCurso: idCurso };
    return this.httpClient.post<Matricula>(this.baseUrl, matriculaData);
  }

  // Remover matrícula
  removerMatricula(idAluno: number, idCurso: number): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/aluno/${idAluno}/curso/${idCurso}`, { responseType: 'text' });
  }

  // Listar matrículas por aluno
  getMatriculasPorAluno(idAluno: number): Observable<Matricula[]> {
    return this.httpClient.get<Matricula[]>(`${this.baseUrl}/aluno/${idAluno}`);
  }

  // Listar matrículas por curso
  getMatriculasPorCurso(idCurso: number): Observable<Matricula[]> {
    return this.httpClient.get<Matricula[]>(`${this.baseUrl}/curso/${idCurso}`);
  }
}
