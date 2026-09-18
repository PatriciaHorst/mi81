package br.com.mi81.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Schema(description = "Id tarefa auto-increment ")
    private Integer id;

    @Schema(description = "Titulo da tarefa")
    private String titulo;

    @Schema(description = "Descrição da tarefa")
    private String descricao;

    @Schema(description = "Prioridade de uma tarefa")
    private String prioridade;

    @Schema(description = "Status da tarefa")
    private boolean status;
}
