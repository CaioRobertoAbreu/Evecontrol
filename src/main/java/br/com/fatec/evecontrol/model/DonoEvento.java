package br.com.fatec.evecontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class DonoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;

    private String email;

    @JsonIgnore
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Perfis")
    private Set<Integer> perfis = new HashSet<>();

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<PerfilUsuario> getPerfisEnum() {
        return perfis.stream().map(PerfilUsuario::toEnum).collect(Collectors.toSet());
    }
}
