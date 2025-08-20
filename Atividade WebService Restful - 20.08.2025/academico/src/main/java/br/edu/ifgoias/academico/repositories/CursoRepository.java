package br.edu.ifgoias.academico.repositories;

import br.edu.ifgoias.academico.entities.Curso;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

}

