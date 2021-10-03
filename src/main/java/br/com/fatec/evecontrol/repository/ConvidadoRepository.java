package br.com.fatec.evecontrol.repository;

import br.com.fatec.evecontrol.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
}
