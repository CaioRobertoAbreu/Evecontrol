package br.com.fatec.evecontrol.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class secconfig extends WebSecurityConfigurerAdapter {

    private final String url =  "/evecontrol/v1/**";
    private final String h2 =  "/h2-console/**";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(h2);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(h2).permitAll()
                .antMatchers(url).permitAll()
                .anyRequest().authenticated()
            .and()
                .csrf().disable()
                .cors()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }
}
