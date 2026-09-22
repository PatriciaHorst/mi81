package br.com.mi81.mapper;

import br.com.mi81.dto.TarefaRequest;
import br.com.mi81.dto.TarefaRequestUpdate;
import br.com.mi81.dto.TarefaResponse;
import br.com.mi81.model.Tarefa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarefaMapper {

    public Tarefa toEntity (TarefaRequest request) {
        return Tarefa.builder()
                .titulo(request.titulo())
                .descricao(request.descricao())
                .prioridade(request.prioridade())
                .build();
    }

    public TarefaResponse toResponse (Tarefa tarefa) {
        return new TarefaResponse(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrioridade()
        );
    }

    public List<TarefaResponse> toResponseList (List<Tarefa> tarefas) {
        return tarefas.stream().map(this::toResponse).toList();
    }

    public void updateEntity (TarefaRequestUpdate requestUpdate, Tarefa tarefa) {
        tarefa.setTitulo(requestUpdate.titulo());
        tarefa.setDescricao(requestUpdate.descricao());
        tarefa.setPrioridade(requestUpdate.prioridade());
    }
}
