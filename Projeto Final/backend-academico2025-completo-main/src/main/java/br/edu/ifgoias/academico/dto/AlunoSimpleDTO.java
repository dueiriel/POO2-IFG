package br.edu.ifgoias.academico.dto;

import java.time.LocalDate;
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
public class AlunoSimpleDTO {
	
	private Integer idaluno;
	private String nome;
	private String sexo;
	private LocalDate dt_nasc;
}
