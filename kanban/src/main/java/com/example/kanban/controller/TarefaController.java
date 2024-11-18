package com.example.kanban.controller;

import com.example.kanban.model.Tarefa;
import com.example.kanban.model.Status;
import com.example.kanban.service.TarefaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> listarTarefaPorId(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
        return new ResponseEntity<>(tarefa, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Tarefa>> listarTarefasPorStatus(@PathVariable Status status) {
        List<Tarefa> tarefas = tarefaService.listarTarefasPorStatus(status);
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<Tarefa> moverTarefa(@PathVariable Long id) {
        Tarefa tarefaAtualizada = tarefaService.moverTarefa(id);
        return new ResponseEntity<>(tarefaAtualizada, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefa);
        return new ResponseEntity<>(tarefaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/relatorio/atrasos")
    public ResponseEntity<List<Tarefa>> gerarRelatorioDeAtrasos() {
        List<Tarefa> tarefasAtrasadas = tarefaService.gerarRelatorioDeAtrasos();
        return new ResponseEntity<>(tarefasAtrasadas, HttpStatus.OK);
    }
}
