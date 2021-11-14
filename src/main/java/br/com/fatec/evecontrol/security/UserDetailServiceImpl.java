package br.com.fatec.evecontrol.security;

import br.com.fatec.evecontrol.repository.DonoEventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final DonoEventoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       var donoEvento = repository.findByEmail(email);

       if(donoEvento == null){
           throw new UsernameNotFoundException(email);
       }

       return new UserSec(donoEvento.getId(), donoEvento.getEmail(), donoEvento.getSenha(),
               donoEvento.getPerfisEnum());
    }
}
