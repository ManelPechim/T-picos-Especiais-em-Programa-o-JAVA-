package br.educationalintegrado.educacional.repository;

import br.educationalintegrado.educacional.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
}
