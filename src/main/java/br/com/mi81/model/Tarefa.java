package br.com.mi81.model;

import br.com.mi81.enums.StatusTarefa;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private StatusTarefa status;
}
