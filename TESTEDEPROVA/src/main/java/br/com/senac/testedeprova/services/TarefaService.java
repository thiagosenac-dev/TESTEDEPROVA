package br.com.senac.testedeprova.services;

import br.com.senac.testedeprova.dtos.TarefasFiltroDto;
import br.com.senac.testedeprova.dtos.TarefasRequestDto;
import br.com.senac.testedeprova.entidades.Tarefas;
import br.com.senac.testedeprova.repositorios.TarefasRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {


    private TarefasRepositorio tarefasRepositorio;

    public TarefaService(TarefasRepositorio tarefasRepositorio) {
        this.tarefasRepositorio = tarefasRepositorio;
    }

    public List<Tarefas> listar(TarefasFiltroDto filtro) {
        if(filtro.getNome() != null) {
            return tarefasRepositorio.findByNomeContaining(filtro.getNome());
        }

        if(filtro.getDataInicio() != null) {
            return tarefasRepositorio.findByDataInicioGreaterThan(filtro.getDataInicio());
        }

        return tarefasRepositorio.findAll();
    }

    public Tarefas criar(TarefasRequestDto tarefa) {
        Tarefas tarefaPersist =
                this.tarefasResquestDtoParaTarefas(tarefa);

        return tarefasRepositorio.save(tarefaPersist);
    }

    public Tarefas atualizar(
            Long id,
            TarefasRequestDto tarefa) {
        if(tarefasRepositorio.existsById(id)) {
            Tarefas tarefaPersist =
                    this.tarefasResquestDtoParaTarefas(tarefa);
            tarefaPersist.setId(id);

            return tarefasRepositorio.save(tarefaPersist);
        }

        throw new RuntimeException("Cliente não encontrado");
    }

    public void deletar(Long id) {
        if(tarefasRepositorio.existsById(id)) {
            tarefasRepositorio.deleteById(id);
        }

        throw new RuntimeException("Cliente não encontrado");
    }

    public Tarefas listarPorId(Long id) {
        Optional<Tarefas> retorno = tarefasRepositorio.findById(id);
        if(retorno.isPresent()) {
            return retorno.get();
        }

        throw new RuntimeException("Cliente não encontrado");
    }

    private Tarefas tarefasResquestDtoParaTarefas(
            TarefasRequestDto entrada) {
        Tarefas saida = new Tarefas();
        saida.setNome(entrada.getNome());
        saida.setDescricao(entrada.getDescricao());
        saida.setDataInicio(entrada.getDataInicio());
        saida.setDataFim(entrada.getDataFim());
        saida.setAutor(entrada.getAutor());

        return saida;
    }

}
