package br.com.mi81.repository;

import br.com.mi81.model.Tarefa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TarefaRepository {

    private final List<Tarefa> listaTarefas = new ArrayList<>();
    private final AtomicInteger colocadorId = new AtomicInteger(1);

    public TarefaRepository () {
        listaTarefas.add(new Tarefa(colocadorId.getAndIncrement(), "Teste1", "baita", "alta", false));
        listaTarefas.add(new Tarefa(colocadorId.getAndIncrement(), "Teste2", "baita", "alta", false));
        listaTarefas.add(new Tarefa(colocadorId.getAndIncrement(), "Teste3", "baita", "alta", false));
        listaTarefas.add(new Tarefa(colocadorId.getAndIncrement(), "Teste4", "baita", "alta", false));
    }

    public List<Tarefa> listarTarefas() {
        return listaTarefas;
    }

    public Tarefa buscarTarefa(Integer id) {
        return listaTarefas.stream().filter(tarefa -> tarefa.getId().equals(id)).
                findFirst().orElse(null);
    }

    public void cadastrar(Tarefa tarefa){
        tarefa.setId(colocadorId.getAndIncrement());
        listaTarefas.add(tarefa);
    }

    public Boolean atualizar(Integer id, Tarefa tarefaAtualizada){
        Tarefa tarefaAntiga = buscarTarefa(id);

        if(tarefaAntiga == null){
            return false;
        }

        tarefaAntiga.setTitulo(tarefaAtualizada.getTitulo());
        tarefaAntiga.setDescricao(tarefaAtualizada.getDescricao());
        tarefaAntiga.setPrioridade(tarefaAtualizada.getPrioridade());

        return true;

    }

    public Boolean concluir(Integer id) {
        Tarefa tarefa = buscarTarefa(id);

        if (tarefa == null) {
            return false;
        }

        tarefa.setStatus(true);

        return true;
    }

    public Boolean excluir(Integer id){
        Tarefa tarefa = buscarTarefa(id);

        if(tarefa == null){
            return false;
        }else{
            listaTarefas.remove(tarefa);
            return true;
        }
    }
}
