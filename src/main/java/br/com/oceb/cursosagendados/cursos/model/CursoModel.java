package br.com.oceb.cursosagendados.cursos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "TB_CURSOS")
@Data
public class CursoModel {
    @Id
    @GeneratedValue(generator = "UUID0")
    UUID id;
    String UF;
    String cidade;
    String local;
    String horario;
    double valor;
    LocalDate dataCurso;
    String responsavel;
    String telefoneResponsavel;

    @CreationTimestamp
    LocalDateTime criadoEm;
}
