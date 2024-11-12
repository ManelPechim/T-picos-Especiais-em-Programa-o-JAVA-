package br.educationalintegrado.educacional.repository;

import br.educationalintegrado.educacional.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
