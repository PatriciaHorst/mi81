package br.com.mi81.dto;

import lombok.Builder;

@Builder
public record TarefaResponse(

        Integer id,
        String titulo,
        String descricao,
        String prioridade
) {}
