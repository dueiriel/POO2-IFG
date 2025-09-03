package br.edu.ifgoias.academico.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifgoias.academico.entities.Aluno;

@Mapper(componentModel = "spring", uses = {CursoMapper.class})
public interface AlunoMapper {
	
	// Converte Aluno para AlunoDTO incluindo cursos
	AlunoDTO toDTO(Aluno aluno);
	
	// Converte AlunoDTO para Aluno (ignora cursos para evitar recursão infinita)
	@Mapping(target = "cursos", ignore = true)
	Aluno toEntity(AlunoDTO alunoDTO);
	
	// Converte Aluno para AlunoSimpleDTO (sem cursos)
	AlunoSimpleDTO toSimpleDTO(Aluno aluno);
	
	// Converte AlunoSimpleDTO para Aluno
	@Mapping(target = "cursos", ignore = true)
	Aluno toEntityFromSimple(AlunoSimpleDTO alunoSimpleDTO);
}
