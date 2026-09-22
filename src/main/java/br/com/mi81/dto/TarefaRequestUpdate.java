package br.com.mi81.dto;

public record TarefaRequestUpdate (
        String titulo,
        String descricao,
        String prioridade
){}
