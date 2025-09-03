package br.edu.ifgoias.academico.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifgoias.academico.entities.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper {
	
	// Converte Curso para CursoDTO incluindo alunos
	CursoDTO toDTO(Curso curso);
	
	// Converte CursoDTO para Curso (ignora alunos para evitar recursão infinita)
	@Mapping(target = "alunos", ignore = true)
	Curso toEntity(CursoDTO cursoDTO);
	
	// Converte Curso para CursoSimpleDTO (sem alunos)
	CursoSimpleDTO toSimpleDTO(Curso curso);
	
	// Converte CursoSimpleDTO para Curso
	@Mapping(target = "alunos", ignore = true)
	Curso toEntityFromSimple(CursoSimpleDTO cursoSimpleDTO);
}
