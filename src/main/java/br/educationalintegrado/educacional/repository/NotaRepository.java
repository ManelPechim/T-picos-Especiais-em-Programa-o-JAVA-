package br.educationalintegrado.educacional.repository;

import br.educationalintegrado.educacional.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}
