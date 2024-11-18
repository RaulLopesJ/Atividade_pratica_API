package com.example.kanban.service;

import com.example.kanban.model.Tarefa;
import com.example.kanban.model.Status;
import com.example.kanban.repository.TarefaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public List<Tarefa> listarTarefasPorStatus(Status status) {
        return tarefaRepository.findByStatus(status);
    }
    public Tarefa buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não existe"));
    }

    public Tarefa moverTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        switch (tarefa.getStatus()) {
            case A_FAZER -> tarefa.setStatus(Status.EM_PROGRESSO);
            case EM_PROGRESSO -> tarefa.setStatus(Status.CONCLUIDO);
            case CONCLUIDO -> throw new RuntimeException("Tarefa já está concluída");
        }

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa novaTarefa) {
        Tarefa tarefaExistente = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefaExistente.setTitulo(novaTarefa.getTitulo());
        tarefaExistente.setDescricao(novaTarefa.getDescricao());
        tarefaExistente.setPrioridade(novaTarefa.getPrioridade());
        tarefaExistente.setDataLimite(novaTarefa.getDataLimite());

        return tarefaRepository.save(tarefaExistente);
    }

    public void excluirTarefa(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> gerarRelatorioDeAtrasos() {
        LocalDate hoje = LocalDate.now();
        return tarefaRepository.findByStatusAndDataLimiteBefore(Status.A_FAZER, hoje);
    }
}
