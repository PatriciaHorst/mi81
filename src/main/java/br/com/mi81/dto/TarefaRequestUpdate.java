package br.com.mi81.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record TarefaRequestUpdate (

        @NotNull
        @Schema(description = "Titulo da tarefa")
        String titulo,

        @NotNull
        @Schema(description = "Descrição da tarefa")
        String descricao,

        @NotNull
        @Schema(description = "Prioridade da tarefa")
        String prioridade
){}
