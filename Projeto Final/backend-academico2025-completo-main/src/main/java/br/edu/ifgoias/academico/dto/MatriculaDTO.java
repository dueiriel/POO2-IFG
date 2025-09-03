package br.edu.ifgoias.academico.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDTO {
	
	private Integer idAluno;
	private Integer idCurso;
	private String nomeAluno;
	private String nomeCurso;
}
