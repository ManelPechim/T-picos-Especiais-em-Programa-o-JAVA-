package br.educationalintegrado.educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column(name = "matricula")
    private String matriculaCod;

    @Column
    private LocalDate data_nascimento;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnoreProperties("aluno")
    private List<Matricula> matricula;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatriculaCod() {
        return matriculaCod;
    }

    public void setMatriculaCod(String matriculaCod) {
        this.matriculaCod = matriculaCod;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public List<Matricula> getMatricula() {
        return matricula;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matricula = matriculas;
    }
}
