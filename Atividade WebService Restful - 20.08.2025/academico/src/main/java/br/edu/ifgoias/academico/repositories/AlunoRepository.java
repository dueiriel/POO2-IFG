package br.edu.ifgoias.academico.repositories;

import br.edu.ifgoias.academico.entities.Aluno;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

}
