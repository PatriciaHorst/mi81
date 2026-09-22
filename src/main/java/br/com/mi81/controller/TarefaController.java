package br.com.mi81.controller;

import br.com.mi81.dto.TarefaRequest;
import br.com.mi81.dto.TarefaRequestUpdate;
import br.com.mi81.dto.TarefaResponse;
import br.com.mi81.enums.StatusTarefa;
import br.com.mi81.mapper.TarefaMapper;
import br.com.mi81.model.Tarefa;
import br.com.mi81.repository.TarefaRepository;
import br.com.mi81.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController{

    private final TarefaService service;

    public TarefaController(TarefaService service, TarefaRepository tarefaRepository, TarefaMapper tarefaMapper) {
        this.service = service;
    }

    /**
     * Lista todas as tarefas cadastradas
     * @return Retorna a lista de tarefas com status 200 (OK)
     */
    @Schema(description = "Lista todas as tarefas cadastradas")

    @Tag(name = "GET", description = "Retorna tarefas cadastradas")
    @Operation(summary = "Listar tarefas", description = "Lista todas as tarefas cadastradas")
    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @Tag(name = "GET", description = "Retorna tarefas cadastradas")
    @Operation(summary = "Listar tarefas", description = "Lista todas as tarefas cadastradas")
    @GetMapping("/filtrar")
    public ResponseEntity<List<TarefaResponse>> listarPorStatus(@RequestParam StatusTarefa status){
         return ResponseEntity.ok(service.listarPorStatus(status));
    }

    /**
     * Busca uma tarefa pelo ID inserido
     * @param id ID da tarefa para busca
     * @return Retorna a tarefa com status 200 (OK) ou então erro com status 404 (Not Found)
     */
    @Tag(name = "GET", description = "Retorna tarefas cadastradas")
    @Operation(summary = "Buscar tarefas", description = "Busca a tarefas cadastradas")
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscar(id));
    }

    /**
     * Cadastra uma nova tarefa
     * @param request Recebe atributos da tarefa para cadastro
     * @return Retorna status 201 (CREATED)
     */
    @Tag(name = "POST", description = "Adiciona uma tarefa ao banco")
    @Operation(summary = "Cadastrar Tarefa", description = "Adiciona uma tarefa na lista")
    @PostMapping
    public ResponseEntity<TarefaResponse> cadastrar(@RequestBody TarefaRequest request){
        TarefaResponse tarefa = service.cadastrar(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefa.id())
                .toUri();

        return ResponseEntity.created(uri).body(tarefa);
    }

    /**
     * Atualiza os atributos de uma tarefa
     * @param id ID da tarefa para atualizar
     * @param request Atributos atualizados inseridos pelo usuário
     * @return Caso a atualização ocorra com sucesso, retorna status 200 (OK), caso contrário 404 (Not Found)
     */
    @Tag(name = "PUT", description = "Atualiza uma tarefa do banco")
    @Operation(summary = "Atualizar Tarefa", description = "Atualiza uma tarefa da lista pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizar(@PathVariable Integer id ,@RequestBody TarefaRequestUpdate request){
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    /**
     * Altera o valor do atributo status para true (Concluído)
     * @param id ID da tarefa a ser concluída
     * @return Retorna status 200 (OK) caso sucesso, ou então 404 (Not Found) caso erro
     */
    @Tag(name = "PATCH", description = "Atualiza parcialmente uma tarefa do banco")
    @Operation(summary = "Concluir Tarefa", description = "Atualiza o atributo status para true de uma tarefa da lista pelo ID")
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<TarefaResponse> concluir(@PathVariable Integer id) {
        return ResponseEntity.ok(service.concluir(id));
    }

    /**
     * Exclui uma tarefa usando o id
     * @param id ID da tarefa a ser excluída
     * @return Retorna um status 404(notFound) caso o id nao seja encontrado e um 204(noContent) quando for excluída
     */
    @Tag(name = "DELETE", description = "Exclui uma tarefa no banco de dados")
    @Operation(summary = "Excluir Tarefa", description = "Exclui uma tarefa da lista pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}