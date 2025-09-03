import { AlunoSimple } from './aluno.model';

export interface Curso {
    idCurso: number;
    nomeCurso: string;
    alunos?: AlunoSimple[];
}
