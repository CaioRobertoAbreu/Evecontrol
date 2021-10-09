package br.com.fatec.evecontrol.repository;

import br.com.fatec.evecontrol.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

    List<Convidado> findAllByCpf(String cpf);

}
