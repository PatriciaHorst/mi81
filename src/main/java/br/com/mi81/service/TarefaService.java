package br.com.mi81.service;

import br.com.mi81.dto.TarefaRequest;
import br.com.mi81.dto.TarefaRequestUpdate;
import br.com.mi81.dto.TarefaResponse;
import br.com.mi81.enums.StatusTarefa;
import br.com.mi81.mapper.TarefaMapper;
import br.com.mi81.model.Tarefa;
import br.com.mi81.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository repository;
    private final TarefaMapper mapper;

    public TarefaService(TarefaRepository repository, TarefaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TarefaResponse> listar () {
        return mapper.toResponseList(repository.listarTarefas());
    }

    public List<TarefaResponse> listarPorStatus (StatusTarefa status) {
        return mapper.toResponseList(repository.listarPorStatus(status));
    }

    public TarefaResponse cadastrar (TarefaRequest request) {
        Tarefa tarefa = mapper.toEntity(request);
        tarefa.setStatus(StatusTarefa.PENDENTE);
        Tarefa tarefaCadastrada = repository.cadastrar(tarefa);
        return mapper.toResponse(tarefaCadastrada);
    }

    public TarefaResponse buscar (Integer id) {
        return repository.buscarTarefa(id).map(mapper::toResponse).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não achou tarefa com aquele ID"));
    }

    public TarefaResponse atualizar (Integer id, TarefaRequestUpdate requestUpdate) {
        Tarefa tarefa = repository.buscarTarefa(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não achou tarefa com aquele ID"));

        mapper.updateEntity(requestUpdate, tarefa);
        tarefa.setStatus(StatusTarefa.PENDENTE);
        Tarefa tarefaAtualizada = repository.cadastrar(tarefa);
        return mapper.toResponse(tarefaAtualizada);
    }

    public TarefaResponse concluir(Integer id) {
        Tarefa tarefa = repository.buscarTarefa(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não achou tarefa com aquele ID"));

        tarefa.setStatus(StatusTarefa.CONCLUIDA);
        Tarefa tarefaAtualizada = repository.cadastrar(tarefa);
        return mapper.toResponse(tarefaAtualizada);
    }

    public void excluir (Integer id) {
        Tarefa tarefa = repository.buscarTarefa(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não achou tarefa com aquele ID"));

        repository.excluir(id);
    }
}
