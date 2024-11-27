package br.educationalintegrado.educacional.controller;


import br.educationalintegrado.educacional.dto.AlunoRequestDTO;
import br.educationalintegrado.educacional.model.Aluno;
import br.educationalintegrado.educacional.model.Matricula;
import br.educationalintegrado.educacional.model.Turma;
import br.educationalintegrado.educacional.repository.AlunoRepository;
import br.educationalintegrado.educacional.repository.MatriculaRepository;
import br.educationalintegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(){
//        return this.repository.findAll();
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id){
//        return this.repository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody AlunoRequestDTO dto){
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatriculaCod(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

//        return this.repository.save(aluno);
        return ResponseEntity.ok(this.repository.save(aluno));
    }

    @PostMapping("/{alunoId}/{turmaId}/add-matricula")
    public ResponseEntity<Matricula> addMatricula(@PathVariable Integer alunoId,
                                                  @PathVariable Integer turmaId){
        Aluno aluno = this.repository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Turma turma = this.turmaRepository.findById(turmaId)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        Matricula matricula = new Matricula();

        matricula.setAluno(aluno);
        matricula.setTurma(turma);

        return  ResponseEntity.ok(this.matriculaRepository.save(matricula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody AlunoRequestDTO dto){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatriculaCod(  dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

//        return this.repository.save(aluno);
        return ResponseEntity.ok(this.repository.save(aluno));
    }

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Integer id){
    Aluno aluno = this.repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

    this.repository.delete(aluno);
    return ResponseEntity.noContent().build();
}

}
