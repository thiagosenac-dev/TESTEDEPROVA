package br.com.senac.testedeprova.services;

import br.com.senac.testedeprova.dtos.TarefasRequestDto;
import br.com.senac.testedeprova.entidades.Tarefas;
import br.com.senac.testedeprova.repositorios.TarefasRepositorio;

import java.util.List;
import java.util.Optional;

public class TarefaService {


    private TarefasRepositorio tarefasRepositorio;

    public TarefaService(TarefasRepositorio tarefasRepositorio) {
        this.tarefasRepositorio = tarefasRepositorio;
    }

    public List<Tarefas> listar(@org.checkerframework.checker.nullness.qual.MonotonicNonNull TarefasRequestDto filtro) {
        if(filtro.getNome() != null) {
            return tarefasRepositorio.findByNome(filtro.getNome());
        }

        if(filtro.getAutor() != null) {
            return tarefasRepositorio.findByAutor(filtro.getAutor());
        }

        if(filtro.getDataInicio() != null) {
            return tarefasRepositorio.findByAutor(filtro.getDataInicio());
        }

        if(filtro.getDataFim() != null) {
            return tarefasRepositorio.findByAutor(filtro.getDataFim());
        }
        return tarefasRepositorio.findAll();
    }

    public Tarefas criar(TarefasRequestDto cliente) {
        Tarefas clientePersist =
                this.tarefasResquestDtoParaTarefas(cliente);

        return tarefasRepositorio.save(clientePersist);
    }

    public Tarefas atualizar(
            Long id,
            TarefasRequestDto cliente) {
        if(tarefasRepositorio.existsById(id)) {
            Tarefas clientePersist =
                    this.tarefasResquestDtoParaTarefas(cliente);
            clientePersist.setId(id);

            return tarefasRepositorio.save(clientePersist);
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
