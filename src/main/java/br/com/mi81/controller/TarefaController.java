package br.com.mi81.controller;

import br.com.mi81.dto.TarefaDto;
import br.com.mi81.model.Tarefa;
import br.com.mi81.repository.TarefaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController{

    private TarefaRepository tarefaRepository = new TarefaRepository();

    /**
     * Lista todas as tarefas cadastradas
     * @return Retorna a lista de tarefas com status 200 (OK)
     */
    @Schema(description = "Lista todas as tarefas cadastradas")

    @Tag(name = "GET", description = "Retorna tarefas cadastradas")
    @Operation(summary = "Listar tarefas", description = "Lista todas as tarefas cadastradas")
    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(){
        return ResponseEntity.ok(tarefaRepository.listarTarefas());
    }

    /**
     * Busca uma tarefa pelo ID inserido
     * @param id ID da tarefa para busca
     * @return Retorna a tarefa com status 200 (OK) ou então erro com status 404 (Not Found)
     */
    @Tag(name = "GET", description = "Retorna tarefas cadastradas")
    @Operation(summary = "Buscar tarefas", description = "Busca a tarefas cadastradas")
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Integer id){
        Tarefa tarefa = tarefaRepository.buscarTarefa(id);

        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tarefa);
        }
    }

    /**
     * Cadastra uma nova tarefa
     * @param tarefaDto Recebe atributos da tarefa para cadastro
     * @return Retorna status 201 (CREATED)
     */
    @Tag(name = "POST", description = "Adiciona uma tarefa ao banco")
    @Operation(summary = "Cadastrar Tarefa", description = "Adiciona uma tarefa na lista")
    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody TarefaDto tarefaDto){

        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(tarefaDto.titulo());
        tarefa.setDescricao(tarefaDto.descricao());
        tarefa.setPrioridade(tarefaDto.prioridade());

        tarefaRepository.cadastrar(tarefa);

        return ResponseEntity.status(201).build();
    }

    /**
     * Atualiza os atributos de uma tarefa
     * @param id ID da tarefa para atualizar
     * @param tarefaDto Atributos atualizados inseridos pelo usuário
     * @return Caso a atualização ocorra com sucesso, retorna status 200 (OK), caso contrário 404 (Not Found)
     */
    @Tag(name = "PUT", description = "Atualiza uma tarefa do banco")
    @Operation(summary = "Atualizar Tarefa", description = "Atualiza uma tarefa da lista pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Integer id ,@RequestBody TarefaDto tarefaDto){
        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(tarefaDto.titulo());
        tarefa.setDescricao(tarefaDto.descricao());
        tarefa.setPrioridade(tarefaDto.prioridade());

        Boolean atualizado = tarefaRepository.atualizar(id, tarefa);

        if(atualizado){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Altera o valor do atributo status para true (Concluído)
     * @param id ID da tarefa a ser concluída
     * @return Retorna status 200 (OK) caso sucesso, ou então 404 (Not Found) caso erro
     */
    @Tag(name = "PATCH", description = "Atualiza parcialmente uma tarefa do banco")
    @Operation(summary = "Concluir Tarefa", description = "Atualiza o atributo status para true de uma tarefa da lista pelo ID")
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Void> concluir(@PathVariable Integer id) {
        Boolean concluido = tarefaRepository.concluir(id);

        if (concluido) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
        Boolean excluido = tarefaRepository.excluir(id);

        if(excluido) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
