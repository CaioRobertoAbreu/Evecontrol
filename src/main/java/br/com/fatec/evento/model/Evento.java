package br.com.fatec.evento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @CreationTimestamp
    private LocalDateTime data;

    private String endereco;

    private String cep;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "EVENTO_id"),
            inverseJoinColumns = @JoinColumn(name = "CONVIDADO_id"))
    private List<Convidado> convidados;

    @ManyToMany(mappedBy = "eventos")
    private List<Funcionario> funcionarios;

}
