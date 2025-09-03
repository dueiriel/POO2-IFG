package br.edu.ifgoias.academico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifgoias.academico.dto.MatriculaDTO;
import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.entities.Curso;
import br.edu.ifgoias.academico.repositories.AlunoRepository;
import br.edu.ifgoias.academico.repositories.CursoRepository;

@Service
@Transactional
public class MatriculaService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Matricula um aluno em um curso
     */
    public MatriculaDTO matricularAluno(Integer idAluno, Integer idCurso) {
        Aluno aluno = alunoRepository.findById(idAluno)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + idAluno));
        
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + idCurso));

        // Verificar se já está matriculado
        if (curso.getAlunos().contains(aluno)) {
            throw new RuntimeException("Aluno já está matriculado neste curso");
        }

        // Realizar matrícula
        curso.adicionarAluno(aluno);
        aluno.adicionarCurso(curso);

        // Salvar as alterações
        cursoRepository.save(curso);
        alunoRepository.save(aluno);

        // Retornar a matrícula criada
        return new MatriculaDTO(idAluno, idCurso, aluno.getNome(), curso.getNomeCurso());
    }

    /**
     * Remove matrícula de um aluno de um curso
     */
    public void removerMatricula(Integer idAluno, Integer idCurso) {
        Aluno aluno = alunoRepository.findById(idAluno)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + idAluno));
        
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + idCurso));

        // Verificar se está matriculado
        if (!curso.getAlunos().contains(aluno)) {
            throw new RuntimeException("Aluno não está matriculado neste curso");
        }

        // Remover matrícula
        curso.getAlunos().remove(aluno);
        aluno.getCursos().remove(curso);

        // Salvar as alterações
        cursoRepository.save(curso);
        alunoRepository.save(aluno);
    }

    /**
     * Lista todas as matrículas
     */
    public List<MatriculaDTO> listarMatriculas() {
        List<Curso> cursos = cursoRepository.findAll();
        
        return cursos.stream()
            .flatMap(curso -> curso.getAlunos().stream()
                .map(aluno -> new MatriculaDTO(
                    aluno.getIdaluno(),
                    curso.getIdCurso(),
                    aluno.getNome(),
                    curso.getNomeCurso()
                )))
            .collect(Collectors.toList());
    }

    /**
     * Lista matrículas de um aluno específico
     */
    public List<MatriculaDTO> listarMatriculasPorAluno(Integer idAluno) {
        Aluno aluno = alunoRepository.findById(idAluno)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado com ID: " + idAluno));

        return aluno.getCursos().stream()
            .map(curso -> new MatriculaDTO(
                aluno.getIdaluno(),
                curso.getIdCurso(),
                aluno.getNome(),
                curso.getNomeCurso()
            ))
            .collect(Collectors.toList());
    }

    /**
     * Lista matrículas de um curso específico
     */
    public List<MatriculaDTO> listarMatriculasPorCurso(Integer idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
            .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + idCurso));

        return curso.getAlunos().stream()
            .map(aluno -> new MatriculaDTO(
                aluno.getIdaluno(),
                curso.getIdCurso(),
                aluno.getNome(),
                curso.getNomeCurso()
            ))
            .collect(Collectors.toList());
    }
}
