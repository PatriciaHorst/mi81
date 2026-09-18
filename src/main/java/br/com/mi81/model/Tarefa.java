package br.com.mi81.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    private Integer id;

    private String titulo;

    private String descricao;

    private String prioridade;

    private boolean status;
}
