package br.educationalintegrado.educacional.repository;

import br.educationalintegrado.educacional.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
