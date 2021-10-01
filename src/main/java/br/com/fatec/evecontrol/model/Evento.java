package br.com.fatec.evecontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn
    private DonoEvento donoEvento;

    private LocalDateTime data;

    private String endereco;

    private String cep;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "EVENTO_id"),
            inverseJoinColumns = @JoinColumn(name = "CONVIDADO_id"))
    private List<Convidado> convidados;

    @ManyToMany(mappedBy = "eventos", cascade = CascadeType.ALL) //TODO testar persist
    private List<Funcionario> funcionarios;

}
