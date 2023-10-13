package br.com.oceb.cursosagendados.cursos.controller;

import br.com.oceb.cursosagendados.cursos.model.CursoModel;
import br.com.oceb.cursosagendados.cursos.repository.CursoRepository;
import br.com.oceb.cursosagendados.cursos.ultis.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    CursoRepository cursoRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody CursoModel cursoModel){
        LocalDate currentDate = LocalDate.now();

        if(currentDate.isAfter(cursoModel.getDataCurso())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data do curso deve ser posterior ao dia atual");
        }

        var curso = this.cursoRepository.save(cursoModel);
       return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @GetMapping("/")
    public ResponseEntity<List<CursoModel>> listAll(){
        var cursos = this.cursoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity findCurso(@PathVariable UUID id){
        var curso = this.cursoRepository.findById(id);
        if(curso.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(curso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O curso não foi encontrado ou não existe!");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        Optional<CursoModel> cursoOptional = this.cursoRepository.findById(id);
        if(cursoOptional.isPresent()){
            this.cursoRepository.deleteById(id);
            return ResponseEntity.status(204).body("O curso foi excluido com sucesso!");
        } else {
            return ResponseEntity.status(404).body("O curso não foi encontrado!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoModel> update(@RequestBody CursoModel cursoModel, @PathVariable UUID id){
        var curso = this.cursoRepository.findById(id).orElse(null);
        Utils.copyNonNullProperties(cursoModel, curso);
        return ResponseEntity.status(HttpStatus.OK).body(curso);
    }

}
