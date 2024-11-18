package com.example.kanban.repository;

import com.example.kanban.model.Tarefa;
import com.example.kanban.model.Prioridade;
import com.example.kanban.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByStatus(Status status);

    List<Tarefa> findByPrioridade(Prioridade prioridade);

    List<Tarefa> findByDataLimite(LocalDate dataLimite);

    List<Tarefa> findByStatusAndDataLimiteBefore(Status status, LocalDate dataLimite);
}
