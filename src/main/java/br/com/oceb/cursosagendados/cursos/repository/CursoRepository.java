package br.com.oceb.cursosagendados.cursos.repository;

import br.com.oceb.cursosagendados.cursos.model.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CursoRepository extends JpaRepository<CursoModel, UUID> {

}
