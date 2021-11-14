package br.com.fatec.evecontrol.repository;

import br.com.fatec.evecontrol.model.DonoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoEventoRepository extends JpaRepository<DonoEvento, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    DonoEvento findByEmail(String email);

}
