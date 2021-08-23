package br.com.fatec.evento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Convidado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String rg;

    private String cpf;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "convidados")
    private List<Evento> eventos;

    private String telefone;
}