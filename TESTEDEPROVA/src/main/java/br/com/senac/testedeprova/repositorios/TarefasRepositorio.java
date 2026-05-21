package br.com.senac.testedeprova.repositorios;

import br.com.senac.testedeprova.entidades.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefasRepositorio extends JpaRepository<Tarefas, Long> {
    List<Tarefas> findByNomeContaining(String nome);
    List<Tarefas> findByDescricao(String descricao);
    List<Tarefas> findByDataInicioGreaterThan(String dataInicio);
    List<Tarefas> findByDataFim(String dataFinal);
    List<Tarefas> findByAutor(String autor);
}
