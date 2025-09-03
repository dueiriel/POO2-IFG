package br.edu.ifgoias.academico.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoias.academico.dto.MatriculaDTO;
import br.edu.ifgoias.academico.services.MatriculaService;

@RestController
@RequestMapping("/matriculas")
@CrossOrigin(origins = "http://localhost:4200")
public class MatriculaResource {

    @Autowired
    private MatriculaService matriculaService;

    /**
     * Lista todas as matrículas
     */
    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> listarMatriculas() {
        try {
            List<MatriculaDTO> matriculas = matriculaService.listarMatriculas();
            return ResponseEntity.ok(matriculas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Matricula um aluno em um curso
     */
    @PostMapping
    public ResponseEntity<MatriculaDTO> matricularAluno(@RequestBody MatriculaDTO matriculaDTO) {
        try {
            MatriculaDTO resultado = matriculaService.matricularAluno(matriculaDTO.getIdAluno(), matriculaDTO.getIdCurso());
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Remove matrícula de um aluno de um curso
     */
    @DeleteMapping("/aluno/{idAluno}/curso/{idCurso}")
    public ResponseEntity<String> removerMatricula(
            @PathVariable Integer idAluno,
            @PathVariable Integer idCurso) {
        try {
            matriculaService.removerMatricula(idAluno, idCurso);
            return ResponseEntity.ok("Matrícula removida com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno do servidor");
        }
    }

    /**
     * Lista matrículas de um aluno específico
     */
    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<List<MatriculaDTO>> listarMatriculasPorAluno(
            @PathVariable Integer idAluno) {
        try {
            List<MatriculaDTO> matriculas = matriculaService.listarMatriculasPorAluno(idAluno);
            return ResponseEntity.ok(matriculas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Lista matrículas de um curso específico
     */
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<MatriculaDTO>> listarMatriculasPorCurso(
            @PathVariable Integer idCurso) {
        try {
            List<MatriculaDTO> matriculas = matriculaService.listarMatriculasPorCurso(idCurso);
            return ResponseEntity.ok(matriculas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
