package br.educationalintegrado.educacional.controller;

import br.educationalintegrado.educacional.dto.CursoRequestDTO;
import br.educationalintegrado.educacional.dto.DisciplinaRequestDTO;
import br.educationalintegrado.educacional.dto.TurmaRequestDTO;
import br.educationalintegrado.educacional.model.Curso;
import br.educationalintegrado.educacional.model.Disciplina;
import br.educationalintegrado.educacional.model.Professor;
import br.educationalintegrado.educacional.model.Turma;
import br.educationalintegrado.educacional.repository.CursoRepository;
import br.educationalintegrado.educacional.repository.DisciplinaRepository;
import br.educationalintegrado.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        return ResponseEntity.ok(disciplina);
    }

//    , Integer cursoId, Integer professorId
    @PostMapping("/{cursoId}/add-disciplina-in-curso")
    public ResponseEntity<Curso> addDisciplina(@PathVariable Integer cursoId, @RequestBody DisciplinaRequestDTO dto, Integer professorId){

        Curso curso = this.cursoRepository.findById(cursoId)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Professor professor = this.professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        disciplina.setCurso(curso);
        disciplina.setProfessor(professor);

        this.repository.save(disciplina);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        return ResponseEntity.ok(this.repository.save(disciplina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
}
