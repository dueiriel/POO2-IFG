package br.edu.ifgoias.academico.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifgoias.academico.dto.AlunoDTO;
import br.edu.ifgoias.academico.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Gerenciamento de alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Listar todos os alunos")
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<AlunoDTO> list = alunoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar aluno por ID")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Integer id) {
        AlunoDTO dto = alunoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @Operation(summary = "Inserir um novo aluno")
    public ResponseEntity<AlunoDTO> insert(@Valid @RequestBody AlunoDTO dto) {
        dto = alunoService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar um aluno")
    public ResponseEntity<AlunoDTO> update(@PathVariable Integer id, @Valid @RequestBody AlunoDTO dto) {
        dto = alunoService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um aluno")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}