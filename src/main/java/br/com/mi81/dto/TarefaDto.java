package br.com.mi81.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TarefaDto (

        @Schema(description = "Titulo da tarefa")
    String titulo,

        @Schema(description = "Descrição da tarefa")
    String descricao,

        @Schema(description = "Prioridade da tarefa")
    String prioridade

){}
