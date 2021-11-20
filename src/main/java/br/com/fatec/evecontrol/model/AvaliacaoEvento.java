package br.com.fatec.evecontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPesquisa;

    @Column(nullable = false)
    private Integer nota;

    @Length(max = 400)
    private String comentario;

    @ManyToOne
    private Evento evento;
}
