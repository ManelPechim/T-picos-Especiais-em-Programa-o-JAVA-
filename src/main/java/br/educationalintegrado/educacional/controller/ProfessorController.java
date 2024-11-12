package br.educationalintegrado.educacional.controller;


import br.educationalintegrado.educacional.dto.ProfessorRequestDTO;
import br.educationalintegrado.educacional.model.Professor;
import br.educationalintegrado.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll(){
//        return this.repository.findAll();
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id){
//        return this.repository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<Professor> save(@RequestBody ProfessorRequestDTO dto){
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

//        return this.repository.save(professor);
        return ResponseEntity.ok(this.repository.save(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable Integer id, @RequestBody ProfessorRequestDTO dto){
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

//        return this.repository.save(professor);
        return ResponseEntity.ok(this.repository.save(professor));
    }

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Integer id){
    Professor professor = this.repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

    this.repository.delete(professor);
    return ResponseEntity.noContent().build();
}

}
