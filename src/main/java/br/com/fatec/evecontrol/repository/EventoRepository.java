package br.com.fatec.evecontrol.repository;

import br.com.fatec.evecontrol.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
