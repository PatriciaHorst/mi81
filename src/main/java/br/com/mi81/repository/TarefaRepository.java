package br.com.mi81.repository;

import br.com.mi81.enums.StatusTarefa;
import br.com.mi81.model.Tarefa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TarefaRepository {

    private final List<Tarefa> listaTarefas = new ArrayList<>();
    private final AtomicInteger sequencialId = new AtomicInteger(1);

    public TarefaRepository () {
        listaTarefas.add(new Tarefa(sequencialId.getAndIncrement(), "Teste1", "baita", "alta", StatusTarefa.PENDENTE));
        listaTarefas.add(new Tarefa(sequencialId.getAndIncrement(), "Teste2", "baita", "alta", StatusTarefa.PENDENTE));
        listaTarefas.add(new Tarefa(sequencialId.getAndIncrement(), "Teste3", "baita", "alta", StatusTarefa.CONCLUIDA));
        listaTarefas.add(new Tarefa(sequencialId.getAndIncrement(), "Teste4", "baita", "alta", StatusTarefa.PENDENTE));
    }

    public List<Tarefa> listarTarefas() {
        return listaTarefas.stream().toList();
    }

    public List<Tarefa> listarPorStatus (StatusTarefa status) {
        return listaTarefas.stream().filter(tarefa -> tarefa.getStatus().equals(status)).toList();
    }

    public Optional<Tarefa> buscarTarefa(Integer id) {
        return listaTarefas.stream().filter(tarefa -> tarefa.getId().equals(id)).
                findFirst();
    }

    public Tarefa cadastrar(Tarefa tarefa){
        if (tarefa.getId() == null) {
            tarefa.setId(sequencialId.getAndIncrement());
            listaTarefas.add(tarefa);
            return tarefa;
        }
        return tarefa;
    }

    public boolean excluir(Integer id){
        return listaTarefas.removeIf(tarefa -> tarefa.getId().equals(id));
    }
}
