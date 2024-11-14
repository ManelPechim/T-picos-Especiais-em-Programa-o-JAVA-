package br.educationalintegrado.educacional.controller;

import br.educationalintegrado.educacional.dto.TurmaRequestDTO;
import br.educationalintegrado.educacional.model.Turma;
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

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id){
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody TurmaRequestDTO dto){
        Turma turma = new Turma();

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        return ResponseEntity.ok(this.repository.save(turma));
    }
}
