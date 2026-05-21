package br.com.senac.testedeprova.controllers;

import br.com.senac.testedeprova.dtos.TarefasFiltroDto;
import br.com.senac.testedeprova.dtos.TarefasRequestDto;
import br.com.senac.testedeprova.entidades.Tarefas;
import br.com.senac.testedeprova.services.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/tarefas")
public class TarefasController {
    private TarefaService tarefaService;

    public TarefasController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tarefas>> listar(TarefasFiltroDto filtro) {
        return ResponseEntity
                .ok(tarefaService.listar(filtro));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Tarefas> listarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tarefaService.listarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<Tarefas> criar(
            @RequestBody TarefasRequestDto cliente) {
        try {
            return ResponseEntity
                    .ok(tarefaService.criar(cliente));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Tarefas> atualizar(
            @RequestBody TarefasRequestDto cliente,
            @PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(tarefaService.atualizar(id, cliente));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        try {
            tarefaService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }
}
