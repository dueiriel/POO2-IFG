package br.edu.ifgoias.academico.dto;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class AlunoDTO {
	
	private Integer idaluno;
	
	@NotNull(message = "O nome do aluno é obrigatório")
	@Size(max = 80, message = "O nome do aluno deve ter no máximo 80 caracteres")
	@NotBlank(message = "O nome do aluno é obrigatório")
	private String nome;
	
	@NotNull(message = "O sexo é obrigatório")
	@Size(max = 30, message = "O sexo deve ter no máximo 30 caracteres")
	@NotBlank(message = "O sexo é obrigatório")
	private String sexo;
	
	@NotNull(message = "A data de nascimento é obrigatória")
	private LocalDate dt_nasc;
	
	// Lista de cursos matriculados
	private List<CursoSimpleDTO> cursos;
}
