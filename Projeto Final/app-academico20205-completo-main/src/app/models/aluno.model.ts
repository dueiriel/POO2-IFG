import { Curso } from './curso.model';

export interface Aluno {
    idaluno?: number;
    nome: string;
    sexo: string;
    dt_nasc: string; // Data como string no formato yyyy-mm-dd para compatibilidade com input date
    cursos?: CursoSimple[];
}

export interface AlunoSimple {
    idaluno?: number;
    nome: string;
    sexo: string;
    dt_nasc: string;
}

export interface CursoSimple {
    idCurso?: number;
    nomeCurso: string;
}
