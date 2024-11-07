package br.educationalintegrado.educacional.repository;

import br.educationalintegrado.educacional.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
