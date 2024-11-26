package br.educationalintegrado.educacional.controller;

import br.educationalintegrado.educacional.dto.TurmaRequestDTO;
import br.educationalintegrado.educacional.model.Curso;
import br.educationalintegrado.educacional.model.Turma;
import br.educationalintegrado.educacional.repository.CursoRepository;
import br.educationalintegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        return ResponseEntity.ok(turma);
    }

    @PostMapping("/{id}/add-turma-in-curso")
    public ResponseEntity<Curso> addTurma(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto){
        Curso curso = this.cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        turma.setCurso(curso);
        this.repository.save(turma);

        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        return ResponseEntity.ok(this.repository.save(turma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}
