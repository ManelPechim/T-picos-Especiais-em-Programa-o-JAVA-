package br.educationalintegrado.educacional.repository;

import br.educationalintegrado.educacional.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository <Disciplina, Integer> {
}
