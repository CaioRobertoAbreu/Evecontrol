package br.com.fatec.evecontrol;

import br.com.fatec.evecontrol.model.Convidado;
import br.com.fatec.evecontrol.model.DonoEvento;
import br.com.fatec.evecontrol.model.Evento;
import br.com.fatec.evecontrol.model.PerfilUsuario;
import br.com.fatec.evecontrol.repository.DonoEventoRepository;
import br.com.fatec.evecontrol.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class EventoApplication implements CommandLineRunner {

    private final DonoEventoRepository donoEventoRepository;
    private final EventoRepository eventoRepository;

    public static void main(String[] args) {
        SpringApplication.run(EventoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var ronnie = Convidado.builder()
                .id(null)
                .nome("Ronald Bilius Weasley")
                .cpf("39230653020")
                .telefone("19987124249")
                .build();

        var hermione = Convidado.builder()
                .id(null)
                .nome("Hermione Jean Granger")
                .cpf("37995691033")
                .telefone("77981258039")
                .build();

        var harry = Convidado.builder()
                .id(null)
                .nome("Harry James Potter")
                .cpf("34735860053")
                .telefone("3899143-0724")
                .build();

        var listaConvidados = Arrays.asList(ronnie, hermione, harry);

        var donoEvento = DonoEvento.builder()
                .id(null)
                .nome("Albus Percival Wulfric Brian Dumbledore")
                .dataNascimento(LocalDate.of(1881, Month.AUGUST, 21))
                .cpf("05448213006")
                .email("albus.dumbledore@gmail.com")
                .senha(new BCryptPasswordEncoder().encode("VarinhaDasVarinhas123*"))
                .perfis(Set.of(PerfilUsuario.USUARIO.getCodigo()))
                .build();

        var admin = DonoEvento.builder()
                .id(null)
                .nome("Caio Abreu")
                .dataNascimento(LocalDate.of(1997, Month.APRIL, 13))
                .cpf("46898769081")
                .email("caio3015@hotmail.com")
                .senha(new BCryptPasswordEncoder().encode("Fatec123*"))
                .perfis(Set.of(PerfilUsuario.ADMIN.getCodigo()))
                .build();

        var evento = new Evento(null,
                "Visita ao Ministério da Magia",
                donoEvento,
                LocalDateTime.of(2015, Month.APRIL, 19, 15, 30),
                "Rua dos Alfeneiros, nº4, Whitehall, Inglaterra",
                "78190970", listaConvidados);

        var eventoTeste = new Evento(null,
                "Evento de teste",
                admin,
                LocalDateTime.of(2015, Month.APRIL, 19, 15, 30),
                "Rua dos Alfeneiros, nº4, Whitehall, Inglaterra",
                "78190970", null);


        donoEventoRepository.saveAll(Arrays.asList(admin, donoEvento));
        eventoRepository.saveAll(Arrays.asList(evento, eventoTeste));
    }
}
