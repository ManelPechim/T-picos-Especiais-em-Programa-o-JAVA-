package br.educationalintegrado.educacional.controller;


import br.educationalintegrado.educacional.dto.CursoRequestDTO;
import br.educationalintegrado.educacional.model.Curso;
import br.educationalintegrado.educacional.model.Turma;
import br.educationalintegrado.educacional.repository.CursoRepository;
import br.educationalintegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
//        return this.repository.findAll();
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Integer id){
//        return this.repository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody CursoRequestDTO dto){
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

//        return this.repository.save(aluno);
        return ResponseEntity.ok(this.repository.save(curso));
    }

    @PostMapping("/{id}/add-turma")
    public ResponseEntity<Curso> addTurma(@PathVariable Integer id,
                                          @RequestBody Turma turma) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        turma.setCurso(curso);
        this.turmaRepository.save(turma);

        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Integer id, @RequestBody CursoRequestDTO dto){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

//        return this.repository.save(aluno);
        return ResponseEntity.ok(this.repository.save(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }

}
