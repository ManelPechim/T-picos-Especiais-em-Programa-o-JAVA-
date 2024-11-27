package br.educationalintegrado.educacional.dto;

import java.time.LocalDate;

public record AlunoRequestDTO(String nome, String email, String matriculaCod, LocalDate data_nascimento) {
}
